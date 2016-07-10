package controllers;

import models.User;
import play.mvc.Result;
import play.mvc.Controller;
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
        User user = User.fetchByUsername(username);
        return user;
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
