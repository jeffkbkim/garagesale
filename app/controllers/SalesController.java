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

    public Result createSale() {
        Form<SaleFormData> saleForm = formFactory.form(SaleFormData.class).bindFromRequest();
        User dummyUser = null;

        if (saleForm != null) {
            SaleFormData newSale = saleForm.get();
            String username = session("connected");
            User user = User.find.where().eq("userName", username).findUnique();
            List<Sale> allSales = Sale.find.all();
            Sale sale = new Sale(newSale.name, newSale.location);
            sale.setUser(user);
            user.addSale(sale);
            sale.save();
            user.save();
        }
        return redirect("/sale");
    }

    public Result createSalePage() {
        String username = session("connected");
        User user = User.find.select("*").where().eq("userName", username).findUnique();
        return ok(views.html.createsale.render(user));
    }

    public Result getSales() {
        String username = session("connected");
        User user = User.find.select("*").where().eq("userName", username).findUnique();
        List<Sale> allSales = Sale.find.select("*").where().eq("user_id", user.id).findList();
        return ok(views.html.sale.render(user, allSales));
    }
}
