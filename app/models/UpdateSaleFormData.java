package models;

import play.data.format.Formats.DateTime;

import java.util.List;

/**
 * Created by Jeff on 6/17/16.
 * Sale Form Data
 */
public class UpdateSaleFormData {
    private int saleID;
    private String name;
    private String location;
    private int presale;
    private int bid;

    /**
     * Sale Form Data no-arg constructor
     */
    public UpdateSaleFormData(){}

    /**
     * Creates SaleFormData with given parameters
     * @param saleID sale id
     * @param name sale name
     * @param location sale location
     * @param bid date and time of sale creation
     * @param presale users involved in sale
     */
    public UpdateSaleFormData(String name, String location,
                              int presale, int bid, int saleID) {
        this.saleID = saleID;
        this.name = name;
        this.location = location;
        this.presale = presale;
        this.bid = bid;
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

    public int getPresale() {
        return presale;
    }

    public void setPresale(int presale) {
        this.presale = presale;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
}
