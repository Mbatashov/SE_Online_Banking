import java.io.Serializable;

// Request class, between CA and AD, MT, CSR
public class Request implements Serializable {
    String type;
    MT mt;
    AD admin;
    CSR csr;

    /*
     * Constructor for Request (type 1) 
     * @param type the type of request
     * @param mt the maintenance team
     * 
     */
    public Request(String type, MT mt) {

        this.type = type;
        this.mt = mt;

    }

    /*
     * Constructor for Request (type 2)
     * @param type the type of request
     * @param admin the admin
     * 
     */
    public Request(String type, AD admin) {

        this.type = type;
        this.admin = admin;

    }

    /*
     * Constructor for Request (type 3)
     * @param type the type of request
     * @param csr the customer service representative
     * 
     */
    public Request(String type, CSR csr) {

        this.type = type;
        this.csr = csr;

    }

    /*
     * Gets the type of request
     * @return the type of request
     * 
     */
    public String getType() {

        return type;

    }

    /*
     * Sets the type of request
     * @param type the type of request
     * 
     */
    public void setType(String type) {

        this.type = type;

    }

    /*
     * Gets the admin
     * @return the admin
     * 
     */
    public AD getAdmin() {
        return admin;

    }

    /*
     * Sets the admin
     * @param admin the admin
     * 
     */
    public void setAdmin(AD admin) {

        this.admin = admin;

    }

    /*
     * Gets the maintenance team
     * @return the maintenance team
     * 
     */
    public MT getMT() {

        return mt;

    }

    /*
     * Sets the maintenance team
     * @param mt the maintenance team
     * 
     */
    public void setMT(MT mt) {

        this.mt = mt;

    }

    /*
     * Gets the customer service representative
     * @return the customer service representative
     * 
     */
    public CSR getCSR() {

        return csr;

    }

    /*
     * Sets the customer service representative
     * @param csr the customer service representative
     * 
     */
    public void setCSR(CSR csr) {

        this.csr = csr;

    }

}

