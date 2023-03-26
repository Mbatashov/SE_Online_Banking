import java.util.*;

public class MT extends People
{
    int id;
    ArrayList<Request> sysChangeRequests;

    public MT(String firstName, String lastName, String email, String phoneNum, int id,
              ArrayList<Request> sysChangeRequests)
    {
        super(firstName, lastName, email, phoneNum);
        this.id = id;
        this.sysChangeRequests = sysChangeRequests;
    }

    public ArrayList<Request> getSysChangeRequests()
    {
        return sysChangeRequests;
    }
    public void addSysRequest(Request sysChange)
    {
        this.sysChangeRequests.add(sysChange);
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
