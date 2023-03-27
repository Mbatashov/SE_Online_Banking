import java.io.Serializable;

// This class is the parent class for all the following classes:
// 1- Client with account (includes recipients) (CA)
// 2- Admin (AD)
// 3- Maintenance Team (MT)
// 4- Customer Service Representative (CSR)
public class Report implements Serializable {
    CA customer;
    AD admin;
    String description;

    /*
     * Another Constructor for Report
     * @param customer: customer that made the report
     * @param admin: admin that receives the report
     */
    public Report(CA customer, AD admin, String description)
    {
        this.customer = customer;
        this.admin = admin;
        this.description = description;
    }

    public void setCustomer(CA customer)
    {
        this.customer = customer;
    }
    public CA getCustomer()
    {
        return customer;
    }

    public void setAdmin(AD admin)
    {
        this.admin = admin;
    }
    public AD getAdmin()
    {
        return admin;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return description;
    }
}
