package models;

import play.data.format.Formats.DateTime;

import java.util.ArrayList;

/**
 * Created by Yuda on 6/17/16.
 * Item Form Data.
 */
public class ItemFormData {
    public int id;
    public String name;
    public String description;
    public double price;
    public int quantity;
    public int saleId;
    public int itemId;

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
                        double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
