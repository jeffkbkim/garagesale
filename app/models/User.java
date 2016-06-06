package models;



/**
 * Created by Douglas on 6/6/2016.
 */
public class User {
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
    protected String password;
    public User(){}
    public User(String firstName,
                String lastName,
                String phoneNumber,
                String email,
                String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public boolean checkPassword(String check) {
        return password.equals(check);
    }
    public static User makeInstance(UserFormData formData) {
        User user = new User();
        user.userName = formData.userName;
        user.firstName = formData.firstName;
        user.lastName = formData.lastName;
        user.password = formData.password;
        user.phoneNumber = formData.phoneNumber;
        user.email = formData.email;
        return user;
    }
}
