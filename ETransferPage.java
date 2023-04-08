import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * This class is a graphical implementation of the E-Transfer page where customers would be able to make
 * e-transfers (either from chequing or savings) to any user if they have their email. Currently, this class assumes
 * that any valid email (checked by BankAutomated) can receive an e-transfer. If the email is within BCS, the money
 * is automatically deposited to chequing account of the user with that email, otherwise, a prompt saying it's an
 * external transfer is shown.
 */
public class ETransferPage extends JFrame implements ActionListener
{
    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    BankAutomated BA;
    HomePage home;
    CA customer;
    LoginPage login;
    
    // GUI Components for ETransferPage
    private final JButton backToHome;
    private final JTextField emailField;
    private final JTextField amountField;
    private final JComboBox<String> selectAccount;
    private final JButton completeButton;

    /**
     * ETransferPage Constructor, this creates all the frame specifications when the customer selects "Transfer" then
     * "Make an e-Transfer" from their homepage (e.g., buttons and labels).
     * @param BA BankAutomated object
     * @param home HomePage object
     * @param customer CA object
     * @param login LoginPage object
     * 
     */
    public ETransferPage(BankAutomated BA, HomePage home, CA customer, LoginPage login)
    {
        // Set title of the frame
        this.setTitle("Make an E-transfer");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;
        this.login = login;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 30);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // GUI Components for reciever's email
        JLabel contact = new JLabel("Receiver's Email:");
        contact.setFont(labels);
        contact.setBorder(emptyBorder);
        contact.setForeground(Color.black);
        contact.setBounds(350,200,300,40);
        this.add(contact);

        // GUI Components for email field
        emailField = new JTextField(150);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setBorder(border);
        emailField.setBounds(650, 200, 350, 40);
        this.add(emailField);

        // GUI Components for amount
        JLabel amount = new JLabel("Amount (in CAD):");
        amount.setFont(labels);
        amount.setBorder(emptyBorder);
        amount.setForeground(Color.black);
        amount.setBounds(350,325,300,40);
        this.add(amount);

        // GUI Components for amount field
        amountField = new JTextField(150);
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBorder(border);
        amountField.setBounds(650, 325, 350, 40);
        this.add(amountField);

        // GUI Components for account from
        JLabel account = new JLabel("Account from:");
        account.setFont(labels);
        account.setBorder(emptyBorder);
        account.setForeground(Color.black);
        account.setBounds(350,450,300,40);
        this.add(account);

        // GUI Components for account selection
        String[] accounts = {"Select Account", "Chequing", "Savings"};
        selectAccount = new JComboBox<>(accounts);
        selectAccount.setFont(new Font("Arial", Font.PLAIN, 20));
        selectAccount.setBounds(650, 450, 350, 40);
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

        // Window listener to logout when the window is closed
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

        // Set the frame
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

        // Set the background
        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(230, 30, 30);
        Color myBlack = new Color(160, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        // Set the title
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("E-Transfer to Contact", 25, 110);

    }

    /**
     * actionPerformed method (implementing ActionListener). It calls all the logic through BankAutomated
     * when certain conditions are met through the ActionEvent parameter (e.g., a button is clicked). It also
     * displays success/failure methods depending on the conditions (e.g., prints a message if the user does not have
     * enough funds for the transfer), as well as controls the frame display.
     * @param e ActionEvent object, which listens and keeps track of any button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If the back to home button is clicked, go back to the home page
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }
        // If the complete button is clicked, complete the transaction
        else if (e.getSource() == completeButton)
        {
            // Get the information from the fields
            String email = emailField.getText();
            String amount = amountField.getText();
            String accountFrom = String.valueOf(selectAccount.getSelectedItem());

            // Check if all fields are filled
            if (email.equals("") || amount.equals("") || selectAccount.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "ERROR: All fields are required.");
                return;
            }
            // Check if the amount is a digit
            else if (!BA.onlyNumericDouble(amount))
            {
                JOptionPane.showMessageDialog(this, "ERROR: Amount needs to be a digit (can include decimal).");
                return;
            }

            // Do the transaction, check for errors
            int result = BA.etransfer(Double.parseDouble(amount), email, customer, accountFrom);

            // Invalid email
            if (result == 1)
            {
                JOptionPane.showMessageDialog(this, "ERROR: Invalid email.");
            }

            // Insufficient funds
            else if (result == 2)
            {
                JOptionPane.showMessageDialog(this, "ERROR: You do not have sufficient funds in selected account.");
            }

            // Success (external transfer)
            else if (result == 3)
            {
                JOptionPane.showMessageDialog(this, "SUCCESS: You transferred $" + amount +
                        " to user with email " + email + ".\n This is an external transfer (i.e., user is not a part of BCS).");
                this.setVisible(false);
                home = new HomePage(login, BA, customer);
                home.setVisible(true);
            }

            // E-transfer limit
            else if (result == 4)
            {
                JOptionPane.showMessageDialog(this, "ERROR: E-transfer limit is $1,000.");
            }

            // Minimum transfer amount
            else if (result == 5)
            {
                JOptionPane.showMessageDialog(this, "ERROR: Minimum transfer amount is $0.5");
            }

            // Customer tries to etransfer themselves
            else if (result == 6)
            {
                JOptionPane.showMessageDialog(this, "ERROR: You cannot e-transfer to yourself. Try transfer funds instead.");
            }

            // Success (internal transfer)
            else if (result == 0)
            {
                JOptionPane.showMessageDialog(this, "SUCCESS: You transferred $" + amount +
                        " to user with email " + email + ".\n This user has an account with BCS, and the funds will " +
                        "be auto-deposited to their chequing account.");
                this.setVisible(false);
                home = new HomePage(login, BA, customer);
                home.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "An error occurred.");
            }

        }

    }

}
