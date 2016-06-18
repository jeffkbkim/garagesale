package models;

import play.data.format.Formats;
import play.data.format.Formats.DateTime;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jeff on 6/17/16.
 */
public class SaleFormData {
    public int saleID;
    public String name;
    public String location;
    public DateTime dateTime;
    public double earnings;
    public HashMap<String, Integer> userList;
    public SaleFormData(){}
    public SaleFormData(int saleID, String name, String location,
                        DateTime dateTime, HashMap<String, Integer> userList) {
        this.saleID = saleID;
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
        this.userList = userList;
    }
}
