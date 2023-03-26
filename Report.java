import java.io.Serializable;

// This class is the parent class for all the following classes:
// 1- Client with account (includes recipients) (CA)
// 2- Admin (AD)
// 3- Maintenance Team (MT)
// 4- Customer Service Representative (CSR)
public class Report implements Serializable {
    String firstName;
    String lastName;
    String email;
    String cardNum;

    /*
     * Constructor for Report
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param email the email of the person
     * @param cardNum the card number of the person
     * 
     */
    public Report(String firstName, String lastName, String email, String cardNum) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cardNum = cardNum;

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
     * Sets the first name of the person
     * @param firstName the first name of the person
     * 
     */
    public void setFirstName(String fName) {

        this.firstName = fName;

    }

    /*
     * Gets the last name of the person
     * @return the last name of the person
     * 
     */
    public String getLastName() {

        return lastName;

    }

    /*
     * Sets the last name of the person
     * @param lastName the last name of the person
     * 
     */
    public void setLastName(String lName) {

        this.lastName = lName;

    }

    /*
     * Gets the email of the person
     * @return the email of the person
     * 
     */
    public String getEmail() {
        return email;
    }

    /*
     * Sets the email of the person
     * @param email the email of the person
     * 
     */
    public void setEmail(String email) {

        this.email = email;

    }

    /*
     * Gets the card number of the person
     * @return the card number of the person
     * 
     */
    public String getCardNum() {
        return cardNum;
    }

    /*
     * Sets the card number of the person
     * @param cardNum the card number of the person
     * 
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

}
