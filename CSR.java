import java.util.*;

/**
 * Class for the customer service representative (CSR) object. It extends People and has extra attributes only pertinent
 * to CSR, such as ID.
 */
public class CSR extends People
{
    private int id;
    private final ArrayList<Request> assistanceRequests;

    /**
     * Constructor for the CSR
     * @param firstName the first name of the CSR
     * @param lastName the last name of the CSR
     * @param email the email of the CSR
     * @param phoneNum the phone number of the CSR
     * @param id the id of the CSR
     * 
     */
    public CSR(String firstName, String lastName, String email, String phoneNum, int id) {

        super(firstName, lastName, email, phoneNum);
        this.id = id;
        assistanceRequests = new ArrayList<>();
    }

    /**
     * Gets the assistance requests of the CSR
     * @return ArrayList of Request objects, the assistance requests the CSR received from a customer
     */
    public ArrayList<Request> getAssistanceRequests() {
        return assistanceRequests;
    }

    /**
     * Adds an assistance request made by the customer to the CSR
     * @param assistance the assistance request to be added
     */
    public void addRequest(Request assistance) {
        this.assistanceRequests.add(assistance);
    }

    /**
     * Gets the id of the CSR
     * @return id of the CSR
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the CSR
     * @param id the id of the CSR
     */
    public void setId(int id) {
        this.id = id;
    }

}
