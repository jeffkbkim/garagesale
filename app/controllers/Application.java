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
import play.data.DynamicForm;

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
<<<<<<< HEAD
        if (session("connected") == null) {
            return ok(login.render());
=======
        return ok(login.render());
    }

    public Result loginAttempt() {
        Form<LoginFormData> loginForm = formFactory.form(LoginFormData.class).bindFromRequest();
        String attemptUser = loginForm.get().username;
        String attemptPass = loginForm.get().password;
        if (attemptUser.equals("user") && attemptPass.equals("pass")) {
            return ok(index.render());
>>>>>>> f03ea3c58bf8028afafab278ca76bb111e595e8d
        }
        return ok(index.render());
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
        return redirect (routes.Application.home());
    }

}
