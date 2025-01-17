public class BankSystem
{
    public static void main(String[] args)
    {
        try
        {
            BankAutomated BA = new BankAutomated();
            CA test= BA.createAccount("Mo", "N", "6667771111", "Yonge Street",
                    "Male", "01/02/2003", "this2@gmail.com", "Hello@World1",
                    "4417123456789113", "0521", "111");
            LoginPage LP = new LoginPage(BA);
            HomePage HP = new HomePage(LP, BA, test);
            ETransferPage ETP = new ETransferPage(BA, HP, test, LP);
            BankTransferPage BTP = new BankTransferPage(BA, HP, test, LP);
            TransferFundsPage TFP = new TransferFundsPage(BA, HP, test, LP);
            MakeReportPage MRP = new MakeReportPage(BA, HP, test);
            RequestsPage RP = new RequestsPage(BA, HP, test);
            NotificationSettingPage NSP = new NotificationSettingPage(HP, BA, test);
            NSP.setVisible(true);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
