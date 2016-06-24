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

//    public Result view() {
//        Logger.debug("SaleController view called!");
//        String user = session("connected");
//        List<Sale> sales = Arrays.asList(
//                new Sale("garage", "atlanta"),
//                new Sale("garage", "atlanta"),
//                new Sale("garage", "atlanta")
//        );
//        if (user != null) {
//            List<User> listUsers = new Model.Finder(User.class).all();
//            displayUser = listUsers.get(listUsers.indexOf(new User(user, null)));
//            if (displayUser != null) {
//                if (displayUser.getSaleList() != null) {
//                    return ok(views.html.sale.render(displayUser, sales));
//                }
//            }
//        }
//        return ok(sale.render(displayUser, sales));
//    }

    public Result createSale() {
        Logger.debug("createSale called!");

        Form<SaleFormData> saleForm = formFactory.form(SaleFormData.class).bindFromRequest();
        User dummyUser = null;

        if (saleForm != null) {
            SaleFormData newSale = saleForm.get();
            String username = session("connected");
            //TODO: implement session
            //dummyUser = new User("kangbin", "123456");
//            List<User> listUsers = new Model.Finder(User.class).all();
//            User thisUser = listUsers.get(listUsers.indexOf(new User(user, null)));
            User user = User.find.where().eq("userName", username).findUnique();
            List<Sale> allSales = Sale.find.all();
            Sale sale = new Sale(newSale.name, newSale.location);
            sale.user = user;
            user.addSale(sale);
            Logger.debug("Current User: " + user.getUserName());
            Logger.debug("Saving sale: " + sale.name);
            Logger.debug("User ID: " + sale.user.id);
            sale.save();
            user.save();
            Logger.debug("Sale ID: " + sale.saleID);
//            if (thisUser == null) {
//                Logger.debug("User is null.");
//            }
//            thisUser.addSale(sale.saleID);
//            thisUser.update();
        }
        //return ok(views.html.sale.render(dummyUser, sales));
        return redirect("/sale");
    }

    public Result createSalePage() {
        //TODO: implement session
        String username = session("connected");
//        List<User> listUsers = new Model.Finder(User.class).all();
//        User thisUser = listUsers.get(listUsers.indexOf(new User(username, null)));
        User user = User.find.select("*").where().eq("userName", username).findUnique();
        List<Sale> allSales = Sale.find.all();
        return ok(views.html.createsale.render(user));
    }

    public Result getSales() {
        Logger.debug("getSale called!");
        String username = session("connected");
//        List<User> listUsers = new Model.Finder(User.class).all();
//        User thisUser = listUsers.get(listUsers.indexOf(new User(username, null)));

        User user = User.find.select("*").where().eq("userName", username).findUnique();
        // List<Sale> allSales = user.getSales();
        Logger.debug("GET sale user: " + user.id + ", " + user.userName);
        // List<Sale> allSales = Sale.find.all();
        List<Sale> allSales = Sale.find.select("*").where().eq("user_id", user.id).findList();
        for (Sale sale : allSales) {
            Logger.debug("GET sale: " + sale.name);
            Logger.debug("User ID in this sale: " + sale.user.id);
        }

        // TODO: implement session
//        if (user != null) {
//            List<User> listUsers = new Model.Finder(User.class).all();
//            thisUser = listUsers.get(listUsers.indexOf(new User(user, null)));
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
        return ok(views.html.sale.render(user, allSales));
    }
}
