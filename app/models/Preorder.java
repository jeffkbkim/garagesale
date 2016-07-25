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
public final class Preorder extends Model {
    @Id
    protected int id;
    @ManyToOne
    protected User user;
    @ManyToOne
    protected Item item;

    public Preorder() {
    }

    public Preorder(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    public static final Finder<Integer, Preorder> find
            = new Finder<>(Preorder.class);

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
     * get Item of the role
     * @return Item
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * get user id for the role
     * @return user id
     */
    public int getUserId() {
        return this.user.getId();
    }

    /**
     * get Item id for the role
     * @return Item id
     */
    public int getItemId() {
        return this.item.getId();
    }
    
    /**
     * map a list of roles to a list of Item id
     * @param roles list of roles to be mapped
     * @return list of Item id
     */
    public static List<Integer> mapRolesToItemIds(List<Preorder> roles) {
        return roles.stream().map(role -> role.getItemId()).collect(Collectors.toList());
    }

    /**
     * map a list of roles to a list of use id
     * @param roles list of roles to be mapped
     * @return list of user id
     */
    public static List<Integer> mapRolesToUserIds(List<Preorder> roles) {
        return roles.stream().map(role -> role.getUserId()).collect(Collectors.toList());
    }

    /**
     * database call to select a list of roles for a user id
     * @param userId relevant user id
     * @return list of roles
     */
    public static List<Preorder> fetchByUserId(int userId) {
        List<Preorder> roles = Preorder.find.select("*").where().eq("user_id", userId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

    /**
     * database call to select a list of roles for a Item id
     * @param ItemId relevant Item id
     * @return list of roles
     */
    public static List<Preorder> fetchByItemId(int ItemId) {
        List<Preorder> roles = Preorder.find.select("*").where().eq("Item_id", ItemId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

    public static String fetchByItemIdString(int ItemId) {
        List<Preorder> roles = Preorder.find.select("*").where().eq("Item_id", ItemId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        String returnString = "";
        for (int i = 0; i < roles.size(); i++) {
            returnString += roles.get(i).getUser().getUserName();
            if (i != roles.size() - 1) {
                returnString += ", ";
            }
        }
        return returnString;
    }

}
