import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ETransferPage extends JFrame implements ActionListener
{
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    BankAutomated BA;
    HomePage home;
    CA customer;
    LoginPage login;
    private final JButton backToHome;
    private final JTextField emailField;
    private final JTextField amountField;
    private final JComboBox<String> selectAccount;
    private final JButton completeButton;

    public ETransferPage(BankAutomated BA, HomePage home, CA customer, LoginPage login)
    {
        this.setTitle("Make an E-transfer");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        Font labels = new Font("Raleway", Font.BOLD, 30);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        JLabel contact = new JLabel("Receiver's Email:");
        contact.setFont(labels);
        contact.setBorder(emptyBorder);
        contact.setForeground(Color.black);
        contact.setBounds(350,200,300,40);
        this.add(contact);

        emailField = new JTextField(150);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setBorder(border);
        emailField.setBounds(650, 200, 350, 40);
        this.add(emailField);

        JLabel amount = new JLabel("Amount (in CAD):");
        amount.setFont(labels);
        amount.setBorder(emptyBorder);
        amount.setForeground(Color.black);
        amount.setBounds(350,325,300,40);
        this.add(amount);

        amountField = new JTextField(150);
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBorder(border);
        amountField.setBounds(650, 325, 350, 40);
        this.add(amountField);

        JLabel account = new JLabel("Account from:");
        account.setFont(labels);
        account.setBorder(emptyBorder);
        account.setForeground(Color.black);
        account.setBounds(350,450,300,40);
        this.add(account);

        String[] accounts = {"Select Account", "Chequing", "Savings"};
        selectAccount = new JComboBox<>(accounts);
        selectAccount.setFont(new Font("Arial", Font.PLAIN, 20));
        selectAccount.setBounds(650, 450, 350, 40);
        selectAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectAccount.addActionListener(this);
        this.add(selectAccount);

        completeButton = new JButton("Complete Transaction");
        completeButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
        completeButton.setBounds(475, 525, 350, 40);
        completeButton.setBackground(Color.black);
        completeButton.setForeground(Color.white);
        completeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completeButton.setBorder(emptyBorder);
        completeButton.addActionListener(this);
        this.add(completeButton);

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
        this.getContentPane().setBackground(Color.white);
        this.getRootPane().setDefaultButton(completeButton);
        this.setSize(WIDTH, LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(230, 30, 30);
        Color myBlack = new Color(160, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("E-Transfer to contact", 25, 110);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }
        else if (e.getSource() == completeButton)
        {
            String email = emailField.getText();
            String amount = amountField.getText();
            String accountFrom = String.valueOf(selectAccount.getSelectedItem());
            if (email.equals("") || amount.equals("") || selectAccount.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "ERROR: All fields are required.");
                return;
            }
            else if (!BA.onlyNumericDouble(amount))
            {
                JOptionPane.showMessageDialog(this, "ERROR: Amount needs to be a digit (can include decimal).");
                return;
            }

            int result = BA.etransfer(Double.parseDouble(amount), email, customer, accountFrom);
            if (result == 1)
            {
                JOptionPane.showMessageDialog(this, "ERROR: Invalid email.");
            }
            else if (result == 2)
            {
                JOptionPane.showMessageDialog(this, "ERROR: You do not have sufficient funds in selected account.");
            }
            else if (result == 3)
            {
                JOptionPane.showMessageDialog(this, "SUCCESS: You transferred " + amount +
                        " to user with email " + email + ".\n This is an external transfer (i.e., user is not a part of BCS).");
                this.setVisible(false);
                home = new HomePage(login, BA, customer);
                home.setVisible(true);
            }
            else if (result == 4)
            {
                JOptionPane.showMessageDialog(this, "ERROR: E-transfer limit is $1,000.");
            }
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
