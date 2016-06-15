package models;

/**
 * Created by Douglas on 6/15/2016.
 */
public class ProfileFormData {
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String verifypassword;
    public ProfileFormData(){}
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
