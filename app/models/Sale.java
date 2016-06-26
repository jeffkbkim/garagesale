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
    protected int saleID;
    protected String name;
    protected String location;
    protected double earnings;
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    public static Finder<Integer, Sale> find
            = new Finder<>(Sale.class);

    public Sale() {
    }

    public Sale(String creator) {
        this();
    }

    public Sale(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public static Sale makeInstance(SaleFormData saleFormData) {
        Sale sale = new Sale();
        sale.saleID = saleFormData.saleID;
        sale.name = saleFormData.name;
        sale.location = saleFormData.location;
        return sale;
    }


    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
