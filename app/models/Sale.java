package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.format.Formats.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 6/16/2016.
 */

@Entity
public class Sale extends Model{
    @Id
    public int saleID;
    public String name;
    public String location;
    public double earnings;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    public HashMap<Integer, Integer> inventory;

    public static Finder<Integer, Sale> find
            = new Model.Finder<>(Integer.class, Sale.class);

    public Sale() {
        inventory = new HashMap<>();
    }

    public Sale(String creator) {
        this();
    }

    public Sale(String name, String location) {
        this.name = name;
        this.location = location;
    }

    //public void addUser(User user) {
       // user.add(user);
    //}

    public static Sale makeInstance(SaleFormData saleFormData) {
        Sale sale = new Sale();
        sale.saleID = saleFormData.saleID;
        sale.name = saleFormData.name;
        sale.location = saleFormData.location;
        //sale.user = saleFormData.user;
        sale.inventory = new HashMap<>();
        return sale;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
