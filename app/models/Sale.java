package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Douglas on 6/16/2016.
 * Sale Entity
 */

@Entity
public final class Sale extends Model {
    @Id
    private int id;
    private String name;
    private String location;
    private String date;
    private double earnings;
    private boolean isOpen;
    private static final String SALE = "sale";
    @OneToMany(mappedBy = SALE)
    private List<Transaction> transactions = new ArrayList<>();
    @OneToMany(mappedBy = SALE)
    private List<Item> items = new ArrayList<>();
    @OneToMany(mappedBy = SALE)
    private List<Receipt> receipts = new ArrayList<>();
    @OneToMany(mappedBy = SALE)
    private List<Role> roles = new ArrayList<>();

    /**
     * creates Finder for Sale Entity.
     */
    private static Finder<Integer, Sale> find
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
        this.isOpen = true;
    }

    /**
     * Creates an Sale instance from Sale Form Data.
     *
     * @param saleFormData SaleFormData
     * @return Sale instance
     */
    public static Sale makeInstance(SaleFormData saleFormData) {
        Sale sale = new Sale();
        sale.id = saleFormData.getSaleID();
        sale.name = saleFormData.getName();
        sale.location = saleFormData.getLocation();
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
     * @return sale location
     */
    public double getEarnings() {
        return this.earnings;
    }

    /**
     * add earning
     * @param increment amount of earning to be added
     */
    public void addEarnings(double increment) {
        this.earnings += increment;
    }

    /**
     * setter for searning
     * @param earnings earning value
     */
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    /**
     * getter for location
     * @return location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * getter for location
     * @return location
     */
    public String getDate() {
        return this.date;
    }

    /**
     * check if sale is open
     * @return true if sale is open, false otherwise
     */
    public boolean isOpen() {
        return this.isOpen;
    }

    public String getStatus() {
        if (isOpen) {
            return "Open";
        }
        return "Closed";
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
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
        return find.byId(id);
    }

    /**
     * database call to select a list of sales by a list of sale id
     * @param ids list of sale ids
     * @return list of sales
     */
    public static List<Sale> fetchBySaleIds(List<Integer> ids) {
        List<Sale> sales = new LinkedList<>();
        Sale sale;
        for (Integer id : ids) {
            sale = Sale.find.byId(id);
            if (sale != null) {
                sales.add(sale);
            }

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
        if (sales == null) {
            sales = new ArrayList<>();
        }
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
        if (sales == null) {
            sales = new ArrayList<>();
        }
        return sales;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public static Finder<Integer, Sale> getFind() {
        return find;
    }

    public static void setFind(Finder<Integer, Sale> find) {
        Sale.find = find;
    }
}
