/**
 * Main class, it contains the main method and initializes the system to its Login Page.
 */
public class BankSystem
{
    /**
     * main method, creates a new LoginPage frame for users to start using the bank system
     * @param args the main method argument
     */
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
