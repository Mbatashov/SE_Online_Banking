import java.io.Serializable;

/**
 * This class is the parent class for: (1) CA, (2) AD, (3) MT, and (4) CSR
 */
public class People implements Serializable
{
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;

    /**
     * Constructor for the People class, extended by the children that inherit it
     * @param firstName: the first name of the person
     * @param lastName: the last name of the person
     * @param email: the email of the person
     * @param phoneNum: the phone number of the person
     * 
     */
    public People(String firstName, String lastName, String email, String phoneNum)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
    }
    
    /**
     * Sets the first name of the person
     * @param firstName the first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Sets the last name of the person
     * @param lastName the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName =  lastName;
    }
    
    /**
     * Gets the first name of the person
     * @return the first name of the person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the person
     * @param email the email of the person
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the person
     * @return the phone number of the person
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Sets the phone number of the person
     * @param phoneNum the phone number of the person
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Gets the first name of the person
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the person
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

}
