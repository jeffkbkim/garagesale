package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Douglas on 6/6/2016.
 */
@Entity
public class User extends Model{
    @Id
    protected int id;
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
    protected String password;
    protected int level;
    public User(){}
    public User(String userName,
                String firstName,
                String lastName,
                String phoneNumber,
                String email,
                String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    public User(String userName, String password) {
        this(userName, null, null, null, null, password);
    }
    public String getUserName() { return userName; }
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
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() { return password; }
    public boolean checkPassword(String check) {
        return password.equals(check);
    }
    public static User makeInstance(UserFormData formData) {
        User user = new User();
        user.userName = formData.username;
        user.firstName = formData.firstname;
        user.lastName = formData.lastname;
        user.password = formData.password;
        user.phoneNumber = formData.phone;
        user.email = formData.email;
        return user;
    }

    public boolean equals(Object o) {
        if (o instanceof User) {
            User tester = (User) o;
            if (tester.getUserName() != null && userName.equals(tester.getUserName())) {
                return true;
            }
        }
        return false;
    }
}
