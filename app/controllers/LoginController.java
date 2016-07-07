package controllers;

import com.avaje.ebean.Model;
import com.google.inject.Inject;
import models.LoginFormData;
import models.User;
import models.UserFormData;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.*;
import play.Logger;

import java.util.List;

/**
 * Created by Douglas on 6/10/16.
 * This controller is used to handle HTTP requests regarding login and register.
 */

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;

    /**
     * validates login attempt.
     * @return home page if incorrect username or password, index page otherwise.
     */
    public Result loginAttempt() {
        Form<LoginFormData> loginForm = formFactory.form(LoginFormData.class).bindFromRequest();
        String attemptUser = loginForm.get().username;
        String attemptPass = loginForm.get().password;
        User testUser = new User(attemptUser, attemptPass);
        List<User> userList = new Model.Finder(User.class).all();
        int userIndex = userList.indexOf(testUser);
        if (userIndex > -1) {
            if (userList.get(userIndex).checkPassword(attemptPass)) {
                session("connected", userList.get(userIndex).getUserName());
                return redirect(routes.SalesController.allSales());
            }
        }
        if (attemptUser.equals("user") && attemptPass.equals("pass")) {
            return ok(index.render());
        } else {
            return unauthorized(login.render());
        }
    }

    /**
     * adds new user to database.
     * @return login page.
     */
    public Result register() {
        Form<UserFormData> userForm = formFactory.form(UserFormData.class).bindFromRequest();
        User user = User.makeInstance(userForm.get());
        user.save();
        return ok(login.render());
    }
}