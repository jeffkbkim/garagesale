package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 6/16/2016.
 * Item Entity.
 */

@Entity
public class Item extends Model{
    @Id
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
    @OneToMany(mappedBy = "item")
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * creates Finder for Item Entity.
     */
    private static Finder<Integer, Item> find
            = new Finder<>(Item.class);


    /**
     * Item no arg constructor
     */
    public Item() {}

    /**
     * Initializes Item with given name, description, quantity, and price.
     *
     * @param name item name
     * @param description item description
     * @param quantity item quantity
     * @param price item price
     */
    public Item(String name, String description, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    /**
     * Item Id getter method.
     * @return item id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Item name getter method.
     * @return item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Item description getter method.
     * @return item description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Item quantity getter method.
     * @return item quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Item price getter method.
     * @return item price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sale (the Item is in) getter method.
     * @return Sale of item
     */
    public Sale getSale() {
        return this.sale;
    }

    /**
     * Item id setter method.
     * @param id item id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Item name setter method.
     * @param name id name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Item quantity setter method.
     * @param quantity item quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Item price setter method.
     * @param price item price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Item Sale setter method.
     * @param sale Item sale
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     * Item all fields setter method.
     * @param name item name
     * @param description item description
     * @param quantity item quantity
     * @param price item price
     */
    public void setAllFields(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * fetches item by sale.
     * @param sale Sale
     * @return list of items in sale.
     */
    public static List<Item> fetchItemsBySale(Sale sale) {
        //TODO: need to handle empty list/null
        return Item.find.select("*").where().eq("sale_id", sale.getId()).findList();
    }

    /**
     * fetches item by id.
     * @param id item id
     * @return Item
     */
    public static Item fetchItemById(int id) {
        //TODO: need to handle empty list/null
        return find.byId(id);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static Finder<Integer, Item> getFind() {
        return find;
    }

    public static void setFind(Finder<Integer, Item> find) {
        Item.find = find;
    }
}
