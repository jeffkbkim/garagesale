package controllers;

import com.google.inject.Inject;
import models.LoginFormData;
import models.User;
import models.UserFormData;
import play.data.FormFactory;
import play.mvc.*;
import play.api.*;
import play.api.data.*;
import play.api.data.Forms;
import play.data.Form;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application.
 */
public class Application extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }

    public Result home() {
        return ok(login.render());
    }

    public Result loginAttempt() {
        Form<LoginFormData> loginForm = formFactory.form(LoginFormData.class).bindFromRequest();
        String attemptUser = loginForm.get().username;
        String attemptPass = loginForm.get().password;
        if (attemptUser.equals("user") && attemptPass.equals("password")) {
            return ok(index.render());
        }
        return ok(login.render());
    }



    public Result register() {
        Form<UserFormData> userForm = formFactory.form(UserFormData.class).bindFromRequest();
        User user = User.makeInstance(userForm.get());
        session("connected", user.getEmail());
        return ok(index.render());
    }

    public Result logout() {
        session().clear();
        flash("success", "You have logged out.");
        return redirect (routes.Application.login());
    }

}
