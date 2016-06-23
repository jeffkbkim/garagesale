package controllers;

import com.avaje.ebean.Model;
import com.google.inject.Inject;
import models.ProfileFormData;
import models.Sale;
import models.SaleFormData;
import models.User;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.data.format.Formats;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import views.html.profile;
import views.html.sale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Jeff on 6/16/16.
 */
public class SalesController extends Controller {
    @Inject
    FormFactory formFactory;
    User displayUser;

    public Result view() {
        Logger.debug("SaleController view called!");
        String user = session("connected");
        List<Sale> sales = Arrays.asList(
                new Sale("garage", "atlanta"),
                new Sale("garage", "atlanta"),
                new Sale("garage", "atlanta")
        );
        if (user != null) {
            List<User> listUsers = new Model.Finder(User.class).all();
            displayUser = listUsers.get(listUsers.indexOf(new User(user, null)));
            if (displayUser != null) {
                if (displayUser.getSaleList() != null) {
                    return ok(views.html.sale.render(displayUser, sales));
                }
            }
        }
        return ok(sale.render(displayUser, sales));
    }

    public Result createSale() {
        Logger.debug("createSale called!");
        List<Sale> sales = Arrays.asList(
                new Sale("garage", "atlanta"),
                new Sale("garage", "atlanta"),
                new Sale("garage", "atlanta")
        );

        Form<SaleFormData> saleForm = formFactory.form(SaleFormData.class).bindFromRequest();
        User dummyUser = null;

        if (saleForm != null) {
            SaleFormData newSale = saleForm.get();
            String user = session("connected");
            //TODO: implement session
            dummyUser = new User("kangbin", "123456");
//            User thisUser;
//            List<User> listUsers = new Model.Finder(User.class).all();
//            thisUser = listUsers.get(listUsers.indexOf(new User(user, null)));
            Sale sale = new Sale(newSale.name, newSale.location);
            sale.save();
            Logger.debug("Sale ID: " + sale.saleID);
//            if (thisUser == null) {
//                Logger.debug("User is null.");
//            }
//            thisUser.addSale(sale.saleID);
//            thisUser.update();
        }
//        return ok(views.html.sale.render(dummyUser, sales));
        return getSales();
    }

    public Result createSalePage() {
        //TODO: implement session
        User dummyUser = new User("kangbin", "123456");
        return ok(views.html.createsale.render(dummyUser));
    }

    public Result getSales() {
        Logger.debug("getSale called!");
        String user = session("connected");
        User thisUser;
        List<Sale> allSales = Sale.find.all();

        // TODO: implement session
        User dummyUser = new User("kangbin", "12345");
//        if (user != null) {
//            List<User> listUsers = new Model.Finder(User.class).all();
//            thisUser = listUsers.get(listUsers.indexOf(new User(user, null)));
//            listSaleID = thisUser.getSaleList();
//        }
//        if (listSaleID != null) {
//            for (Integer i : listSaleID) {
//                Sale fetchedSale = (Sale) new Model.Finder(Sale.class).byId(i);
//                if (fetchedSale != null) {
//                    allSales.add(fetchedSale);
//                }
//            }
//        }
//        return ok(toJson(allSales));
        return ok(views.html.sale.render(dummyUser, allSales));
    }
}