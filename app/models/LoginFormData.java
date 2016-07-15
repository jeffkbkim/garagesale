package models;

/**
 * Created by Douglas on 6/6/2016.
 * Login Form Data
 */
public class LoginFormData {
    private String username;
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
