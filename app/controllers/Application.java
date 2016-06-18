package controllers;

import play.mvc.*;
import play.api.*;
import play.api.data.*;
import play.Logger;
import models.User;
import java.util.List;
import com.avaje.ebean.Model;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application.
 */
public class Application extends Controller {



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

    public Result administration() {
        List<User> listUsers = new Model.Finder(User.class).all();
        return ok(administration.render(listUsers));
    }

    public Result logout() {
        Logger.debug("Logout called");
        session().remove("connected");
        session().clear();
        flash("success", "You have logged out.");
        return redirect (routes.Application.home());
    }

}
