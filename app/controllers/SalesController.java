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
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import views.html.profile;
import views.html.sale;
import java.util.List;

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
        if (user != null) {
            List<User> listUsers = new Model.Finder(User.class).all();
            displayUser = listUsers.get(listUsers.indexOf(new User(user, null)));
            if (displayUser != null) {
                if (displayUser.getSaleList() != null) {
                    return ok(views.html.sale.render(displayUser, displayUser.getSaleList().get(0)));
                }
            }
        }
        Sale newSale = new Sale();
        //displayUser.getSaleList().add(newSale);
        return ok(sale.render(displayUser, newSale));
    }

    public Result createSale() {
        Logger.debug("createSale called!");
        Form<SaleFormData> saleForm = formFactory.form(SaleFormData.class).bindFromRequest();
        SaleFormData newSale = saleForm.get();
        String user = session("connected");
        User thisUser;
        List<User> listUsers = new Model.Finder(User.class).all();
        thisUser = listUsers.get(listUsers.indexOf(new User(user, null)));
        thisUser.getSaleList().get(0).setName(newSale.name);
        thisUser.getSaleList().get(0).setLocation(newSale.location);
        return ok(views.html.sale.render(displayUser, thisUser.getSaleList().get(0)));
    }

    public Result getSale() {
        Logger.debug("getSale called!");
        Form<ProfileFormData> profileForm = formFactory.form(ProfileFormData.class).bindFromRequest();
        String user = session("connected");
        ProfileFormData updatedUser = profileForm.get();
        User thisUser;
        if (user!= null) {
            List<User> listUsers = new Model.Finder(User.class).all();
            thisUser = listUsers.get(listUsers.indexOf(new User(user, null)));
            List<Sale> thisUserSaleList = thisUser.getSaleList();
//            if (thisUserSaleList != null) {
//                //return ok(salelist.render(thisUserSaleList));
//                return ok(sale.render(thisUser, thisUserSaleList.get(0)));
//            }
        }
        return ok(index.render());
    }
}
