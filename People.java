package bank.core;

import java.io.Serializable;

public class People implements Serializable
{
    // This class is the parent class for all the following classes:
    // 1- Client with account (includes recipients) (CA)
    // 2- Admin (AD)
    // 3- Maintenance Team (MT)
    // 4- Customer Service Representative (CSR)
    String firstName;
    String lastName;
    String email;
    String phoneNum;

    /*
     * Constructor for the People class
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
    
    /*
     * Sets the first name of the person
     * @param firstName the first name of the person
     * 
     */
    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }
    
    /* 
     * Sets the last name of the person
     * @param lastName the last name of the person
     * 
     */
    public void setLastName(String lastName) {

        this.lastName =  lastName;

    }
    
    /*
     * Gets the first name of the person
     * @return the first name of the person
     * 
     */
    public String getEmail() {

        return email;

    }

    /*
     * Sets the email of the person
     * @param email: the email of the person
     * 
     */
    public void setEmail(String email) {

        this.email = email;

    }

    /*
     * Gets the phone number of the person
     * @return the phone number of the person
     * 
     */
    public String getPhoneNum() {

        return phoneNum;

    }

    /*
     * Sets the phone number of the person
     * @param phoneNum the phone number of the person
     * 
     */
    public void setPhoneNum(String phoneNum) {

        this.phoneNum = phoneNum;

    }

    /*
     * Gets the first name of the person
     * @return the first name of the person
     * 
     */
    public String getFirstName() {

        return firstName;

    }

    /*
     * Gets the last name of the person
     * @return the last name of the person
     * 
     */
    public String getLastName() {

        return lastName;

    }

}
