package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.format.Formats.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    public DateTime dateTime;
    public double earnings;
    public HashMap<String, Integer> userList;
    public List<Item> inventory;
    public Sale() {
        inventory = new ArrayList<Item>();
        userList = new HashMap();
    }
    public Sale(String creator) {
        this();
        userList.put(creator, 0);
    }
    public void addItems(List<Item> itemList) {
        for(Item i : itemList) {
            inventory.add(i);
        }
    }
    public void addUser(String username, int role) {
        userList.put(username, role);
    }

    public static Sale makeInstance(SaleFormData saleFormData) {
        Sale sale = new Sale();
        sale.saleID = saleFormData.saleID;
        sale.name = saleFormData.name;
        sale.location = saleFormData.location;
        sale.dateTime = saleFormData.dateTime;
        sale.userList = saleFormData.userList;
        sale.inventory = saleFormData.inventory;
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
