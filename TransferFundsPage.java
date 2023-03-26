import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class TransferFundsPage extends JFrame implements ActionListener
{
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    BankAutomated BA;
    HomePage home;
    CA customer;
    LoginPage login;

    JButton completeButton;
    JButton backToHome;
    private final JTextField amountField;
    private final JComboBox<String> fromToSelect;
    public TransferFundsPage(BankAutomated BA, HomePage home, CA customer, LoginPage login)
    {
        this.setTitle("Funds Transfer");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;
        this.login = login;

        Font labels = new Font("Raleway", Font.BOLD, 30);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        JLabel amount = new JLabel("Amount (in CAD):");
        amount.setFont(labels);
        amount.setBorder(emptyBorder);
        amount.setForeground(Color.black);
        amount.setBounds(290,250,300,40);
        this.add(amount);

        amountField = new JTextField(150);
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBorder(border);
        amountField.setBounds(690, 250, 350, 40);
        this.add(amountField);

        JLabel account = new JLabel("From, To:");
        account.setFont(labels);
        account.setBorder(emptyBorder);
        account.setForeground(Color.black);
        account.setBounds(290,350,300,40);
        this.add(account);

        String[] accounts = {"Select an option", "Chequing to Savings", "Savings to Chequing"};
        fromToSelect = new JComboBox<>(accounts);
        fromToSelect.setFont(new Font("Arial", Font.PLAIN, 20));
        fromToSelect.setBounds(690, 350, 350, 40);
        fromToSelect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fromToSelect.addActionListener(this);
        this.add(fromToSelect);

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
        this.setVisible(false);
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
        g2.drawString("Transfer Funds Between Your Accounts", 25, 110);
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
            String amount = amountField.getText();
            String fromTo = String.valueOf(fromToSelect.getSelectedItem());
            String from = "";
            String to = "";
            if (fromTo.equals("Chequing to Savings"))
            {
                from = "Chequing";
                to = "Savings";
            }
            else if (fromTo.equals("Savings to Chequing"))
            {
                from = "Savings";
                to = "Chequing";
            }

            if (amount.equals("") || fromToSelect.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "ERROR: All fields are required.");
                return;
            }
            else if (!BA.onlyNumericDouble(amount))
            {
                JOptionPane.showMessageDialog(this, "ERROR: Amount needs to be a digit (can include decimal).");
                return;
            }

            int result = BA.transferFunds(Double.parseDouble(amount), from, customer);
            if (result == 1)
            {
                JOptionPane.showMessageDialog(this, "ERROR: You do not have sufficient funds " +
                        "in your " + from + " account to complete this transaction.");
            }
            else if (result == 0)
            {
                JOptionPane.showMessageDialog(this, "SUCCESS: You transferred $" + amount +
                        " from your " + from + " account\n to your " + to + " account.");
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