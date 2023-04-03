package bank.core;

import java.util.*;

// Customer Service Representative Class
public class CSR extends People
{
    int id;
    ArrayList<Request> assistanceRequests = new ArrayList<>();

    /*
     * Constructor for the CSR
     * @param firstName the first name of the CSR
     * @param lastName the last name of the CSR
     * @param email the email of the CSR
     * @param phoneNum the phone number of the CSR
     * @param id the id of the CSR
     * @param assistanceRequests the assistance requests of the CSR
     * 
     */
    public CSR(String firstName, String lastName, String email, String phoneNum, int id) {

        super(firstName, lastName, email, phoneNum);
        this.id = id;

    }

    /*
     * Gets the assistance requests of the CSR
     * @return the assistance requests of the CSR
     * 
     */
    public ArrayList<Request> getAssistanceRequests() {

        return assistanceRequests;

    }

    /*
     * Adds an assistance request to the CSR
     * @param assistance the assistance request to be added
     * 
     */
    public void addRequest(Request assistance) {

        this.assistanceRequests.add(assistance);

    }

    /*
     * Gets the id of the CSR
     */
    public int getId() {

        return id;

    }

    /*
     * Sets the id of the CSR
     * @param id the id of the CSR
     * 
     */
    public void setId(int id) {

        this.id = id;

    }

}
