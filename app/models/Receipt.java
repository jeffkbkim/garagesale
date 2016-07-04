package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 6/29/2016.
 */
@Entity
public class Receipt extends Model{
    @Id
    protected int id;
    protected String date;
    protected double totalprofit;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    protected Sale sale;
    @OneToMany(mappedBy = "receipt")
    protected ArrayList<Transaction> transactions = new ArrayList<>();

    public static Finder<Integer, Receipt> find
            = new Finder<>(Receipt.class);

    public Receipt() {
    }

    public Sale getSale() {
        return this.sale;
    }

    public String getDate() {
        return this.date;
    }

    public double getProfit() {
        return this.totalprofit;
    }

    public void setProfit(double profit) {
        this.totalprofit = profit;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public static List<Receipt> fetchReceiptsBySale(Sale sale) {
        List<Receipt> receipts = Receipt.find.select("*").where().eq("sale_id", sale.getId()).findList();
        return receipts;
    }

    public static Receipt fetchReceiptById(int id) {
        Receipt receipt = find.byId(id);
        return receipt;
    }
}
