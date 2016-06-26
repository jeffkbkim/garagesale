package controllers;

import com.google.inject.Inject;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.addmodifyitem;
import views.html.sale;
import views.html.catalog;

import java.util.List;

import play.Logger;

/**
 * Created by Yuda on 6/26/16.
 */
public class CatalogController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result renderCatalogPage(int saleId) {
        //TODO: handle invalid saleId
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchSaleById(saleId);

        List<Item> items = Item.fetchItemsBySale(sale);

        return ok(catalog.render(user, sale, items));
    }

    public Result renderAddModifyItemPage(int saleId) {
        // TODO: handle invalid saleId
        // TODO: handle modify item
        User user = Utils.getUserSession();

        // Item item = Item.fetchItemById(itemId);
        Sale sale = Sale.fetchSaleById(saleId);
        return ok(addmodifyitem.render(user, sale));
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
}
