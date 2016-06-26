package models;

/**
 * Created by Douglas on 6/15/2016.
 * Profile Form Data
 */
public class ProfileFormData {
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String verifypassword;

    /**
     * Profile Form Data no-arg constructor
     */
    public ProfileFormData(){}

    /**
     * Creates Profile Form Data with given parameters.
     * @param firstName user firstname
     * @param lastName user last name
     * @param email user email address
     * @param phone user phone number
     * @param verifypassword input password
     */
    public ProfileFormData(String firstName,
                           String lastName,
                           String email,
                           String phone, String verifypassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.verifypassword = verifypassword;
    }

}
