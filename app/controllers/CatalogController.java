package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.additem;
import views.html.modifyitem;
import views.html.sale;
import views.html.catalog;
import views.html.tag;
import views.html.alltags;
import views.html.receipt;
import views.html.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import play.Logger;

/**
 * Created by Yuda on 6/26/16.
 * This controller is used to the render the catalog page, add, and modify Item.
 */
public class CatalogController extends Controller {
    // TODO: check validity of parameters for security
    @Inject
    FormFactory formFactory;

    /**
     * renders catalog page.
     * @param saleId id of sale
     * @return catalog page
     */
    public Result renderCatalogPage(int saleId) {
        //TODO: handle invalid saleId
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchSaleById(saleId);

        List<Item> items = Item.fetchItemsBySale(sale);
        List<Transaction> transactions = Transaction.fetchTransactionsBySale(sale);
        List<Receipt> receipts = Receipt.fetchReceiptsBySale(sale);
        return ok(catalog.render(user, sale, items, transactions, receipts));
    }

    /**
     * renders add item page.
     * @param saleId id of sale
     * @return additem page
     */
    public Result renderAddItemPage(int saleId) {
        // TODO: handle invalid saleId
        User user = Utils.getUserSession();

        Sale sale = Sale.fetchSaleById(saleId);
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
        Sale sale = Sale.fetchSaleById(saleId);
        return ok(modifyitem.render(user, sale, item));
    }

    public Result renderTag(int saleId, int itemId) {
        User user = Utils.getUserSession();
        Item item = Item.fetchItemById(itemId);
        Sale sale = Sale.fetchSaleById(saleId);
        return ok(tag.render(user, sale, item));
    }

    public Result renderAllTags(int saleId) {
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchSaleById(saleId);
        List<Item> items = Item.fetchItemsBySale(sale);
        return ok(alltags.render(user, sale, items));
    }

    public Result renderReceipt(int receiptId) {
        User user = Utils.getUserSession();
        Receipt r = Receipt.fetchReceiptById(receiptId);
        List<Transaction> transactions = Transaction.fetchTransactionByReceipt(r);
        Sale sale = r.getSale();
        return ok(receipt.render(user, sale, r, transactions));
    }

    public Result renderReport(int saleId) {
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchSaleById(saleId);
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

        Item item = new Item(itemFormData.name, itemFormData.description,
                itemFormData.quantity, itemFormData.price);
        Sale sale = Sale.fetchSaleById(itemFormData.saleId);
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

        int itemId = itemFormData.itemId;
        Item item = Item.fetchItemById(itemId);
        item.setAllFields(itemFormData.name,
                            itemFormData.description,
                            itemFormData.quantity,
                            itemFormData.price);
        Sale sale = Sale.fetchSaleById(itemFormData.saleId);
        item.setSale(sale);
        item.save();

        return redirect(routes.CatalogController.renderCatalogPage(sale.getId()));
    }

    public Result makeTransaction() {
        JsonNode json = request().body().asJson();
        Iterator i = json.iterator();
        JsonNode firstItem = (JsonNode) i.next();
        int saleID = Integer.parseInt(String.valueOf(firstItem.findValue("saleId")));
        Sale sale = Sale.fetchSaleById(saleID);

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
            buyer = buyer.substring(1, buyer.length() - 1);
            Logger.debug(itemID + " " + quantity + " " + buyer);
            Item item = Item.fetchItemById(itemID);
            double profit = item.getPrice() * quantity;
            totalProfit += profit;
            Transaction t = new Transaction(quantity, profit, buyer);
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



}
