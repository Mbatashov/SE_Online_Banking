import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * This class is a graphical implementation of the Bank Transfer page where customers would be able to make
 * bank transfers (either from chequing or savings) to any user if they have their account number. Currently, this class
 * assumes that any 5-digit number is a valid bank account number. If a user with that account number exists within
 * the system, then the money is automatically deposited to their chequing account. Otherwise, a prompt saying it is an
 * external transfer is shown.
 */
public class BankTransferPage extends JFrame implements ActionListener
{
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;
    BankAutomated BA;
    HomePage home;
    CA customer;
    LoginPage login;

    // GUI Components for BankTransferPage
    private final JButton backToHome;
    private final JTextField accountNumField;
    private final JTextField amountField;
    private final JComboBox<String> selectAccount;
    private final JButton completeButton;

    /**
     * This constructor creates all the frame specifications for when the customer selects "Transfer"
     * and then "Bank Transfer" in the system (i.e., buttons, labels, etc.)
     * @param BA BankAutomated object
     * @param home HomePage object
     * @param customer CA object
     * @param login LoginPage object
     */
    public BankTransferPage(BankAutomated BA, HomePage home, CA customer, LoginPage login)
    {
        this.setTitle("Make Bank Transfer");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;
        this.login = login;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 30);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);


        // GUI Components for contact button
        JLabel contact = new JLabel("Receiver's Account #:");
        contact.setFont(new Font("Raleway", Font.BOLD, 28));
        contact.setBorder(emptyBorder);
        contact.setForeground(Color.black);
        contact.setBounds(290,200,300,40);
        this.add(contact);

        // GUI Components for account number field
        accountNumField = new JTextField(150);
        accountNumField.setFont(new Font("Arial", Font.PLAIN, 20));
        accountNumField.setBorder(border);
        accountNumField.setBounds(690, 200, 350, 40);
        this.add(accountNumField);

        // GUI Components for amount field
        JLabel amount = new JLabel("Amount (in CAD):");
        amount.setFont(labels);
        amount.setBorder(emptyBorder);
        amount.setForeground(Color.black);
        amount.setBounds(290,325,300,40);
        this.add(amount);

        // GUI Components for amount field
        amountField = new JTextField(150);
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBorder(border);
        amountField.setBounds(690, 325, 350, 40);
        this.add(amountField);

        // GUI Components for account selection
        JLabel account = new JLabel("Account from:");
        account.setFont(labels);
        account.setBorder(emptyBorder);
        account.setForeground(Color.black);
        account.setBounds(290,450,300,40);
        this.add(account);

        // GUI Components for account selection
        String[] accounts = {"Select Account", "Chequing", "Savings"};
        selectAccount = new JComboBox<>(accounts);
        selectAccount.setFont(new Font("Arial", Font.PLAIN, 20));
        selectAccount.setBounds(690, 450, 350, 40);
        selectAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectAccount.addActionListener(this);
        this.add(selectAccount);

        // GUI Components for complete button
        completeButton = new JButton("Complete Transaction");
        completeButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
        completeButton.setBounds(475, 525, 350, 40);
        completeButton.setBackground(Color.black);
        completeButton.setForeground(Color.white);
        completeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completeButton.setBorder(emptyBorder);
        completeButton.addActionListener(this);
        this.add(completeButton);

        // GUI Components for back to home button
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

        // Window Listener for closing the window
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

        // Window Settings
        this.getContentPane().setBackground(Color.white);
        this.getRootPane().setDefaultButton(completeButton);
        this.setSize(WIDTH, LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * paint method, overrides the JFrame paint method in order to allow for custom graphical design
     * @param g Graphics object
     */
    public void paint(Graphics g)
    {
        super.paint(g);

        // Background
        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(230, 30, 30);
        Color myBlack = new Color(160, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        // Title
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Make Bank Transfer", 25, 110);
    }

    /**
     * actionPerformed method (implementing ActionListener). It calls all the logic through BankAutomated
     * when certain conditions are met through the ActionEvent parameter (e.g., a button is clicked). It also
     * displays success/failure methods depending on the conditions, as well as controls the frame display (e.g.,
     * if back to home is clicked, it displays the homepage frame and stops displaying the transfer one).
     * @param e ActionEvent object, which listens and keeps track of any button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If back to home button is clicked
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }

        // If complete button is clicked
        else if (e.getSource() == completeButton)
        {
            // Get all the information from the fields
            String accountNum = accountNumField.getText();
            String amount = amountField.getText();
            String accountFrom = String.valueOf(selectAccount.getSelectedItem());

            // Check if all fields are filled
            if (accountNum.equals("") || amount.equals("") || selectAccount.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "ERROR: All fields are required.");
                return;
            }

            // Check if account number is valid
            else if (!BA.onlyNumericDouble(amount))
            {
                JOptionPane.showMessageDialog(this, "ERROR: Amount needs to be a digit (can include decimal).");
                return;
            }

            // Complete Transfer
            int result = BA.bankTransfer(Double.parseDouble(amount), accountNum, customer, accountFrom);

            // Transfer was unsuccessful (invalid account number, insufficient funds, minimum transfer amount)
            if (result == 1)
            {
                JOptionPane.showMessageDialog(this, "ERROR: Invalid account number.");
            }
            else if (result == 2)
            {
                JOptionPane.showMessageDialog(this, "ERROR: You do not have sufficient funds in selected account.");
            }
            else if (result == 5)
            {
                JOptionPane.showMessageDialog(this, "ERROR: Minimum transfer is $0.5");
            }
            else if (result == 6)
            {
                JOptionPane.showMessageDialog(this, "ERROR: You cannot make a bank transfer to yourself. Try transfer funds instead.");
            }

            // Transfer was successful (external transfer)
            else if (result == 3)
            {
                // Print success message and send user back to home page
                JOptionPane.showMessageDialog(this, "SUCCESS: You transferred $" + amount +
                        " to user with account number " + accountNum + ".\n This is an external transfer (i.e., user is not a part of BCS).");
                this.setVisible(false);
                home = new HomePage(login, BA, customer);
                home.setVisible(true);
            }

            // Transfer was successful (internal transfer)
            else if (result == 0)
            {
                // Print success message and send user back to home page
                JOptionPane.showMessageDialog(this, "SUCCESS: You transferred $" + amount +
                        " to user with account number " + accountNum + ".\n This user has an account with BCS, and the funds will " +
                        "be auto-deposited to their chequing account.");
                this.setVisible(false);
                home = new HomePage(login, BA, customer);
                home.setVisible(true);
            }

            // An error occurred
            else
            {
                JOptionPane.showMessageDialog(this, "An error occurred.");
            }
        }
    }
}
