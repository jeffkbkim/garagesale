package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yudawinata on 7/6/16.
 */
@Entity
public final class Role extends Model {
    @Id
    protected int id;
    @ManyToOne
    protected User user;
    @ManyToOne
    protected Sale sale;
    public enum RoleEnum {
        saledmin("Sale Administrator"),
        seller("Seller"),
        cashier("Cashier"),
        clerk("Clerk"),
        bookkeeper("Bookkeeper");

        private String value;

        RoleEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    };
    protected RoleEnum role;

    public Role() {
    }

    public Role(User user, Sale sale, RoleEnum role) {
        this.user = user;
        this.sale = sale;
        this.role = role;
    }

    public static final Finder<Integer, Role> find
            = new Finder<>(Role.class);

    /**
     * get role id
     * @return role id
     */
    public int getId() {
        return id;
    }

    /**
     * get user of the role
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * get sale of the role
     * @return sale
     */
    public Sale getSale() {
        return this.sale;
    }

    /**
     * get user id for the role
     * @return user id
     */
    public int getUserId() {
        return this.user.getId();
    }

    /**
     * get sale id for the role
     * @return sale id
     */
    public int getSaleId() {
        return this.sale.getId();
    }

    /**
     * get role type of the role
     * @return role type
     */
    public RoleEnum getRole() {
        return role;
    }

    /**
     * map a list of roles to a list of sale id
     * @param roles list of roles to be mapped
     * @return list of sale id
     */
    public static List<Integer> mapRolesToSaleIds(List<Role> roles) {
        return roles.stream().map(role -> role.getSaleId()).collect(Collectors.toList());
    }

    /**
     * map a list of roles to a list of use id
     * @param roles list of roles to be mapped
     * @return list of user id
     */
    public static List<Integer> mapRolesToUserIds(List<Role> roles) {
        return roles.stream().map(role -> role.getUserId()).collect(Collectors.toList());
    }

    /**
     * database call to select a list of roles for a user id
     * @param userId relevant user id
     * @return list of roles
     */
    public static List<Role> fetchByUserId(int userId) {
        List<Role> roles = Role.find.select("*").where().eq("user_id", userId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

    /**
     * database call to select a list of roles for a sale id
     * @param saleId relevant sale id
     * @return list of roles
     */
    public static List<Role> fetchBySaleId(int saleId) {
        List<Role> roles = Role.find.select("*").where().eq("sale_id", saleId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

    /**
     * database call to select a list of roles for a sale id and user id
     * @param saleId relevant sale id
     * @param userId relevant user id
     * @return list of roles
     */
    public static List<Role> fetchBySaleIdAndUserId(int saleId, int userId) {
        List<Role> roles = fetchBySaleId(saleId);
        List<Role> rolesFilteredByUser
                = roles.stream().filter(role -> role.getUserId() == userId).collect(Collectors.toList());
        return rolesFilteredByUser;
    }

    /**
     * database call to select roles for a specific user, with a specific role type
     * @param userId relevant user id
     * @param roleEnum role type
     * @return list of roles
     */
    public static List<Role> fetchByUserIdForARole(int userId, RoleEnum roleEnum) {
        List<Role> roles = fetchByUserId(userId);

        return filterRoles(roles, roleEnum);
    }

    /**
     * database call to select roles for a specific user, with a specific role type
     * @param saleId relevant sale id
     * @param roleEnum role type
     * @return list of roles
     */
    public static List<Role> fetchBySaleIdForARole(int saleId, RoleEnum roleEnum) {
        List<Role> roles = fetchBySaleId(saleId);

        return filterRoles(roles, roleEnum);
    }

    /**
     * filters a list of roles to match a specific role type
     * @param roles list of roles to be filtered
     * @param roleEnum role type
     * @return filtered list of roles
     */
    public static List<Role> filterRoles(List<Role> roles, RoleEnum roleEnum) {
        List<Role> rolesFiltered;

        rolesFiltered = roles.stream().filter(role -> role.getRole() == roleEnum).collect(Collectors.toList());
        return rolesFiltered;
    }

    /**
     * get all roles
     * @return list of roles
     */
    public static List<Role> fetchAllRoles() {
        List<Role> roles = Role.find.select("*").findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }
}
