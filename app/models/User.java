package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Douglas on 6/6/2016.
 * User Entity
 */
@Entity
@Table(name="user_table")
public final class User extends Model{
    @Id
    private int id;
    @Column(unique = true)
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private int loginAttempts;
    private boolean isLocked;
    private boolean isSuperUser;
    @OneToMany(mappedBy = "user")
    private List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Receipt> receipts = new ArrayList<>();


    /**
     * User no-arg constructor
     */
    public User(){}

    /**
     * Creates User with given parameters
     * @param userName user username
     * @param firstName user firstname
     * @param lastName user lastname
     * @param phoneNumber user phone number
     * @param email user email address
     * @param password user password
     */
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
        this.isSuperUser = false;
    }

    /**
     * Creates user with only username and password
     * @param userName user username
     * @param password user password
     */
    public User(String userName, String password) {
        this(userName, null, null, null, null, password);
    }

    /**
     * user id getter method
     * @return user id
     */
    public int getId() {
        return id;
    }

    /**
     * user username getter method
     * @return user username
     */
    public String getUserName() { return userName; }

    /**
     * user first name getter method
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * user last name getter method
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * user phone number getter method
     * @return user phone number
     */
    public String getPhone() {
        return phoneNumber;
    }

    /**
     * user email address getter method
     * @return user email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * this method finds the role of a user
     *
     * @return administration list
     */
    public String getRole() {
        if (Role.fetchByUserId(id).size() > 0) {
            StringBuilder roles = new StringBuilder();
            for (int i = 0; i < Role.fetchByUserId(id).size(); i++) {
                roles.append(Role.fetchByUserId(id).get(i).getRole().toString());
                roles.append(" ");
            }
            return roles.toString();
        } else {
            return "No Roles Assigned";
        }
    }

    /**
     * creates Finder for User Entity
     */
    private static Finder<Integer, User> find
            = new Finder<>(User.class);

    /**
     * returns the number of current user's login attempts
     * @return user login attempts
     */
    public int getLoginAttempts() {
        return loginAttempts;
    }

    /**
     * shows whether user is locked or not.
     * @return true if user locked, false otherwise.
     */
    public boolean getIsLocked() {
        return isLocked;
    }

    /**
     * checks whether user is superuser or not.
     * @return true if user is superuser, false otherwise.
     */
    public boolean getIsSuperUser() {
        return isSuperUser;
    }

    /**
     * user first name setter method
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * user last name setter method
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * user phone number setter method
     * @param phone phone number
     */
    public void setPhone(String phone)
    {
        this.phoneNumber = phone;
    }

    /**
     * user email address setter method
     * @param email user email address
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * sets user's number of login attempts
     * @param loginAttempts number of login attempts
     */
    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    /**
     * sets whether user is locked or not
     * @param isLocked true if user is locked, false otherwise.
     */
    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * superuser setter method.
     * @param isSuperUser is user superuser
     */
    public void setIsSuperUser(boolean isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

    /**
     * user password getter method
     * @return user password
     */
    public String getPassword() { return password; }

    /**
     * validates password
     * @param check password input
     * @return true if password is correct, false otherwise
     */
    public boolean checkPassword(String check) {
        return password.equals(check);
    }

    /**
     * makes instance of User with UserFormData
     * @param formData UserFormData
     * @return User corresponding to the data
     */
    public static User makeInstance(UserFormData formData) {
        User user = new User(formData.getUsername(),
                formData.getFirstname(),
                formData.getLastname(),
                formData.getPhone(),
                formData.getEmail(),
                formData.getPassword());
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User tester = (User) o;
            if (tester.getUserName() != null && userName.equals(tester.getUserName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
//        int result = id;
//        result = 31 * result + (userName != null ? userName.hashCode() : 0);
//        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + loginAttempts;
//        result = 31 * result + (isLocked ? 1 : 0);
//        result = 31 * result + (isSuperUser ? 1 : 0);
//        result = 31 * result + (roles != null ? roles.hashCode() : 0);
//        return result;
        return id;
    }

    /**
     * database call to select a user by its id
     * @param id user id
     * @return user
     */
    public static User fetchById(int id) {
        return User.find.byId(id);
    }

    /**
     * Fetches user by username
     * @param username user username
     * @return user corresponding to username
     */
    public static User fetchByUsername(String username) {
        return User.find.select("*").where().eq("userName", username).findUnique();
    }

    /**
     * database call to select users by their ids
     * @param ids user ids
     * @return a list of users
     */
    public static List<User> fetchByIds(List<Integer> ids) {
        List<User> users = new LinkedList<>();
        User user;
        for (Integer id : ids) {
            user = User.find.byId(id);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    /**
     * database call to select all users
     * @return list of all users
     */
    public static List<User> fetchAllUsers() {
        List<User> allUsers = find.select("*").findList();
        if (allUsers == null) {
            allUsers = new ArrayList<>();
        }
        return allUsers;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public static Finder<Integer, User> getFind() {
        return find;
    }

    public static void setFind(Finder<Integer, User> find) {
        User.find = find;
    }
}
