package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

// This class is for the Savings History page
public class SavingsHistoryPage extends JFrame implements ActionListener
{
    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    BankAutomated BA;
    HomePage home;
    CA customer;

    // GUI Components for SavingsHistoryPage
    private final JButton backToHome;

    /*
     * SavingsHistoryPage Constructor
     * @param home HomePage object
     * @param BA BankAutomated object
     * @param customer CA object
     * 
     */
    public SavingsHistoryPage(HomePage home, BankAutomated BA, CA customer)
    {

        // Set title of the frame
        this.setTitle("Savings History");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        // GUI Components
        Font labels = new Font("Raleway", Font.PLAIN, 22);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        Color bg = new Color(214, 215, 215);

        // GUI Components for savings history blocks
        ArrayList<JTextArea> blocks = new ArrayList<>();
        blocks.add(new JTextArea()); blocks.add(new JTextArea()); blocks.add(new JTextArea());
        blocks.add(new JTextArea()); blocks.add(new JTextArea()); blocks.add(new JTextArea());

        // If there are transactions in the customer savings history
        int i = 0;
        if (customer.getSavingsHist().size() != 0)
        {
            // Loop through the customer savings history
            for (Transaction transaction : customer.getSavingsHist())
            {
                // If the transaction is a transfer to another account (E-Transfer)
                if (transaction.getSender().equals(customer.getEmail()) && transaction.getAccountFrom().equals("Savings"))
                {

                    // Set the transaction details
                    String type = "E-Transfer";
                    String id = String.valueOf(transaction.getId());
                    String receiverEmail = transaction.getReceiver();
                    String amount = "$ -" + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getSenderRemaining()*100.0)/100.0;

                    // Write to transaction block
                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  Receiver (Email): " + receiverEmail +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);

                    // Set the bounds of the transaction block
                    if (i == 0)
                    {
                        trans.setBounds(35, 150, 600, 150);
                    }
                    else if (i == 1)
                    {
                        trans.setBounds(635, 150, 600, 150);
                    }
                    else if (i == 2)
                    {
                        trans.setBounds(35, 300, 600, 150);
                    }
                    else if (i == 3)
                    {
                        trans.setBounds(635, 300, 600, 150);
                    }
                    else if (i == 4)
                    {
                        trans.setBounds(35, 450, 600, 150);
                    }
                    else if (i == 5)
                    {
                        trans.setBounds(635, 450, 600, 150);
                    }
                    this.add(trans);
                }

                // If the transaction is a transfer to another account (Bank Transfer)
                else if (transaction.getSender().equals(customer.getFirstName() + " " + customer.getLastName()) && transaction.getAccountFrom().equals("Savings"))
                {

                    // Set the transaction details
                    String type = "Bank Transfer";
                    String id = String.valueOf(transaction.getId());
                    String receiverAcc = transaction.getReceiver();
                    String amount = "$ -" + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getSenderRemaining()*100.0)/100.0;

                    // Write to transaction block
                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  Receiver (Acc#): " + receiverAcc +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);

                    // Set the bounds of the transaction block
                    if (i == 0)
                    {
                        trans.setBounds(35, 150, 600, 150);
                    }
                    else if (i == 1)
                    {
                        trans.setBounds(635, 150, 600, 150);
                    }
                    else if (i == 2)
                    {
                        trans.setBounds(35, 300, 600, 150);
                    }
                    else if (i == 3)
                    {
                        trans.setBounds(635, 300, 600, 150);
                    }
                    else if (i == 4)
                    {
                        trans.setBounds(35, 450, 600, 150);
                    }
                    else if (i == 5)
                    {
                        trans.setBounds(635, 450, 600, 150);
                    }
                    this.add(trans);
                }

                // Fund transfer from chequing to savings
                else if (transaction.getSender().equals("Chequing"))
                {
                    // Set the transaction details
                    String type = "Fund Transfer";
                    String id = String.valueOf(transaction.getId());
                    String fromTo = "Chequing to Savings";
                    String amount = "$  " + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getReceiverRemaining()*100.0)/100.0;

                    // Write to transaction block
                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  From, To: " + fromTo +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);

                    // Set the bounds of the transaction block
                    if (i == 0)
                    {
                        trans.setBounds(35, 150, 600, 150);
                    }
                    else if (i == 1)
                    {
                        trans.setBounds(635, 150, 600, 150);
                    }
                    else if (i == 2)
                    {
                        trans.setBounds(35, 300, 600, 150);
                    }
                    else if (i == 3)
                    {
                        trans.setBounds(635, 300, 600, 150);
                    }
                    else if (i == 4)
                    {
                        trans.setBounds(35, 450, 600, 150);
                    }
                    else if (i == 5)
                    {
                        trans.setBounds(635, 450, 600, 150);
                    }
                    this.add(trans);
                }

                // Fund transfer from savings to chequing 
                else if (transaction.getSender().equals("Savings"))
                {

                    // Set the transaction details
                    String type = "Fund Transfer";
                    String id = String.valueOf(transaction.getId());
                    String fromTo = "Savings to Chequing";
                    String amount = "$ -" + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getSenderRemaining()*100.0)/100.0;

                    // Write to transaction block
                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  From, To: " + fromTo +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);

                    // Set the bounds of the transaction block
                    if (i == 0)
                    {
                        trans.setBounds(35, 150, 600, 150);
                    }
                    else if (i == 1)
                    {
                        trans.setBounds(635, 150, 600, 150);
                    }
                    else if (i == 2)
                    {
                        trans.setBounds(35, 300, 600, 150);
                    }
                    else if (i == 3)
                    {
                        trans.setBounds(635, 300, 600, 150);
                    }
                    else if (i == 4)
                    {
                        trans.setBounds(35, 450, 600, 150);
                    }
                    else if (i == 5)
                    {
                        trans.setBounds(635, 450, 600, 150);
                    }
                    this.add(trans);
                }

                i++;

                if (i >= 6)
                {
                    break;
                }
            }
        }

        // If there are no transactions
        else
        {
            JLabel noHist = new JLabel("No Transaction History");
            noHist.setFont(new Font("Raleway", Font.BOLD, 50));
            noHist.setBounds(375, 300, 700, 70);
            this.add(noHist);
        }

        // Back to home button
        backToHome = new JButton("Back to Home");
        backToHome.setFont(new Font("SansSerif", Font.PLAIN, 22));
        backToHome.setBounds(450, 600, 350, 50);
        backToHome.setBackground(Color.white);
        backToHome.setForeground(new Color(57, 107, 170));
        backToHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backToHome.setContentAreaFilled(false);
        backToHome.setFocusPainted(false);
        backToHome.setBorder(emptyBorder);
        backToHome.setContentAreaFilled(false);
        backToHome.addActionListener(this);
        this.add(backToHome);

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

        // Set the frame properties
        this.getContentPane().setBackground(bg);
        this.getRootPane().setDefaultButton(backToHome);
        this.setSize(WIDTH, LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /*
     * Paints the background of the frame
     * @param g the graphics object
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

        // Set font
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Savings Transaction History", 25, 110);
        
    }

    /*
     * Action listener for the back to home button
     * @param e the action event
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If the back to home button is pressed go back to the home screen
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }

    }

}
