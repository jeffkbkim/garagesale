package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.format.Formats.DateTime;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Douglas on 6/16/2016.
 * Sale Entity
 */

@Entity
public class Sale extends Model {
    @Id
    protected int id;
    protected String name;
    protected String location;
    protected double earnings;
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;
    @OneToMany(mappedBy = "sale")
    protected ArrayList<Transaction> transactions = new ArrayList<>();
    @OneToMany(mappedBy = "sale")
    protected ArrayList<Item> items = new ArrayList<>();
    @OneToMany(mappedBy = "sale")
    protected ArrayList<Receipt> receipts = new ArrayList<>();
    @OneToMany(mappedBy = "sale")
    protected ArrayList<Role> roles = new ArrayList<>();

    /**
     * creates Finder for Sale Entity.
     */
    public static Finder<Integer, Sale> find
            = new Finder<>(Sale.class);

    /**
     * Sale no-arg constructor
     */
    public Sale() {
    }

    /**
     * Creates Sale with given parameters
     *
     * @param name
     * @param location
     */
    public Sale(String name, String location) {
        this.name = name;
        this.location = location;
    }

    /**
     * Creates an Sale instance from Sale Form Data.
     *
     * @param saleFormData SaleFormData
     * @return Sale instance
     */
    public static Sale makeInstance(SaleFormData saleFormData) {
        Sale sale = new Sale();
        sale.id = saleFormData.saleID;
        sale.name = saleFormData.name;
        sale.location = saleFormData.location;
        return sale;
    }

    /**
     * sale id getter method
     *
     * @return sale id
     */
    public int getId() {
        return this.id;
    }

    /**
     * sale name getter method
     *
     * @return sale name
     */
    public String getName() {
        return this.name;
    }

    /**
     * sale location getter method
     *
     * @return sale location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * sale id setter method
     *
     * @param id sale id
     */
    public void setSaleID(int id) {
        this.id = id;
    }

    /**
     * sale user setter method
     *
     * @param user sale user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * sale name setter method
     *
     * @param name sale name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sale location setter method
     *
     * @param location sale location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * fetches Sale with sale id
     *
     * @param id sale id
     * @return Sale corresponding to sale id
     */
    public static Sale fetchById(int id) {
        Sale sale = find.byId(id);
        return sale;
    }

    public static List<Sale> fetchBySaleIds(List<Integer> ids) {
        List<Sale> sales = new LinkedList<>();
        Sale sale;
        for (Integer id : ids) {
            sale = Sale.find.byId(id);
            if (sale != null)
                sales.add(sale);
        }
        return sales;
    }

    /**
     * fetch all available sales
     *
     * @return all sales
     */
    public static List<Sale> fetchAllSales() {
        List<Sale> sales = Sale.find.select("*").findList();
        if (sales == null)
            sales = new ArrayList<>();
        return sales;
    }

    /**
     * fetches Sale with user
     *
     * @param user Sale user
     * @return list of all Sales user is involved in.
     */
    public static List<Sale> fetchByUser(User user) {
        List<Sale> sales = Sale.find.select("*").where().eq("user_id", user.getId()).findList();
        if (sales == null)
            sales = new ArrayList<>();
        return sales;
    }
}
