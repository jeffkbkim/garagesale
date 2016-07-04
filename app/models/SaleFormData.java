package models;

import play.data.format.Formats;
import play.data.format.Formats.DateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jeff on 6/17/16.
 * Sale Form Data
 */
public class SaleFormData {
    public int saleID;
    public String name;
    public String location;
    public DateTime dateTime;
    public double earnings;
    public ArrayList<User> users;

    /**
     * Sale Form Data no-arg constructor
     */
    public SaleFormData(){}

    /**
     * Creates SaleFormData with given parameters
     * @param saleID sale id
     * @param name sale name
     * @param location sale location
     * @param dateTime date and time of sale creation
     * @param users users involved in sale
     */
    public SaleFormData(int saleID, String name, String location,
                        DateTime dateTime, ArrayList<User> users) {
        this.saleID = saleID;
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
        this.users = users;
    }
}
