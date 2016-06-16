package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Douglas on 6/16/2016.
 */

@Entity
public class Item extends Model{
    @Id
    public int id;
    public String name;
    public double quantity;
    public double price;
    public String description;
    public Item() {}
    public Item(String name, double quantity, double price, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }
}
