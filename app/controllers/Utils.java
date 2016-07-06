package controllers;

import models.User;

import static play.mvc.Controller.session;

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
}
