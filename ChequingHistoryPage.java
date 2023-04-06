import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * This class is the graphical implementation of the customer's chequing account transaction history
 * It displays the last 6 transactions the customer did with their chequing account.
 */
public class ChequingHistoryPage extends JFrame implements ActionListener
{
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;
    BankAutomated BA;
    HomePage home;
    CA customer;
    private final JButton backToHome;
    
     /**
     * ChequingHistoryPage Constructor. This constructor creates all the frame specifications for when the customer
     * clicks on their chequing account (i.e., buttons, display setup, etc.). It retrieves the customer's latest
     * 6 chequing transaction and formats and displays them.
     * @param home HomePage object to return to when the user wants to
     * @param BA BankAutomated object to process the logout if the user exits the system
     * @param customer CA object, the customer that is logged in
     */
    public ChequingHistoryPage(HomePage home, BankAutomated BA, CA customer)
    {
        this.setTitle("Chequing History");
        this.setLayout(null);
        this.home = home;
        this.BA = BA;
        this.customer = customer;

        Font labels = new Font("Raleway", Font.PLAIN, 22);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        Color bg = new Color(214, 215, 215);

        ArrayList<JTextArea> blocks = new ArrayList<>();
        blocks.add(new JTextArea()); blocks.add(new JTextArea()); blocks.add(new JTextArea());
        blocks.add(new JTextArea()); blocks.add(new JTextArea()); blocks.add(new JTextArea());

        int i = 0;
        if (customer.getSavingsHist().size() != 0)
        {
            for (Transaction transaction : customer.getChequingHist())
            {
                if (transaction.getSender().equals(customer.getEmail()) && transaction.getAccountFrom().equals("Chequing"))
                {
                    String type = "E-Transfer";
                    String id = String.valueOf(transaction.getId());
                    String receiverEmail = transaction.getReceiver();
                    String amount = "$ -" + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getSenderRemaining()*100.0)/100.0;

                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  Receiver (Email): " + receiverEmail +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);
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
                else if (transaction.getSender().equals(customer.getFirstName() + " " + customer.getLastName()) && transaction.getAccountFrom().equals("Chequing"))
                {
                    String type = "Bank Transfer";
                    String id = String.valueOf(transaction.getId());
                    String receiverAcc = transaction.getReceiver();
                    String amount = "$ -" + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getSenderRemaining()*100.0)/100.0;

                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  Receiver (Acc#): " + receiverAcc +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining +  "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);
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
                else if (transaction.getSender().equals("Chequing"))
                {
                    String type = "Fund Transfer";
                    String id = String.valueOf(transaction.getId());
                    String fromTo = "Chequing to Savings";
                    String amount = "$ -" + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getSenderRemaining() * 100.0)/100.0;

                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  From, To: " + fromTo +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);
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
                else if (transaction.getSender().equals("Savings"))
                {
                    String type = "Fund Transfer";
                    String id = String.valueOf(transaction.getId());
                    String fromTo = "Savings to Chequing";
                    String amount = "$  " + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getReceiverRemaining()*100.0)/100.0;

                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  From, To: " + fromTo +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);
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
                else if (transaction.getReceiver().equals(customer.getBankNumber()))
                {
                    String type = "Bank Transfer";
                    String id = String.valueOf(transaction.getId());
                    String senderName = transaction.getSender();
                    String amount = "$  " + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getReceiverRemaining()*100.0)/100.0;

                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  Sender's Name: " + senderName +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining + "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);

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
                else if (transaction.getReceiver().equals(customer.getEmail()))
                {
                    String type = "E-Transfer";
                    String id = String.valueOf(transaction.getId());
                    String senderName = transaction.getSender();
                    String amount = "$  " + transaction.getAmount();
                    String remaining = "$ " + Math.round(transaction.getReceiverRemaining()*100.0)/100.0;

                    JTextArea trans = blocks.get(i);
                    trans.setText("  Transaction ID: " + id + "\n  Sender's Email: " + senderName +
                            "\n  Amount: " + amount + "\n  Remaining Balance: " + remaining +  "\n  Type: " + type);
                    trans.setBackground(Color.white);
                    trans.setBorder(border);
                    trans.setEditable(false);
                    trans.setFont(labels);

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
        else
        {
            JLabel noHist = new JLabel("No Transaction History");
            noHist.setFont(new Font("Raleway", Font.BOLD, 50));
            noHist.setBounds(375, 300, 700, 70);
            this.add(noHist);
        }

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
        this.getContentPane().setBackground(bg);
        this.getRootPane().setDefaultButton(backToHome);
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

        // Paint the background
        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(230, 30, 30);
        Color myBlack = new Color(160, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Chequing Transaction History", 25, 110);
    }

    /**
     * actionPerformed method (implementing ActionListener). When the "Back To Home" button is clicked, it sends
     * the customer back to their homepage.
     * @param e ActionEvent object, which listens and keeps track of any button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If the back to home button is pressed
        if (e.getSource() == backToHome) {
            this.setVisible(false);
            home.setVisible(true);
        }
        
    }

}
