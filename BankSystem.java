public class BankSystem
{
    public static void main(String[] args)
    {
        try {
            BankAutomated BA = new BankAutomated();
            //Remove the comment at the beginning of the line below to reset the system (delete all saved customers)
            //BA.clearPeopleFile(); System.exit(0);
            //If previous line was used to reset the system, comment it out again to allow LoginPage to be created
            new LoginPage(BA);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
