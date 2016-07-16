package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import play.Logger;

/**
 * Created by Yuda on 6/26/16.
 * This controller is used to the render the catalog page, add, and modify Item.
 */
public class CatalogController extends Controller {
    // TODO: check validity of parameters for security
    @Inject
    private FormFactory formFactory;

    /**
     * renders catalog page.
     * @param saleId id of sale
     * @return catalog page
     */
    public Result renderCatalogPage(int saleId) {
        //TODO: handle invalid saleId
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);

        List<Item> items = Item.fetchItemsBySale(sale);
        List<Transaction> transactions = Transaction.fetchTransactionsBySale(sale);
        List<Receipt> receipts = Receipt.fetchReceiptsBySale(sale);
        Role.RoleEnum role = userRoleForSale(user, sale);
        return ok(catalog.render(user, sale, role, items, transactions, receipts));
    }

    /**
     * gets roles for a sale
     * @param user user
     * @param sale sale
     * @return role
     */
    public Role.RoleEnum userRoleForSale(User user, Sale sale) {
        List<Role> roles = Role.fetchBySaleIdAndUserId(sale.getId(), user.getId());
        if (roles.isEmpty()) {
            throw new NoSuchElementException();
        }
        return roles.get(0).getRole();
    }

    /**
     * renders catalog page.
     * @param saleId id of sale
     * @return catalog page
     */
    public Result renderCatalogReadOnlyPage(int saleId) {
        //TODO: handle invalid saleId
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);
        return ok(catalogreadonly.render(user, sale, Item.fetchItemsBySale(sale)));
    }


    /**
     * renders add item page.
     * @param saleId id of sale
     * @return additem page
     */
    public Result renderAddItemPage(int saleId) {
        // TODO: handle invalid saleId
        User user = Utils.getUserSession();

        Sale sale = Sale.fetchById(saleId);
        return ok(additem.render(user, sale));
    }

    /**
     * renders modify item page.
     * @param saleId sale id
     * @param itemId item id
     * @return modifyitem page
     */
    public Result renderModifyItemPage(int saleId, int itemId) {
        // TODO: handle invalid saleId
        User user = Utils.getUserSession();

        Item item = Item.fetchItemById(itemId);
        Sale sale = Sale.fetchById(saleId);
        return ok(modifyitem.render(user, sale, item));
    }

    /**
     * renders tag page
     * @param saleId id of sale
     * @param itemId id of item
     * @return renders tag html with corresponding user, sale id, and item id
     */
    public Result renderTag(int saleId, int itemId) {
        User user = Utils.getUserSession();
        Item item = Item.fetchItemById(itemId);
        Sale sale = Sale.fetchById(saleId);
        return ok(tag.render(user, sale, item));
    }

    /**
     * fetches all items in sale and renders alltags page
     * @param saleId id of sale
     * @return renders alltags html
     */
    public Result renderAllTags(int saleId) {
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);
        List<Item> items = Item.fetchItemsBySale(sale);
//        scala.collection.immutable.List<Item> itemsImm
//                = JavaConverters.asScalaBufferConverter(items).asScala().toList();
        return ok(alltags.render(user, sale, items));
    }

    /**
     * creates receipt and renders receipt page
     * @param receiptId
     * @return receipt page
     */
    public Result renderReceipt(int receiptId) {
        User user = Utils.getUserSession();
        Receipt r = Receipt.fetchReceiptById(receiptId);
        List<Transaction> transactions = Transaction.fetchTransactionByReceipt(r);
//        scala.collection.immutable.List<Transaction> transactionsImm
//                = JavaConverters.asScalaBufferConverter(transactions).asScala().toList();
        Sale sale = r.getSale();
        return ok(receipt.render(user, sale, r, transactions));
    }

    public Result renderReport(int saleId) {
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);
        List<Receipt> receipts = Receipt.fetchReceiptsBySale(sale);
        List<Item> items = Item.fetchItemsBySale(sale);
        return ok(report.render(user, sale, receipts, items));
    }

    /**
     * adds item
     *
     * @return redirect to add item page with added item.
     */
    public Result addItem() {
        //TODO: need to validate form, sale, and user
        Form<ItemFormData> itemForm = formFactory.form(ItemFormData.class).bindFromRequest();
        ItemFormData itemFormData = itemForm.get();

        Item item = new Item(itemFormData.getName(), itemFormData.getDescription(),
                itemFormData.getQuantity(), itemFormData.getPrice());
        Sale sale = Sale.fetchById(itemFormData.getSaleId());
        item.setSale(sale);
        item.save();

        return redirect(routes.CatalogController.renderCatalogPage(sale.getId()));
    }

    /**
     * changes item information
     * @return add item page with modified item information.
     */
    public Result modifyItem() {
        //TODO: need to validate form, sale, and user
        Form<ItemFormData> itemForm = formFactory.form(ItemFormData.class).bindFromRequest();
        ItemFormData itemFormData = itemForm.get();

        int itemId = itemFormData.getItemId();
        Item item = Item.fetchItemById(itemId);
        item.setAllFields(itemFormData.getName(),
                            itemFormData.getDescription(),
                            itemFormData.getQuantity(),
                            itemFormData.getPrice());
        Sale sale = Sale.fetchById(itemFormData.getSaleId());
        item.setSale(sale);
        item.save();

        return redirect(routes.CatalogController.renderCatalogPage(sale.getId()));
    }

    /**
     * makes a transaction.
     *
     * @return catalog page with updated transaction.
     */
    public Result makeTransaction() {
        JsonNode json = request().body().asJson();
        Iterator i = json.iterator();
        JsonNode firstItem = (JsonNode) i.next();
        int saleID = Integer.parseInt(String.valueOf(firstItem.findValue("saleId")));
        Sale sale = Sale.fetchById(saleID);

        Receipt receipt = new Receipt();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        receipt.setDate(dateFormat.format(date));
        receipt.setSale(sale);
        receipt.save();
        double totalProfit = 0;

        while(i.hasNext()) {
            JsonNode transaction = (JsonNode) i.next();
            int itemID = Integer.parseInt(String.valueOf(transaction.findValue("id")));
            int quantity = Integer.parseInt(String.valueOf(transaction.findValue("quantity")));
            String buyer = transaction.findValue("buyer").toString();
            String method = transaction.findValue("payment").toString();
            buyer = buyer.substring(1, buyer.length() - 1);
            Logger.debug(itemID + " " + quantity + " " + buyer);
            Item item = Item.fetchItemById(itemID);
            double profit = item.getPrice() * quantity;
            totalProfit += profit;
            Transaction t = new Transaction(quantity, profit, buyer, method);
            t.setSale(sale);
            t.setItem(item);
            t.setReceipt(receipt);
            t.save();
            item.setQuantity(item.getQuantity() - quantity);
            item.update();
        }
        receipt.setProfit(totalProfit);
        sale.addEarnings(totalProfit);
        sale.update();
        receipt.update();
        return (redirect(routes.CatalogController.renderCatalogPage(saleID)));
    }

    /**
     * print owners of the sale, separated by commas
     * @param sale relevant sale
     * @return a string that shows a list of users, separated by commas
     */
    public static String printOwners(Sale sale) {
        List<Role> roles = Role.fetchBySaleIdForARole(sale.getId(), Role.RoleEnum.saleAdmin);

        List<User> owners = User.fetchByIds(Role.mapRolesToUserIds(roles));

        StringBuilder output = new StringBuilder();

        for (User owner : owners) {
            output.append(owner.getUserName());
            output.append(", ");
        }

        return output.substring(0, output.length() - 2);

    }

}
