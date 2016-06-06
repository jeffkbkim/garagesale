package models;


/**
 * Created by Douglas on 6/6/2016.
 */
public class UserFormData {
    public String username;
    public String firstname;
    public String lastname;
    public String phone;
    public String email;
    public String password;

    public UserFormData() {}

    public UserFormData(String userame,
                        String firstname,
                        String lastname,
                        String phone,
                        String email,
                        String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
