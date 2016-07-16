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

import java.util.*;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * Created by Jeff on 6/16/16.
 * This controller handles HTTP requests related to sales.
 */
public class SalesController extends Controller {
    @Inject
    FormFactory formFactory;

    /**
     * render sale role page
     * @param saleId sale id
     * @return sale page
     */
    public Result renderSaleRolesPage(int saleId) {
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);

        List<Role> rolesForThisSale = Role.fetchBySaleId(sale.getId());

        List<Role> rolesForSaleadmins = Role.filterRoles(rolesForThisSale, Role.RoleEnum.saleAdmin);
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

    /**
     * render add user role page
     * @param saleId sale id
     * @return user role page
     */
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
            Sale sale = new Sale(newSale.getName(), newSale.getLocation());

            sale.save();

            // Note that role is added after sale is saved, so that id is first generated
            addRoleToDB(user, sale, Role.RoleEnum.saleAdmin);
        }
        return redirect(routes.SalesController.getSales());
    }

    /**
     * close sale by sale id, user has to be a sale admin
     * @param saleId sale id
     * @return page redirect to list of sales
     */
    public Result closeSale(int saleId) {
        Sale sale = Sale.fetchById(saleId);
        User user = Utils.getUserSession();

        if (!isUserSaleAdmin(user, sale))
            return redirect(routes.SalesController.getSales());
        sale.setIsOpen(false);
        sale.save();
        return redirect(routes.SalesController.getSales());
    }

    /**
     * check if user is a sale admin for a specific sale
     * @param user
     * @param sale
     * @return
     */
    public static boolean isUserSaleAdmin(User user, Sale sale) {
        List<Role> roles = Role.fetchBySaleIdForARole(sale.getId(), Role.RoleEnum.saleAdmin);
        return Role.mapRolesToUserIds(roles).contains(user.getId());
    }


    /**
     * add user role into a sale
     * @return the sale role page
     */
    public Result addRole() {
        Form<SaleRoleFormData> saleRoleForm = formFactory.form(SaleRoleFormData.class).bindFromRequest();
        Sale sale;
        User userAssign;

        SaleRoleFormData saleRoleFormData = saleRoleForm.get();
        userAssign = User.fetchByUsername(saleRoleFormData.getUsername());
        sale = Sale.fetchById(saleRoleFormData.getSaleId());

        addRoleToDB(userAssign, sale, saleRoleFormData.getRole());

        return redirect(routes.SalesController.renderSaleRolesPage(sale.getId()));
    }

    /**
     * check if a user has a role in a specific sale
     * @param user relevant user
     * @param sale relevant sale
     * @return true if user has a role for the sale, false otherwise
     */
    private boolean isRoleInDB(User user, Sale sale) {
        List<Role> roles = Role.fetchBySaleId(sale.getId());
        roles = roles.stream()
                .filter(role -> role.getUserId() == user.getId())
                .collect(Collectors.toList());
        return roles.size() > 0;
    }

    /**
     * add a role to the database, will not add a duplicate
     * @param user relevant user
     * @param sale relevant sale
     * @param role role type
     */
    private void addRoleToDB(User user, Sale sale, Role.RoleEnum role) {
        Role newRole = new Role(user, sale, role);
        if (!isRoleInDB(user, sale))
            newRole.save();
    }

    /**
     * helper method to add role to a user for a sale
     * @param user relevant user
     * @param sale relevant sale
     * @param role role type in string
     * @return true if role type is valid, false otherwise
     */
    private boolean addRoleToDB(User user, Sale sale, String role) {
        Role.RoleEnum roleEnum;
        switch(role) {
            case "saleadmin":
                roleEnum = Role.RoleEnum.saleAdmin;
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
        List<Role> roles = Role.fetchByUserId(user.getId());
        Set<Integer> saleIds = new HashSet<>(Role.mapRolesToSaleIds(roles));
        List<Sale> salesNotManagedAndOpen
                = allSales.stream()
                .filter(sale -> sale.isOpen() && !saleIds.contains(sale.getId()))
                .collect(Collectors.toList());
        return ok(views.html.allsales.render(user, salesNotManagedAndOpen));
    }

}
