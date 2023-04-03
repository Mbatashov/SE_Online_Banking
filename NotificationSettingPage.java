package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


// This class is for the Notification Settings page
public class NotificationSettingPage extends JFrame implements ActionListener
{

    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    BankAutomated BA;
    HomePage home;
    CA customer;

    // GUI Components for NotificationSettingPage
    private final JButton backToHome;
    private final JButton completeButton;
    private final JComboBox<String> selectNotification; private final JComboBox<String> selectNotification2;
    private final JComboBox<String> selectNotification3; private final JComboBox<String> selectNotification4;

    /*
     * NotificationSettingPage Constructor
     * @param home HomePage object
     * @param BA BankAutomated object
     * @param customer CA object
     */
    public NotificationSettingPage(HomePage home, BankAutomated BA, CA customer)
    {

        // Set title of the frame
        this.setTitle("Notification Settings");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 22);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        String[] options = {"Select Notification Method", "Email", "SMS", "Don't Notify"};
        String[] options2 = {"Select Notification Method", "Email", "SMS"};
        int custRequestReply = customer.getRequestReplies();
        if (custRequestReply == 0) { custRequestReply = 3; }
        int custReportReply = customer.getReportReplies();
        if (custReportReply == 0) { custReportReply = 3; }
        int custNewsletter = customer.getNewsletterSubscription();
        if (custNewsletter == 0) { custNewsletter = 3; }
        int custThreshold = customer.getBigPayment();
        if (custThreshold == 0) { custThreshold = 3; }

        // GUI Components for back to home button
        JLabel requestNotif = new JLabel("Notification for request reply:");
        requestNotif.setFont(labels);
        requestNotif.setBorder(emptyBorder);
        requestNotif.setForeground(Color.black);
        requestNotif.setBounds(225,150,350,40);
        this.add(requestNotif);

        // GUI Components for select notification options 
        selectNotification = new JComboBox<>(options2);
        selectNotification.setSelectedIndex(custRequestReply);
        selectNotification.setFont(new Font("Arial", Font.PLAIN, 20));
        selectNotification.setBounds(575, 150, 500, 40);
        selectNotification.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectNotification.addActionListener(this);
        this.add(selectNotification);

        // JLABEL for report reply notification
        JLabel reportNotif = new JLabel("Notification for report reply:");
        reportNotif.setFont(labels);
        reportNotif.setBorder(emptyBorder);
        reportNotif.setForeground(Color.black);
        reportNotif.setBounds(225,250,350,40);
        this.add(reportNotif);

        // JCOMBOBOX for report reply notification
        selectNotification2 = new JComboBox<>(options2);
        selectNotification2.setSelectedIndex(custReportReply);
        selectNotification2.setFont(new Font("Arial", Font.PLAIN, 20));
        selectNotification2.setBounds(575, 250, 500, 40);
        selectNotification2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectNotification2.addActionListener(this);
        this.add(selectNotification2);

        // JLABEL for newsletter and special deals
        JLabel newsletter = new JLabel("Newsletter and special deals:");
        newsletter.setFont(labels);
        newsletter.setBorder(emptyBorder);
        newsletter.setForeground(Color.black);
        newsletter.setBounds(225,350,350,40);
        this.add(newsletter);

        // JCOMBOBOX for newsletter and special deals
        selectNotification3 = new JComboBox<>(options);
        selectNotification3.setSelectedIndex(custNewsletter);
        selectNotification3.setFont(new Font("Arial", Font.PLAIN, 20));
        selectNotification3.setBounds(575, 350, 500, 40);
        selectNotification3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectNotification3.addActionListener(this);
        this.add(selectNotification3);

        // JLABEL for payments above threshold
        JLabel threshold = new JLabel("Payments above threshold:");
        threshold.setFont(labels);
        threshold.setBorder(emptyBorder);
        threshold.setForeground(Color.black);
        threshold.setBounds(225,450,350,40);
        this.add(threshold);

        // JCOMBOBOX for payments above threshold
        selectNotification4 = new JComboBox<>(options);
        selectNotification4.setSelectedIndex(custThreshold);
        selectNotification4.setFont(new Font("Arial", Font.PLAIN, 20));
        selectNotification4.setBounds(575, 450, 500, 40);
        selectNotification4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectNotification4.addActionListener(this);
        this.add(selectNotification4);

        // JBUTTON for saving preferences
        completeButton = new JButton("Save Preferences");
        completeButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
        completeButton.setBounds(475, 525, 350, 40);
        completeButton.setBackground(Color.black);
        completeButton.setForeground(Color.white);
        completeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completeButton.setBorder(emptyBorder);
        completeButton.addActionListener(this);
        this.add(completeButton);

        // JBUTTON for going back to home page
        backToHome = new JButton("Back to Home");
        backToHome.setFont(new Font("SansSerif", Font.PLAIN, 22));
        backToHome.setBounds(475, 575, 350, 50);
        backToHome.setBackground(Color.white);
        backToHome.setForeground(new Color(57, 107, 170));
        backToHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backToHome.setContentAreaFilled(false);
        backToHome.setFocusPainted(false);
        backToHome.setBorder(emptyBorder);
        backToHome.setContentAreaFilled(false);
        backToHome.addActionListener(this);
        this.add(backToHome);

        // Window listener for closing the window, logout and exit
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

        // JLABEL for the title
        this.getContentPane().setBackground(Color.white);
        this.getRootPane().setDefaultButton(completeButton);
        this.setSize(WIDTH, LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /*
     * Method to paint the background of the window
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

        // font
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Select Your Notification Preferences", 25, 110);
    }

    /*
     * Method to handle the action events
     * @param e ActionEvent object
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // If the back to home button is clicked
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }

        // If the complete button is clicked
        else if (e.getSource() == completeButton)
        {

            // Check if all preferences have been selected
            if(selectNotification.getSelectedIndex()==0 || selectNotification2.getSelectedIndex()==0 ||
                    selectNotification3.getSelectedIndex()==0 || selectNotification4.getSelectedIndex()==0)
            {
                JOptionPane.showMessageDialog(this, "Please select all preferences before saving.");
            }

            // Update preferences based on the selection
            else
            {
                if (selectNotification.getSelectedIndex()==1)
                {
                    customer.setRequestReplies(1);
                }
                else if (selectNotification.getSelectedIndex()==2)
                {
                    customer.setRequestReplies(2);
                }
                else if (selectNotification.getSelectedIndex()==3)
                {
                    customer.setRequestReplies(0);
                }

                if (selectNotification2.getSelectedIndex()==1)
                {
                    customer.setReportReplies(1);
                }
                else if (selectNotification2.getSelectedIndex()==2)
                {
                    customer.setReportReplies(2);
                }
                else if (selectNotification2.getSelectedIndex()==3)
                {
                    customer.setReportReplies(0);
                }

                if (selectNotification3.getSelectedIndex()==1)
                {
                    customer.setNewsletterSubscription(1);
                }
                else if (selectNotification3.getSelectedIndex()==2)
                {
                    customer.setNewsletterSubscription(2);
                }
                else if (selectNotification3.getSelectedIndex()==3)
                {
                    customer.setNewsletterSubscription(0);
                }

                if (selectNotification4.getSelectedIndex()==1)
                {
                    customer.setBigPayment(1);
                }
                else if (selectNotification4.getSelectedIndex()==2)
                {
                    customer.setBigPayment(2);
                }
                else if (selectNotification4.getSelectedIndex()==3)
                {
                    customer.setBigPayment(0);
                }
                JOptionPane.showMessageDialog(this, "Your notification settings have been updated.");

                this.setVisible(false);
                home.setVisible(true);
            }
        }
    }
}
