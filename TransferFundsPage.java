package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// This class is for the Funds Transfer page
public class TransferFundsPage extends JFrame implements ActionListener
{
    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    BankAutomated BA;
    HomePage home;
    CA customer;
    LoginPage login;

    // GUI Components for TransferFundsPage
    JButton completeButton;
    JButton backToHome;
    private final JTextField amountField;
    private final JComboBox<String> fromToSelect;

    /*
     * TransferFundsPage Constructor
     * @param BA BankAutomated object
     * @param home HomePage object
     * @param customer CA object
     * @param login LoginPage object
     * 
     */
    public TransferFundsPage(BankAutomated BA, HomePage home, CA customer, LoginPage login)
    {

        // Set title of the frame
        this.setTitle("Funds Transfer");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;
        this.login = login;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 30);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        // Jlabel for amount
        JLabel amount = new JLabel("Amount (in CAD):");
        amount.setFont(labels);
        amount.setBorder(emptyBorder);
        amount.setForeground(Color.black);
        amount.setBounds(290,250,300,40);
        this.add(amount);

        // Text field for amount
        amountField = new JTextField(150);
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBorder(border);
        amountField.setBounds(690, 250, 350, 40);
        this.add(amountField);

        // Jlabel for account to transfer from
        JLabel account = new JLabel("From, To:");
        account.setFont(labels);
        account.setBorder(emptyBorder);
        account.setForeground(Color.black);
        account.setBounds(290,350,300,40);
        this.add(account);

        // Combo box for account to transfer from
        String[] accounts = {"Select an option", "Chequing to Savings", "Savings to Chequing"};
        fromToSelect = new JComboBox<>(accounts);
        fromToSelect.setFont(new Font("Arial", Font.PLAIN, 20));
        fromToSelect.setBounds(690, 350, 350, 40);
        fromToSelect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fromToSelect.addActionListener(this);
        this.add(fromToSelect);

        // Complete Transaction button
        completeButton = new JButton("Complete Transaction");
        completeButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
        completeButton.setBounds(475, 525, 350, 40);
        completeButton.setBackground(Color.black);
        completeButton.setForeground(Color.white);
        completeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completeButton.setBorder(emptyBorder);
        completeButton.addActionListener(this);
        this.add(completeButton);

        // Back to Home button
        backToHome = new JButton("Back to Home");
        backToHome.setFont(new Font("SansSerif", Font.PLAIN, 22));
        backToHome.setBounds(475, 600, 350, 50);
        backToHome.setBackground(Color.white);
        backToHome.setForeground(new Color(57, 107, 170));
        backToHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backToHome.setContentAreaFilled(false);
        backToHome.setFocusPainted(false);
        backToHome.setBorder(emptyBorder);
        backToHome.setContentAreaFilled(false);
        backToHome.addActionListener(this);
        this.add(backToHome);

        // Window closing event, logout when window is closed
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

        // Set frame properties
        this.getContentPane().setBackground(Color.white);
        this.getRootPane().setDefaultButton(completeButton);
        this.setSize(WIDTH, LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /*
     * paint method
     * @param g Graphics object
     * 
     */
    public void paint(Graphics g)
    {
        super.paint(g);

        // Set background gradient
        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(230, 30, 30);
        Color myBlack = new Color(160, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        // Set font
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Transfer Funds Between Your Accounts", 25, 110);
    }

    /*
     * actionPerformed method
     * @param e ActionEvent object
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Back to home button, go back to home page
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }

        // Complete transaction button, complete the transaction
        else if (e.getSource() == completeButton)
        {
            String amount = amountField.getText();
            String fromTo = String.valueOf(fromToSelect.getSelectedItem());
            String from = "";
            String to = "";

            // Set from and to accounts
            if (fromTo.equals("Chequing to Savings"))
            {
                from = "Chequing";
                to = "Savings";
            }

            // Set from and to accounts
            else if (fromTo.equals("Savings to Chequing"))
            {
                from = "Savings";
                to = "Chequing";
            }

            // Check if all fields are filled
            if (amount.equals("") || fromToSelect.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "ERROR: All fields are required.");
                return;
            }

            // Check if amount is a digit
            else if (!BA.onlyNumericDouble(amount))
            {
                JOptionPane.showMessageDialog(this, "ERROR: Amount needs to be a digit (can include decimal).");
                return;
            }

            // Do the transfer
            int result = BA.transferFunds(Double.parseDouble(amount), from, customer);

            // Check if transfer was successful (0 = success, 1 = insufficient funds, 2 = min transfer)
            if (result == 1)
            {
                JOptionPane.showMessageDialog(this, "ERROR: You do not have sufficient funds " +
                        "in your " + from + " account to complete this transaction.");
            }
            else if (result == 2)
            {
                JOptionPane.showMessageDialog(this, "ERROR: Minimum transfer is $0.5");
            }
            else if (result == 0)
            {
                JOptionPane.showMessageDialog(this, "SUCCESS: You transferred $" + amount +
                        " from your " + from + " account\n to your " + to + " account.");
                this.setVisible(false);
                home = new HomePage(login, BA, customer);
                home.setVisible(true);
            }

            // Error occurred
            else
            {
                JOptionPane.showMessageDialog(this, "An error occurred.");
            }

        }

    }
    
}