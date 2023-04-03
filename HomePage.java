package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// This class is for the home page
public class HomePage extends JFrame implements ActionListener
{

    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    private final String custName;
    private final JButton homeButton;
    private final JButton transferButton;
    private final JButton contactUSButton;
    private final JButton settingsButton;
    private final JButton findUsButton;
    private final JButton logoutButton;
    private final JButton chequingButton;
    private final JButton cheqAmountButton;
    private final JButton savingsButton;
    private final JButton savAmountButton;

    // Objects
    BankAutomated BA;
    CA customer;
    LoginPage previous;

    /*
     * HomePage Constructor
     * @param previous LoginPage object
     * @param BA BankAutomated object
     * @param customer CA object
     * 
     */
    public HomePage(LoginPage previous, BankAutomated BA, CA customer)
    {
        // Set title of the frame
        this.setTitle("Account Home");
        this.setLayout(null);
        
        // local variables
        custName = customer.firstName;
        double cheqAmount = customer.getChequing();
        double savAmount = customer.getSavings();
       
        // Set objects for previous, BA, and customer
        this.previous = previous;
        this.BA = BA;
        this.customer = customer;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 25);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border topBorder = BorderFactory.createMatteBorder(1,0,0,0,Color.GRAY);
        Color bg = new Color(214, 215, 215);
        Color buttonColor = Color.BLACK;

        // GUI Components for home button
        homeButton = new JButton("Home");
        homeButton.setFont(labels);
        homeButton.setBorder(emptyBorder);
        homeButton.setOpaque(false);
        homeButton.setFocusPainted(false);
        homeButton.setBackground(bg);
        homeButton.setForeground(buttonColor);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.setBounds(125, 125, 80, 40);
        homeButton.addActionListener(this);
        this.add(homeButton);

        // GUI Components for transfer button
        transferButton = new JButton("Transfer");
        transferButton.setFont(labels);
        transferButton.setBorder(emptyBorder);
        transferButton.setOpaque(false);
        transferButton.setFocusPainted(false);
        transferButton.setBackground(bg);
        transferButton.setForeground(buttonColor);
        transferButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transferButton.setBounds(280, 125, 120, 40);
        transferButton.addActionListener(this);
        this.add(transferButton);

        // GUI Components for contact us button
        contactUSButton = new JButton("Contact Us");
        contactUSButton.setFont(labels);
        contactUSButton.setBorder(emptyBorder);
        contactUSButton.setOpaque(false);
        contactUSButton.setFocusPainted(false);
        contactUSButton.setBackground(bg);
        contactUSButton.setForeground(buttonColor);
        contactUSButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contactUSButton.setBounds(475, 125, 150, 40);
        contactUSButton.addActionListener(this);
        this.add(contactUSButton);

        // GUI Components for settings button
        settingsButton = new JButton("Settings");
        settingsButton.setFont(labels);
        settingsButton.setBorder(emptyBorder);
        settingsButton.setOpaque(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setBackground(bg);
        settingsButton.setForeground(buttonColor);
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingsButton.setBounds(685, 125, 120, 40);
        settingsButton.addActionListener(this);
        this.add(settingsButton);

        // GUI Components for find us button
        findUsButton = new JButton("Find Us");
        findUsButton.setFont(labels);
        findUsButton.setBorder(emptyBorder);
        findUsButton.setOpaque(false);
        findUsButton.setFocusPainted(false);
        findUsButton.setBackground(bg);
        findUsButton.setForeground(buttonColor);
        findUsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        findUsButton.setBounds(875, 125, 100, 40);
        findUsButton.addActionListener(this);
        this.add(findUsButton);

        // GUI Components for logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(labels);
        logoutButton.setBorder(emptyBorder);
        logoutButton.setOpaque(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(bg);
        logoutButton.setForeground(buttonColor);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setBounds(1050, 125, 100, 40);
        logoutButton.addActionListener(this);
        this.add(logoutButton);

        // GUI Components for welcome label
        JLabel accLabel = new JLabel("Accounts:");
        accLabel.setFont(new Font("Raleway", Font.BOLD, 30));
        accLabel.setBorder(emptyBorder);
        accLabel.setBackground(bg);
        accLabel.setBounds(30, 160, 200, 100);
        this.add(accLabel);

        // GUI Components for chequing history button
        chequingButton = new JButton("   Chequing");
        chequingButton.setFont(labels);
        chequingButton.setBorder(emptyBorder);
        chequingButton.setContentAreaFilled(false);
        chequingButton.setOpaque(true);
        chequingButton.setFocusPainted(false);
        chequingButton.setBackground(Color.white);
        chequingButton.setForeground(Color.black);
        chequingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chequingButton.setHorizontalAlignment(SwingConstants.LEFT);
        chequingButton.setBounds(25, 250, 612, 150);
        chequingButton.addActionListener(this);
        this.add(chequingButton);

        // GUI Components for savings history button
        savingsButton = new JButton("   Savings");
        savingsButton.setFont(labels);
        savingsButton.setBorder(topBorder);
        savingsButton.setContentAreaFilled(false);
        savingsButton.setOpaque(true);
        savingsButton.setFocusPainted(false);
        savingsButton.setBackground(Color.white);
        savingsButton.setForeground(Color.black);
        savingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        savingsButton.setHorizontalAlignment(SwingConstants.LEFT);
        savingsButton.setBounds(25, 400, 612, 150);
        savingsButton.addActionListener(this);
        this.add(savingsButton);

        // GUI Components for chequing amount button
        cheqAmountButton = new JButton("$ " + cheqAmount + "   ");
        cheqAmountButton.setFont(new Font("Raleway", Font.BOLD, 25));
        cheqAmountButton.setBorder(BorderFactory.createEmptyBorder());
        cheqAmountButton.setContentAreaFilled(false);
        cheqAmountButton.setOpaque(true);
        cheqAmountButton.setFocusPainted(false);
        cheqAmountButton.setBackground(Color.white);
        cheqAmountButton.setForeground(Color.black);
        cheqAmountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cheqAmountButton.setHorizontalAlignment(SwingConstants.RIGHT);
        cheqAmountButton.setVerticalAlignment(SwingConstants.CENTER);
        cheqAmountButton.setBounds(610, 250, 630, 150);
        cheqAmountButton.addActionListener(this);
        this.add(cheqAmountButton);

        // GUI Components for savings amount button
        savAmountButton = new JButton("$ " + savAmount + "   ");
        savAmountButton.setFont(new Font("Raleway", Font.BOLD, 25));
        savAmountButton.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.GRAY));
        savAmountButton.setContentAreaFilled(false);
        savAmountButton.setOpaque(true);
        savAmountButton.setFocusPainted(false);
        savAmountButton.setBackground(Color.white);
        savAmountButton.setForeground(Color.black);
        savAmountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        savAmountButton.setHorizontalAlignment(SwingConstants.RIGHT);
        savAmountButton.setBounds(610, 400, 630, 150);
        savAmountButton.addActionListener(this);
        this.add(savAmountButton);

        // GUI Components for thank you label
        JLabel thanks = new JLabel("Thank you for using BCS.");
        thanks.setBackground(Color.white);
        accLabel.setBorder(emptyBorder);
        thanks.setForeground(new Color(96, 96, 96));
        thanks.setFont(new Font("Arial", Font.PLAIN, 15));
        thanks.setBounds(530, 575, 400, 20);
        this.add(thanks);

        // Window listener, logout when window is closed
        this.addWindowListener(new WindowEventHandler() {
            @Override
            public void windowClosing(WindowEvent evt) {
                //BA.logout (logic.logout) would be called here
                //Write all changes to the file
                BA.logout();

                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    window.dispose();
                }

                System.exit(0);
            }
        });

        // GUI Components frame
        this.getContentPane().setBackground(bg);
        this.setSize(WIDTH, LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /*
     * Method to paint the background of the frame
     * @param g Graphics object
     * 
     */
    public void paint(Graphics g)
    {
        super.paint(g);

        // Paint the background
        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(230, 30, 30);
        Color myBlack = new Color(160, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        // Paint the welcome message
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Welcome Back, " + custName, 25, 110);

    }


    /*
     * Method to handle button clicks
     * @param e ActionEvent object
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        // If the home button is clicked, go back to the home page
        if (e.getSource() == homeButton)
        {
            this.setVisible(false);
            this.setVisible(true);
        }

        // If the logout button is clicked, go back to the login page
        else if (e.getSource() == logoutButton)
        {
            int result = JOptionPane.showConfirmDialog(this,
                    "You will now be redirected to the login page.", "Logout?",
                    JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION)
            {
                this.setVisible(false);
                previous.setVisible(true);
            }
        }

        // IF the transfer button is clicked, display the transfer options
        else if(e.getSource() == transferButton)
        {
            String[] options = new String[] {"E-transfer", "Bank Transfer", "Transfer Funds", "Cancel"};
            int response = JOptionPane.showOptionDialog(this, "Select transfer option:",
                    "Transfer Option", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                    options[0]);

            // If the user selects e-transfer, go to the e-transfer page
            if (response == 0)
            {
                
                this.setVisible(false);
                ETransferPage eTransferPage = new ETransferPage(BA, this, customer, previous);
                eTransferPage.setVisible(true);
            }

            // If the user selects bank transfer, go to the bank transfer page
            else if (response == 1)
            {
                
                this.setVisible(false);
                BankTransferPage bankTransferPage = new BankTransferPage(BA,this, customer, previous);
                bankTransferPage.setVisible(true);
            }

            // If the user selects transfer funds, go to the transfer funds page
            else if (response == 2)
            {
                this.setVisible(false);
                TransferFundsPage fundTransferPage = new TransferFundsPage(BA,this, customer, previous);
                fundTransferPage.setVisible(true);
            }
        }

        // Contact us button to display contact options
        else if(e.getSource() == contactUSButton)
        {
            
            String[] options = new String[] {"Make a Request", "Make a Suspicious Activity Report","Cancel"};
            int response = JOptionPane.showOptionDialog(this, "Select contact option:",
                    "Ways to Contact", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                    options[0]);

            // If the user selects make a request, go to the request page
            if (response == 0)
            {
                this.setVisible(false);
                RequestsPage requestPage = new RequestsPage(BA,this, customer);
                requestPage.setVisible(true);
            }

            // If the user selects make a suspicious activity report, go to the report page
            else if (response == 1)
            {
                this.setVisible(false);
                MakeReportPage reportPage = new MakeReportPage(BA,this, customer);
                reportPage.setVisible(true);
            }
        }

        // If the find us button is clicked, go to the find us page
        else if(e.getSource() == findUsButton)
        {
            FindUsPage fuss = new FindUsPage(BA, this);
            this.setVisible(false);
            fuss.setVisible(true);
        }

        // If the settings button is clicked, display the settings options
        else if (e.getSource() == settingsButton )
        {
            
            String[] options = new String[] {"Notification Settings", "Profile Settings", "Privacy Settings", "Cancel"};
            int response = JOptionPane.showOptionDialog(this, "Select an option:",
                    "Setting Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                    options[0]);

            // If the user selects notification settings, go to the notification settings page
            if (response == 0)
            {
                this.setVisible(false);
                NotificationSettingPage notificationPage = new NotificationSettingPage(this, BA, customer);
                notificationPage.setVisible(true);
            }

            // If the user selects profile settings, go to the profile settings page
            else if (response == 1)
            {
                this.setVisible(false);
                ProfileSettingPage profileSettingPage = new ProfileSettingPage(this, BA, customer);
                profileSettingPage.setVisible(true);
            }

            // If the user selects privacy settings, go to the privacy settings page
            else if (response == 2)
            {
                this.setVisible(false);
                PrivacySettingPage privacyPage = new PrivacySettingPage(this, BA, customer);
                privacyPage.setVisible(true);
            }
        }

        // If the chequing button is clicked, go to the chequing history page
        else if (e.getSource() == chequingButton || e.getSource() == cheqAmountButton)
        {
            this.setVisible(false);
            ChequingHistoryPage chequingHistoryPage = new ChequingHistoryPage(this, BA, customer);
            chequingHistoryPage.setVisible(true);
        }

        // If the savings button is clicked, go to the savings history page
        else if (e.getSource() == savAmountButton || e.getSource() == savingsButton)
        {
            this.setVisible(false);
            SavingsHistoryPage savingsHistoryPage = new SavingsHistoryPage(this, BA, customer);
            savingsHistoryPage.setVisible(true);
        }

    }
    
}
