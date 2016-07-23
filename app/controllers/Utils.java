package controllers;

import models.Sale;
import models.User;
import play.mvc.Http;
import play.mvc.Result;
import views.html.login;

import java.io.File;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.request;
import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
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
        return User.fetchByUsername(username);
    }

    /**
     * Get username
     *
     * @return username
     */
    public static String getUsername() {
        String username = session("connected");
        return username;
    }


    /**
     * Get sale id
     *
     * @return sale id
     */
    public static int getSaleId() {
        String currentSale = session("currentSale");
        if (currentSale == null) {
            currentSale = "-1";
        }
        return Integer.parseInt(currentSale);
    }

    /**
     * Get sale name
     *
     * @return sale name
     */
    public static String getSaleName() {
        String currentSale = session("currentSale");
        String saleName;
        if (currentSale == null) {
            saleName = "No Selected Sale";
        } else {
            Sale sale = Sale.fetchById(Integer.parseInt(currentSale));
            saleName = sale.getName();
        }
        return saleName;
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

    public Result upload() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            file.renameTo(new File("/home/sunho207/Desktop/", fileName));
            return ok("File uploaded");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

}
