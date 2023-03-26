import java.util.*;

public class CSR extends People
{
    int id;
    ArrayList<Request> assistanceRequests;

    public CSR(String firstName, String lastName, String email, String phoneNum, int id,
               ArrayList<Request> assistanceRequests)
    {
        super(firstName, lastName, email, phoneNum);
        this.id = id;
        this.assistanceRequests = assistanceRequests;
    }

    // Setter and getter for assistanceRequests
    public ArrayList<Request> getAssistanceRequests()
    {
        return assistanceRequests;
    }
    public void addRequest(Request assistance)
    {
        this.assistanceRequests.add(assistance);
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
}
