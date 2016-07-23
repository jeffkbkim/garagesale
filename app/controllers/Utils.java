package controllers;

import models.Sale;
import models.User;
import play.mvc.Result;
import views.html.login;

import static play.mvc.Controller.session;
import static play.mvc.Results.ok;

/**
 * Created by yudawinata on 6/26/16.
 */
public class Utils {
    /**
     * Get user session, null otherwise
     *
     * @return user session
     */
    public static User getUserSession() {
        String username = session("connected");
        return User.fetchByUsername(username);
    }

    public static String currToString(double curr) {
        return String.format("%.2f", curr);
    }

    /**
     * Get username
     *
     * @return username
     */
    public static String getUsername() {
        String username = session("connected");
        return username;
    }


    /**
     * Get sale id
     *
     * @return sale id
     */
    public static int getSaleId() {
        String currentSale = session("currentSale");
        if (currentSale == null) {
            currentSale = "-1";
        }
        return Integer.parseInt(currentSale);
    }

    /**
     * Get sale name
     *
     * @return sale name
     */
    public static String getSaleName() {
        String currentSale = session("currentSale");
        String saleName;
        if (currentSale == null) {
            saleName = "No Selected Sale";
        } else {
            Sale sale = Sale.fetchById(Integer.parseInt(currentSale));
            saleName = sale.getName();
        }
        return saleName;
    }

    /**
     * creates a superuser
     * @return login page with superuser
     */
    public Result createSuperUser() {
        User user = new User("cs2340", "superuser");
        user.setIsSuperUser(true);
        user.save();
        return ok(login.render(""));
    }
}
