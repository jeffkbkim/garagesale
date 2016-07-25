package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Hayun on 7/24/2016.
 */
public class DonationDataForm {
    private String name;
    private String address;
    private String date;
    private double contribution;

    public DonationDataForm(){}

    public DonationDataForm(String name, String address, String date, double contribution){
        this.name = name;
        this.address = address;
        this.date = date;
        this.contribution = contribution;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDate() { return date; }
    public double getContribution() { return contribution; }

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setDate(String date) { this.date = date; }
    public void setContribution(double contribution) {this.contribution = contribution; }
}
