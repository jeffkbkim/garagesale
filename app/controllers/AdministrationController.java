package controllers;

import models.Role;
import models.Sale;
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

    public Result saleAdministration(int saleId) {
        Logger.debug("administration called!");
        Sale sale = Sale.fetchById(saleId);
        User user = Utils.getUserSession();
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

        List<User> allUsers =  User.fetchByIds(userIdsForSaleadmins);
        allUsers.addAll(User.fetchByIds(userIdsForSellers));
        allUsers.addAll(User.fetchByIds(userIdsForCashiers));
        allUsers.addAll(User.fetchByIds(userIdsForClerks));
        allUsers.addAll(User.fetchByIds(userIdsForBookkeepers));

        return ok(saleAdministration.render(allUsers, user, sale));
    }

    /**
     * locks user
     * @param userId id of the user
     * @return administration page with locked user updated
     */
    public Result lock(int userId) {
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
        User user = User.fetchById(userId);
        user.setIsLocked(false);
        user.save();
        return administration();
    }

}