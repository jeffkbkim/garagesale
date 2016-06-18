package controllers;

import com.avaje.ebean.Model;
import com.google.inject.Inject;
import models.ProfileFormData;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import views.html.*;

import play.Logger;

public class AdministrationController extends Controller {

    @Inject
    FormFactory formFactory;

}
