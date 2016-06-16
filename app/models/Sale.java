package models;

import com.avaje.ebean.Model;

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
    public int id;
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
}
