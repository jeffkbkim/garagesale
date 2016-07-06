package controllers;

import com.avaje.ebean.Model;
import com.google.inject.Inject;
import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.data.format.Formats;
import play.mvc.Controller;
import play.mvc.Result;
import scala.collection.JavaConverters;
import views.html.index;
import views.html.login;
import views.html.profile;
import views.html.sale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * Created by Jeff on 6/16/16.
 * This controller handles HTTP requests related to sales.
 */
public class SalesController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result renderSaleRolesPage(int saleId) {
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);

        List<Role> rolesForThisSale = Role.fetchBySaleId(sale.getId());

        List<Role> rolesForSaleadmins = Role.filterRoles(rolesForThisSale, Role.RoleEnum.saledmin);
        List<Role> rolesForSellers = Role.filterRoles(rolesForThisSale, Role.RoleEnum.seller);
        List<Role> rolesForCashiers = Role.filterRoles(rolesForThisSale, Role.RoleEnum.cashier);
        List<Role> rolesForClerks = Role.filterRoles(rolesForThisSale, Role.RoleEnum.clerk);
        List<Role> rolesForBookkeepers = Role.filterRoles(rolesForThisSale, Role.RoleEnum.bookkeeper);

        List<Integer> userIdsForSaleadmins
                = Role.mapRolesToUserIds(rolesForSaleadmins);
        List<Integer> userIdsForSellers
                = Role.mapRolesToUserIds(rolesForSellers);
        List<Integer> userIdsForCashiers
                = Role.mapRolesToUserIds(rolesForCashiers);
        List<Integer> userIdsForClerks
                = Role.mapRolesToUserIds(rolesForClerks);
        List<Integer> userIdsForBookkeepers
                = Role.mapRolesToUserIds(rolesForBookkeepers);

        List<User> saleadmins = User.fetchByIds(userIdsForSaleadmins);
        List<User> sellers = User.fetchByIds(userIdsForSellers);
        List<User> cashiers = User.fetchByIds(userIdsForCashiers);
        List<User> clerks = User.fetchByIds(userIdsForClerks);
        List<User> bookkeepers = User.fetchByIds(userIdsForBookkeepers);

        return ok(views.html.saleroles.render(user, sale, saleadmins, sellers, cashiers, clerks, bookkeepers));
    }

    public Result renderAddUserRolePage(int saleId) {
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);
        List<User> allUsers = User.fetchAllUsers();

        return ok(views.html.adduserrole.render(user, sale, allUsers));
    }


    /**
     * Saves new sale to database.
     * @return sale page with added sale.
     */
    public Result createSale() {
        Form<SaleFormData> saleForm = formFactory.form(SaleFormData.class).bindFromRequest();

        if (saleForm != null) {
            SaleFormData newSale = saleForm.get();
            User user = Utils.getUserSession();
            Sale sale = new Sale(newSale.name, newSale.location);

            sale.save();

            // Note that role is added after sale is saved, so that id is first generated
            addRoleToDB(user, sale, Role.RoleEnum.saledmin);
        }
        return redirect(routes.SalesController.getSales());
    }

    /**
     * add user role into a sale
     * @return the sale role page
     */
    public Result addRole() {
        Form<SaleRoleFormData> saleRoleForm = formFactory.form(SaleRoleFormData.class).bindFromRequest();
        User user = Utils.getUserSession();
        Sale sale;
        User userAssign;

        SaleRoleFormData saleRoleFormData = saleRoleForm.get();
        userAssign = User.fetchByUsername(saleRoleFormData.username);
        sale = Sale.fetchById(saleRoleFormData.saleId);

        addRoleToDB(userAssign, sale, saleRoleFormData.role);

        return redirect(routes.SalesController.renderSaleRolesPage(sale.getId()));
    }

    private boolean isRoleInDB(User user, Sale sale) {
        List<Role> roles = Role.fetchBySaleId(sale.getId());
        roles = roles.stream().filter(role -> role.getUserId() == user.getId()).collect(Collectors.toList());
        return roles.size() > 0;
    }

    private void addRoleToDB(User user, Sale sale, Role.RoleEnum role) {
        Role newRole = new Role(user, sale, role);
        if (!isRoleInDB(user, sale))
            newRole.save();
    }

    private boolean addRoleToDB(User user, Sale sale, String role) {
        Role newRole = new Role();
        Role.RoleEnum roleEnum;
        switch(role) {
            case "saleadmin":
                roleEnum = Role.RoleEnum.saledmin;
                break;
            case "seller":
                roleEnum = Role.RoleEnum.seller;
                break;
            case "cashier":
                roleEnum = Role.RoleEnum.cashier;
                break;
            case "clerk":
                roleEnum = Role.RoleEnum.clerk;
                break;
            case "bookkeeper":
                roleEnum = Role.RoleEnum.bookkeeper;
                break;
            default:
                return false;
        }
        addRoleToDB(user, sale, roleEnum);
        return true;
    }

    /**
     * renders create sale page.
     * @return create sale page with user session.
     */
    public Result createSalePage() {
        User user = Utils.getUserSession();
        return ok(views.html.createsale.render(user));
    }

    /**
     *
     * @return current sales of user.
     */
    public Result getSales() {
        User user = Utils.getUserSession();
        if (user == null) {
            return redirect("/");
        }
        List<Role> saleRoles = Role.fetchByUserId(user.getId());

        List<Sale> sales = Sale.fetchBySaleIds(Role.mapRolesToSaleIds(saleRoles));

        return ok(views.html.sale.render(user, sales));
    }

    /**
     * Controller for all sales page
     * @return all sales page
     */
    public Result allSales() {
        User user = Utils.getUserSession();
        List<Sale> allSales = Sale.fetchAllSales();
        return ok(views.html.sale.render(user, allSales));
    }
}
