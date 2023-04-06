import java.io.Serializable;

/**
 * This class creates the Report object that contains all the details of any suspicious activity report that the
 * customer makes
 */
public class Report implements Serializable
{
    CA customer;
    AD admin;
    String description;

    /**
     * Constructor for Report
     * @param customer the customer that made the suspicious activity report
     * @param admin the admin that receives the suspicious activity report
     */
    public Report(CA customer, AD admin, String description)
    {
        this.customer = customer;
        this.admin = admin;
        this.description = description;
    }

    /**
     * Sets the customer that made the report
     * @param customer the customer that made the report
     */
    public void setCustomer(CA customer)
    {
        this.customer = customer;
    }

    /**
     * Gets the customer that made the report
     * @return the customer that made the report
     */
    public CA getCustomer()
    {
        return customer;
    }

    /**
     * Sets the admin that receives the report
     * @param admin the admin that receives the report
     */
    public void setAdmin(AD admin)
    {
        this.admin = admin;
    }

    /**
     * Gets the admin that receives the report
     * @return the admin that receives the report
     */
    public AD getAdmin()
    {
        return admin;
    }

    /**
     * Sets the description of the report that the customer made
     * @param description a string describing the report details
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Gets the description of the report that the customer made
     * @return a string describing the report details
     */
    public String getDescription()
    {
        return description;
    }
}
