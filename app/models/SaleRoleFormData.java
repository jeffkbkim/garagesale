package models;

import play.data.format.Formats.DateTime;

import java.util.ArrayList;

/**
 * Sale Role From Data
 */
public class SaleRoleFormData {
    public int saleId;
    public String username;
    public String role;

    /**
     * Sale Form Data no-arg constructor
     */
    public SaleRoleFormData(){}

    /**
     * Form Constructor
     * @param saleId sale id
     * @param username username to be added
     * @param role role to be assigned
     */
    public SaleRoleFormData(int saleId, String username, String role) {
        this.saleId = saleId;
        this.username = username;
        this.role = role;
    }
}
