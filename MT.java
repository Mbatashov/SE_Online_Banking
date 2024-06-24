import java.util.*;

/**
 * Class for maintenance team object. It extends the people class, and has extra attributes such as all the system
 * request changes made by the customer (stored in an arraylist).
 */
public class MT extends People
{
    private int id;
    private final ArrayList<Request> sysChangeRequests;

    /**
     * Constructor for the MT
     * @param firstName the first name of the Maintenance Team member
     * @param lastName the last name of the Maintenance Team member
     * @param email the email of the Maintenance Team member
     * @param phoneNum the phone number of the Maintenance Team member
     * @param id the id of the Maintenance Team member
     */
    public MT(String firstName, String lastName, String email, String phoneNum, int id) {
        super(firstName, lastName, email, phoneNum);
        this.id = id;
        sysChangeRequests = new ArrayList<>();
    }

    /**
     * Gets the system change requests sent to the Maintenance Team by the customer
     * @return the system change requests of the MT
     */
    public ArrayList<Request> getSysChangeRequests() {

        return sysChangeRequests;

    }

    /**
     * Adds a system change request to the Maintenance Team
     * @param sysChange the system change request to be added
     */
    public void addSysRequest(Request sysChange) {
        this.sysChangeRequests.add(sysChange);
    }

    /**
     * Gets the id of the Maintenance Team member
     * @return the id of the MT
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the Maintenance Team member
     * @param id the id of the MT
     */
    public void setId(int id) {
        this.id = id;
    }

}
