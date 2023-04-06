import java.io.Serializable;

/**
 * This class creates the different request objects depending on the different types of request the customer makes.
 * Different receivers are set for different types of reports made. Types: (1) Request system changes, (2) request
 * meeting from admin, (3) request assistance from CSR.
 */
public class Request implements Serializable {
    String type;
    MT mt;
    AD admin;
    CSR csr;
    String details;

    /**
     * Constructor for Request (type 1), requesting system changes from maintenance team
     * @param type the type of request
     * @param mt the maintenance team receiving the system changes request
     * @param description the details of the system changes request
     */
    public Request(String type, MT mt, String description) {
        this.type = type;
        this.mt = mt;
        this.details = description;
    }

    /**
     * Constructor for Request (type 2), requesting a meeting from admin
     * @param type the type of request
     * @param admin the admin receiving the meeting request
     * @param details the details of the meeting request
     */
    public Request(String type, AD admin, String details) {
        this.type = type;
        this.admin = admin;
        this.details = details;
    }

    /**
     * Constructor for Request (type 3), requesting assistance from CSR
     * @param type the type of request
     * @param csr the customer service representative that receives the request
     * @param description the description of the assistance request
     */
    public Request(String type, CSR csr, String description) {
        this.type = type;
        this.csr = csr;
        this.details = description;
    }

    /**
     * Gets the type of request
     * @return the type of request
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of request
     * @param type the type of request
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the admin
     * @return the admin that receives the meeting request
     */
    public AD getAdmin() {
        return admin;
    }

    /**
     * Sets the admin
     * @param admin the admin that receives the meeting request
     */
    public void setAdmin(AD admin) {
        this.admin = admin;
    }

    /**
     * Gets the maintenance team
     * @return the maintenance team receiving the system changes request
     */
    public MT getMT() {
        return mt;
    }

    /**
     * Sets the maintenance team
     * @param mt the maintenance team receiving the system changes request
     */
    public void setMT(MT mt) {
        this.mt = mt;
    }

    /**
     * Gets the customer service representative
     * @return the customer service representative receiving the assistance request
     */
    public CSR getCSR() {
        return csr;
    }

    /**
     * Sets the customer service representative
     * @param csr the customer service representative receiving the assistance request
     */
    public void setCSR(CSR csr) {
        this.csr = csr;
    }

    /**
     * Gets the details of the request
     * @return the details/date of the request being made
     */
    public String getDetails()
    {
        return details;
    }

    /**
     * Sets the details of the request
     * @param str the details/date of the request being made
     */
    public void setDetails(String str)
    {
        this.details = str;
    }

}

