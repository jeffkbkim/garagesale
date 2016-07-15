package models;

/**
 * Created by Douglas on 6/15/2016.
 * Profile Form Data
 */
public class ProfileFormData {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String verifypassword;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifypassword() {
        return verifypassword;
    }

    public void setVerifypassword(String verifypassword) {
        this.verifypassword = verifypassword;
    }
}
