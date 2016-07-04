package models;

/**
 * Created by Douglas on 6/6/2016.
 * Login Form Data
 */
public class LoginFormData {
    public String username;
    public String password;

    /**
     * Login Form Data no-arg constructor
     */
    public LoginFormData(){}

    /**
     * Login Form Data constructor
     * @param username username
     * @param password user password
     */
    public LoginFormData(String username,
                         String password) {
        this.username = username;
        this.password = password;
    }

}
