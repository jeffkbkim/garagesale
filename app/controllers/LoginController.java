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

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result loginAttempt() {
        Logger.debug("Login attempt called");
        Form<LoginFormData> loginForm = formFactory.form(LoginFormData.class).bindFromRequest();
        String attemptUser = loginForm.get().username;
        String attemptPass = loginForm.get().password;
        User testUser = new User(attemptUser, attemptPass);
        List<User> userList = new Model.Finder(User.class).all();
        int userIndex = userList.indexOf(testUser);
        if (userIndex > -1) {
            if (userList.get(userIndex).checkPassword(attemptPass)) {
                session("connected", userList.get(userIndex).getUserName());
                return ok(index.render());
            }
        }
        if (attemptUser.equals("user") && attemptPass.equals("pass")) {
            return ok(index.render());
        } else {
            return unauthorized(login.render(true));
        }
    }

    public Result register() {
        Logger.debug("Register called");
        Form<UserFormData> userForm = formFactory.form(UserFormData.class).bindFromRequest();
        User user = User.makeInstance(userForm.get());
        user.save();
        Logger.debug("User created: " + user.getUserName());
        return ok(index.render());
    }
}