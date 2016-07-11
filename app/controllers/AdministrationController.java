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
 * This controller contains an action to handle administration requests
 * to the application.
 */
public class AdministrationController extends Controller {


    /**
     * this method renders administration list
     *
     * @return administration list
     */
    public Result administration() {
        Logger.debug("administration called!");
        User user = Utils.getUserSession();
        List<User> allUsers = User.fetchAllUsers();
        return ok(administration.render(allUsers, user));
    }

    /**
     * locks user
     * @param userId id of the user
     * @return administration page with locked user updated
     */
    public Result lock(int userId) {
        Logger.debug("lock called!");
        User user = User.fetchById(userId);
        user.setIsLocked(true);
        user.save();
        return administration();
    }

    /**
     * unlocks user
     * @param userId id of the user
     * @return administration page with unlocked user updated
     */
    public Result unlock(int userId) {
        Logger.debug("unlock called!");
        User user = User.fetchById(userId);
        user.setIsLocked(false);
        user.save();
        return administration();
    }

}