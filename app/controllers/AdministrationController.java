package controllers;

import models.User;
import play.mvc.*;
import play.api.*;
import play.api.data.*;
import play.Logger;

import views.html.*;
import views.html.administration;

import java.util.List;

/**
 * This controller contains an action to handle HTTP (home and logout) requests
 * to the application.
 */
public class AdministrationController extends Controller {


    /**
     * this method renders login scala template.
     *
     * @return login page.
     */
    public Result administration() {
        List<User> allUsers = User.fetchAllUsers();
        return ok(administration.render(allUsers));
    }

}