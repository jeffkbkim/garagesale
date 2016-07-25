package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yudawinata on 7/6/16.
 */
@Entity
public final class Bid extends Model {
    @Id
    protected int id;
    @ManyToOne
    protected User user;
    @ManyToOne
    protected Item item;
    protected double bidPrice;

    public Bid() {

    }

    public Bid(User user, Item item, double bidPrice) {
        this.user = user;
        this.item = item;
        this.bidPrice = bidPrice;
    }

    public static final Finder<Integer, Bid> find
            = new Finder<>(Bid.class);

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

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
    public static List<Integer> mapRolesToItemIds(List<Bid> roles) {
        return roles.stream().map(role -> role.getItemId()).collect(Collectors.toList());
    }

    /**
     * map a list of roles to a list of use id
     * @param roles list of roles to be mapped
     * @return list of user id
     */
    public static List<Integer> mapRolesToUserIds(List<Bid> roles) {
        return roles.stream().map(role -> role.getUserId()).collect(Collectors.toList());
    }

    /**
     * database call to select a list of roles for a user id
     * @param userId relevant user id
     * @return list of roles
     */
    public static List<Bid> fetchByUserId(int userId) {
        List<Bid> roles = Bid.find.select("*").where().eq("user_id", userId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**

     * database call to select a list of roles for a Item id
     * @param ItemId relevant Item id
     * @return list of roles
     */
    public static List<Bid> fetchByItemId(int ItemId) {
        List<Bid> roles = Bid.find.select("*").where().eq("Item_id", ItemId).findList();
        if (roles == null)
            roles = new LinkedList<>();
        return roles;
    }

}
