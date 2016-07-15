package models;

import play.data.format.Formats.DateTime;

import java.util.List;

/**
 * Created by Jeff on 6/17/16.
 * Sale Form Data
 */
public class SaleFormData {
    private int saleID;
    private String name;
    private String location;
    private DateTime dateTime;
    private double earnings;
    private List<User> users;

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
                        DateTime dateTime, List<User> users) {
        this.saleID = saleID;
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
        this.users = users;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
