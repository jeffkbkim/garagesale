package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 6/29/2016.
 * Receipt Entity
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

    /**
     * creates Finder for Receipt Entity.
     */
    public static Finder<Integer, Receipt> find
            = new Finder<>(Receipt.class);

    /**
     * Receipt no-arg constructor
     */
    public Receipt() {
    }

    /**
     * Sale getter method.
     * @return sale
     */
    public Sale getSale() {
        return this.sale;
    }

    /**
     * Date getter method.
     * @return date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * totalprofit getter method.
     * @return total profit
     */
    public double getProfit() {
        return this.totalprofit;
    }

    /**
     * totalprofit setter method.
     * @param profit receipt profit
     */
    public void setProfit(double profit) {
        this.totalprofit = profit;
    }

    /**
     * receipt id getter method
     * @return receipt id
     */
    public int getId() {
        return this.id;
    }

    /**
     * receipt id setter method
     * @param id receipt id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * receipt date setter method
     * @param date receipt date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * the sale corresponding to the receipt setter method.
     * @param sale sale corresponding to receipt
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     * fetches receipts by sale
     * @param sale sale corresponding to sale
     * @return list of receipts of sale
     */
    public static List<Receipt> fetchReceiptsBySale(Sale sale) {
        List<Receipt> receipts = Receipt.find.select("*").where().eq("sale_id", sale.getId()).findList();
        return receipts;
    }

    /**
     * fetches receipt by receipt id
     * @param id receipt id
     * @return receipt
     */
    public static Receipt fetchReceiptById(int id) {
        Receipt receipt = find.byId(id);
        return receipt;
    }
}
