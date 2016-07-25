package controllers;

import com.google.inject.Inject;
import models.*;
import play.Logger;
import play.api.libs.mailer.Email;
import play.api.libs.mailer.MailerClient;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.request;
import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

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

    public static String currToString(double curr) {
        return String.format("%.2f", curr);
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
        Logger.error("Error: upload called");
        Logger.debug("Debug: upload called");
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        int itemId = Integer.parseInt(request().body().asMultipartFormData().asFormUrlEncoded().get("itemId")[0]);
        int saleId = Integer.parseInt(request().body().asMultipartFormData().asFormUrlEncoded().get("saleId")[0]);
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            String extension = ".jpg";
            File file = picture.getFile();
            try {
                ImageInputStream inputStream = ImageIO.createImageInputStream(file);
                ImageReader reader = ImageIO.getImageReaders(inputStream).next();
                if (reader != null) {
                    String formatName = reader.getFormatName();
                    switch (formatName) {
                        case "JPEG":
                            extension = ".jpg";
                            break;
                        case "PNG":
                            extension = ".png";
                            break;
                        case "GIF":
                            extension = ".gif";
                            break;
                        default:
                    }
                }
            } catch (IOException e) {
                Logger.debug("IOException");
            }
            String newFilename = saleId + "_" + itemId + extension;
            Logger.debug(newFilename);
            try {
                Files.copy(file.toPath(), (new File("public/data/", newFilename)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                Item item = Item.fetchItemById(itemId);
                item.setImageName(newFilename);
                item.update();
            } catch (FileNotFoundException e) {
                Logger.error("The file was not found");
            } catch (IOException e) {
                Logger.error("IO Exception");
            }
            return redirect(routes.CatalogController.renderCatalogPage(saleId));
        } else {
            flash("error", "Missing file");
            return badRequest();
        }

    }

    public Result uploadProfile() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        User user = getUserSession();
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            String extension = ".jpg";
            File file = picture.getFile();
            try {
                ImageInputStream inputStream = ImageIO.createImageInputStream(file);
                ImageReader reader = ImageIO.getImageReaders(inputStream).next();
                if (reader != null) {
                    String formatName = reader.getFormatName();
                    switch (formatName) {
                        case "JPEG":
                            extension = ".jpg";
                            break;
                        case "PNG":
                            extension = ".png";
                            break;
                        case "GIF":
                            extension = ".gif";
                            break;
                        default:
                    }
                }
            } catch (IOException e) {
                Logger.debug("IOException");
            }
            String newFilename = user.getId() + extension;
            Logger.debug(newFilename);
            try {
                Files.copy(file.toPath(), (new File("public/data/user", newFilename)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                user.setImageName(newFilename);
                user.update();
            } catch (FileNotFoundException e) {
                Logger.error("The file was not found");
            } catch (IOException e) {
                Logger.error("IO Exception");
            }
            return redirect(routes.ProfileController.view());
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

}
