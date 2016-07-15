package models;

/**
 * Sale Role From Data
 */
public class SaleRoleFormData {
    private int saleId;
    private String username;
    private String role;

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

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
