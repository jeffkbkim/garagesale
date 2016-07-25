package models;

/**
 * Created by Yuda on 6/17/16.
 * Item Form Data.
 */
public class BidFormData {
    private int saleId;
    private double bidPrice;
    private int itemId;

    /**
     * ItemFormData no arg constructor
     */
    public BidFormData(){}

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int item) {
        this.itemId = item;
    }

    /**

     * initializes ItemFormData
     * @param price bid price
     */
    public BidFormData(double price) {
        this.bidPrice = price;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double price) {
        this.bidPrice = price;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

}
