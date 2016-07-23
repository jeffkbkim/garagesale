package models;

/**
 * Created by Yuda on 6/17/16.
 * Item Form Data.
 */
public class ItemFormData {
    private int id;
    private String name;
    private String description;
    private double price;
    private double minimumPrice;
    private int quantity;
    private int saleId;
    private int itemId;

    /**
     * ItemFormData no arg constructor
     */
    public ItemFormData(){}

    /**
     * initializes ItemFormData
     * @param id item id
     * @param name item name
     * @param description item description
     * @param price item price
     * @param quantity item quantity
     */
    public ItemFormData(int id, String name, String description,
                        double price, double minimumPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minimumPrice = minimumPrice;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
