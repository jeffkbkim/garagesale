package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by Douglas on 6/29/2016.
 */

@Entity
public class Transaction extends Model{
    @Id
    protected int id;
    protected int quantity;
    protected double profit;
    protected String buyer;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    protected Sale sale;
    @ManyToOne
    @JoinColumn(name = "item_id")
    protected Item item;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    protected Receipt receipt;

    public static Finder<Integer, Transaction> find
            = new Finder<>(Transaction.class);

    public Transaction() {}

    public Transaction( int quantity, double profit, String buyer) {
        this.quantity = quantity;
        this.profit = profit;
        this.buyer = buyer;
    }

    public Receipt getReceipt() { return this.receipt; }

    public Item getItem() { return this.item; }

    public int getId() { return this.id; }

    public String getBuyer() { return this.buyer; }

    public int getQuantity() { return this.quantity; }

    public double getProfit() { return this.profit; }

    public Sale getSale() { return this.sale; }

    public void setId(int id) { this.id = id; }

    public void setBuyer(String buyer) { this.buyer = buyer; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setProfit(double profit) { this.profit = profit; }

    public void setSale(Sale sale) { this.sale = sale; }

    public void setItem(Item item) { this.item = item; }

    public void setReceipt(Receipt receipt) { this.receipt = receipt; }

    public void setAllFields( int quantity, double profit, String buyer) {
        this.quantity = quantity;
        this.profit = profit;
        this.buyer = buyer;
    }

    public static List<Transaction> fetchTransactionsBySale(Sale sale) {
        List<Transaction> trans = Transaction.find.select("*").where().eq("sale_id", sale.getId()).findList();
        return trans;
    }

    public static List<Transaction> fetchTransactionsByItem(Item i) {
        List<Transaction> trans = Transaction.find.select("*").where().eq("item_id", i.getId()).findList();
        return trans;
    }

    public static List<Transaction> fetchTransactionByReceipt(Receipt receipt) {
        List<Transaction> trans = Transaction.find.select("*").where().eq("receipt_id", receipt.getId()).findList();
        return trans;
    }

    public static Transaction fetchTransactionById(int id) {
        Transaction trans = find.byId(id);
        return trans;
    }
}
