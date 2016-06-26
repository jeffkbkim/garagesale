package controllers;

import play.mvc.*;
import play.api.*;
import play.api.data.*;
import play.Logger;

import views.html.*;

/**
 * This controller contains an action to handle HTTP (home and logout) requests
 * to the application.
 */
public class Application extends Controller {


    /**
     * this method renders login scala template.
     * @return login page.
     */
    public Result index() {
        return ok(index.render());
    }

    public Result home() {
        Logger.debug("home called");
        if (session("connected") == null) {
            return ok(login.render());
        }
        return ok(login.render());
    }

    /**
     * clears user session and redirects to home page.
     */
    public Result logout() {
        Logger.debug("Logout called");
        session().remove("connected");
        session().clear();
        flash("success", "You have logged out.");
        return redirect (routes.Application.home());
    }

}
