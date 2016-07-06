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
public class Role extends Model {
    @Id
    protected int id;
    @ManyToOne
    protected User user;
    @ManyToOne
    protected Sale sale;
    public enum RoleEnum {
        saledmin,
        seller,
        cashier,
        clerk,
        bookkeeper
    };
    protected RoleEnum role;

    public Role() {
    }

    public Role(User user, Sale sale, RoleEnum role) {
        this.user = user;
        this.sale = sale;
        this.role = role;
    }

    public static Finder<Integer, Role> find
            = new Finder<>(Role.class);

    public int getId() {
        return id;
    }

    public User getUser() {
        return this.user;
    }

    public Sale getSale() {
        return this.sale;
    }

    public int getUserId() {
        return this.user.getId();
    }

    public int getSaleId() {
        return this.sale.getId();
    }

    public RoleEnum getRole() {
        return role;
    }

//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public void setSaleId(int saleId) {
//        this.saleId = saleId;
//    }

//    public void setRole(RoleEnum role) {
//        this.role = role;
//    }

    public static List<Integer> mapRolesToSaleIds(List<Role> roles) {
        return roles.stream().map(role -> role.getSaleId()).collect(Collectors.toList());
    }

    public static List<Integer> mapRolesToUserIds(List<Role> roles) {
        return roles.stream().map(role -> role.getUserId()).collect(Collectors.toList());
    }

    public static List<Role> fetchByUserId(int userId) {
        List<Role> roles = Role.find.select("*").where().eq("user_id", userId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

    public static List<Role> fetchBySaleId(int saleId) {
        List<Role> roles = Role.find.select("*").where().eq("sale_id", saleId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

    public static List<Role> fetchByUserIdForARole(int userId, RoleEnum roleEnum) {
        List<Role> roles = fetchByUserId(userId);

        return filterRoles(roles, roleEnum);
    }

    public static List<Role> fetchBySaleIdForARole(int saleId, RoleEnum roleEnum) {
        List<Role> roles = fetchBySaleId(saleId);

        return filterRoles(roles, roleEnum);
    }

    public static List<User> fetchUsersBySaleIdForARole(int saleId, RoleEnum roleEnum) {
        List<Role> roles = Role.find.select("*").where().eq("sale_id", saleId).findList();
        List<User> users = new LinkedList<>();

        for (Role role : roles) {
            if (role.getRole() == roleEnum)
                users.add(User.fetchById(role.getUserId()));
        }
        return users;
    }

    public static List<Role> filterRoles(List<Role> roles, RoleEnum roleEnum) {
        List<Role> rolesFiltered;

        rolesFiltered = roles.stream().filter(role -> role.getRole() == roleEnum).collect(Collectors.toList());
        return rolesFiltered;
    }

    public static List<Role> fetchAllRoles() {
        List<Role> roles = Role.find.select("*").findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }
}
