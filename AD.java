import java.util.ArrayList;

/**
 * This class creates the admin object in the system, which extends the People class.
 * Admins have the suspicious activity reports and meeting requests the customers made to them.
 */
public class AD extends People
{
    private int id;
    private final ArrayList<Report> customerReports;
    private final ArrayList<Request> meetingRequests;

    /**
     * Constructor for the Admin
     * @param firstName the first name of the Admin
     * @param lastName the last name of the Admin
     * @param email the email of the Admin
     * @param phoneNum the phone number of the Admin
     * @param id the id of the Admin
     * 
     */
    public AD(String firstName, String lastName, String email, String phoneNum, int id) {

        super(firstName, lastName, email, phoneNum);
        this.id = id;
        customerReports = new ArrayList<>();
        meetingRequests= new ArrayList<>();
    }

    /**
     * Gets the id of the Admin
     * @return id of the admin
     */
    public int getId() {

        return id;

    }

    /**
     * Sets the id of the Admin
     * @param id the id of the Admin
     * 
     */
    public void setId(int id) {

        this.id = id;

    }

    /**
     * Gets the customer reports of the Admin
     * @return the customer reports of the Admin
     * 
     */
    public ArrayList<Report> getCustomerReports() {

        return customerReports;

    }

    /**
     * Adds a customer report to the Admin
     * @param report the customer report to be added
     * 
     */
    public void addCustomerReports(Report report) {

        this.customerReports.add(report);

    }

    /**
     * Gets the meeting requests of the Admin
     * @return the meeting requests of the Admin
     * 
     */
    public ArrayList<Request> getMeetingRequests() {

        return meetingRequests;

    }

    /**
     * Adds a meeting request to the Admin
     * @param meetingRequest the meeting request to be added
     * 
     */
    public void addMeetingRequests(Request meetingRequest) {

        this.meetingRequests.add(meetingRequest);

    }

}
