package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 6/16/2016.
 */

@Entity
public class Item extends Model{
    @Id
    protected int id;
    protected String name;
    protected String description;
    protected int quantity;
    protected double price;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    protected Sale sale;

    public static Finder<Integer, Item> find
            = new Finder<>(Item.class);

    public Item() {}
    public Item(String name, String description, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public Sale getSale() {
        return this.sale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setAllFields(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public static List<Item> fetchItemsBySale(Sale sale) {
        //TODO: need to handle empty list/null
        List<Item> items = Item.find.select("*").where().eq("sale_id", sale.getId()).findList();
        return items;
    }

    public static Item fetchItemById(int id) {
        //TODO: need to handle empty list/null
        Item item = find.byId(id);
        return item;
    }
}
