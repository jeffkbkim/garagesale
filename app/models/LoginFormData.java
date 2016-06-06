package models;

/**
 * Created by Douglas on 6/6/2016.
 */
public class LoginFormData {
    public String username;
    public String password;
    public LoginFormData(){}
    public LoginFormData(String username,
                         String password) {
        this.username = username;
        this.password = password;
    }

}
