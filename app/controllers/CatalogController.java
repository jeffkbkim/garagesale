package controllers;

import com.google.inject.Inject;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.additem;
import views.html.modifyitem;
import views.html.sale;
import views.html.catalog;

import java.util.List;

import play.Logger;

/**
 * Created by Yuda on 6/26/16.
 */
public class CatalogController extends Controller {
    // TODO: check validity of parameters for security
    @Inject
    FormFactory formFactory;

    public Result renderCatalogPage(int saleId) {
        //TODO: handle invalid saleId
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchSaleById(saleId);

        List<Item> items = Item.fetchItemsBySale(sale);

        return ok(catalog.render(user, sale, items));
    }

    public Result renderAddItemPage(int saleId) {
        // TODO: handle invalid saleId
        User user = Utils.getUserSession();

        Sale sale = Sale.fetchSaleById(saleId);
        return ok(additem.render(user, sale));
    }

    public Result renderModifyItemPage(int saleId, int itemId) {
        // TODO: handle invalid saleId
        User user = Utils.getUserSession();

        Item item = Item.fetchItemById(itemId);
        Sale sale = Sale.fetchSaleById(saleId);
        return ok(modifyitem.render(user, sale, item));
    }

    public Result addItem() {
        //TODO: need to validate form, sale, and user
        Form<ItemFormData> itemForm = formFactory.form(ItemFormData.class).bindFromRequest();
        ItemFormData itemFormData = itemForm.get();

        Item item = new Item(itemFormData.name, itemFormData.description,
                itemFormData.quantity, itemFormData.price);
        Sale sale = Sale.fetchSaleById(itemFormData.saleId);
        item.setSale(sale);
        item.save();

        return redirect("/catalog?saleId=" + sale.getId());
    }

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

        return redirect("/catalog?saleId=" + sale.getId());
    }

}
