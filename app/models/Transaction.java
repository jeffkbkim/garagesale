package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by Douglas on 6/29/2016.
 * Transaction Entity
 */

@Entity
public class Transaction extends Model{
    @Id
    private int id;
    private int quantity;
    private double profit;
    private String method;
    private String buyer;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    /**
     * creates Finder for Transaction Entity
     */
    private static Finder<Integer, Transaction> find
            = new Finder<>(Transaction.class);
    /**
     * Transaction no-arg constructor
     */
    public Transaction() {}

    /**
     * creates a Transaction instance
     * @param quantity quantity of item
     * @param profit total profit from item
     * @param buyer name of buyer
     * @param method method of payment
     */
    public Transaction( int quantity, double profit, String buyer, String method) {
        this.quantity = quantity;
        this.profit = profit;
        this.buyer = buyer;
        this.method = method;
    }

    /**
     * Receipt getter method
     * @return receipt
     */
    public Receipt getReceipt() { return this.receipt; }

    /**
     * Item getter method
     * @return item
     */
    public Item getItem() { return this.item; }

    /**
     * transaction Id getter method
     * @return transaction id
     */
    public int getId() { return this.id; }

    /**
     * buyer getter method
     * @return name of buyer
     */
    public String getBuyer() { return this.buyer; }

    /**
     * quantity getter method
     * @return quantity of item
     */
    public int getQuantity() { return this.quantity; }

    /**
     * profit getter method
     * @return profit from item
     */
    public double getProfit() { return this.profit; }

    /**
     * Sale getter method
     * @return sale corresponding to Transaction
     */
    public Sale getSale() { return this.sale; }

    /**
     * Get payment method
     * @return The payment method
     */
    public String getMethod() { return method; }

    /**
     * Transaction id setter method
     * @param id id of Transaction
     */
    public void setId(int id) { this.id = id; }

    /**
     * buyer setter method
     * @param buyer name of buyer
     */
    public void setBuyer(String buyer) { this.buyer = buyer; }

    /**
     * item quantity setter method
     * @param quantity item quantity
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * profit setter method
     * @param profit profit from item
     */
    public void setProfit(double profit) { this.profit = profit; }


    /**
     * Set payment method
     * @param method New method
     */
    public void setMethod(String method) { this.method = method; }

    /**
     * Sale setter method
     * @param sale Sale corresponding to transaction
     */
    public void setSale(Sale sale) { this.sale = sale; }

    /**
     * Item setter method
     * @param item transaction item
     */
    public void setItem(Item item) { this.item = item; }

    /**
     * Receipt setter method
     * @param receipt transaction receipt
     */
    public void setReceipt(Receipt receipt) { this.receipt = receipt; }




    /**
     * sets quantity, profit, buyer in transaction
     * @param quantity quantity of item
     * @param profit profit from item
     * @param buyer name of buyer
     */
    public void setAllFields( int quantity, double profit, String buyer, String method) {
        this.quantity = quantity;
        this.profit = profit;
        this.buyer = buyer;
        this.method = method;
    }


    /**
     * fetches transactions of sale
     * @param sale sale corresponding to transaction
     * @return list of transaction
     */
    public static List<Transaction> fetchTransactionsBySale(Sale sale) {
        return Transaction.find.select("*").where().eq("sale_id", sale.getId()).findList();
    }

    /**
     * fetches transactions by item
     * @param i item
     * @return list of transactions
     */
    public static List<Transaction> fetchTransactionsByItem(Item i) {
        return Transaction.find.select("*").where().eq("item_id", i.getId()).findList();
    }

    /**
     * fetches transactions by receipt
     * @param receipt receipt
     * @return list of transactions
     */
    public static List<Transaction> fetchTransactionByReceipt(Receipt receipt) {
        return Transaction.find.select("*").where().eq("receipt_id", receipt.getId()).findList();
    }

    /**
     * fetches transaction by id
     * @param id transaction id
     * @return Transaction
     */
    public static Transaction fetchTransactionById(int id) {
        return find.byId(id);
    }

    public static Finder<Integer, Transaction> getFind() {
        return find;
    }

    public static void setFind(Finder<Integer, Transaction> find) {
        Transaction.find = find;
    }
}
