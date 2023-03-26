public class BankSystem
{
    public static void main(String[] args)
    {
        try
        {
            BankAutomated BA = new BankAutomated();
            //System.out.println("here");
            LoginPage LP = new LoginPage(BA);
            LP.setVisible(true);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
