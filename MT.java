package bank.core;

import java.util.*;

// Maintenance Technician Class
public class MT extends People
{
    int id;
    ArrayList<Request> sysChangeRequests = new ArrayList<>();
    /*
     * Constructor for the MT
     * @param firstName the first name of the MT
     * @param lastName the last name of the MT
     * @param email the email of the MT
     * @param phoneNum the phone number of the MT
     * @param id the id of the MT
     * @param sysChangeRequests the system change requests of the MT
     * 
     */
    public MT(String firstName, String lastName, String email, String phoneNum, int id) {

        super(firstName, lastName, email, phoneNum);
        this.id = id;
        
    }

    /*
     * Gets the system change requests of the MT
     * @return the system change requests of the MT
     * 
     */
    public ArrayList<Request> getSysChangeRequests() {

        return sysChangeRequests;

    }

    /*
     * Adds a system change request to the MT
     * @param sysChange the system change request to be added
     * 
     */
    public void addSysRequest(Request sysChange) {

        this.sysChangeRequests.add(sysChange);

    }

    /*
     * Gets the id of the MT
     */
    public int getId() {

        return id;

    }

    /*
     * Sets the id of the MT
     * @param id the id of the MT
     * 
     */
    public void setId(int id) {

        this.id = id;

    }

}
