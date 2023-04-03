package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// This class is for the Profile Settings page
public class ProfileSettingPage extends JFrame implements ActionListener
{
    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    BankAutomated BA;
    HomePage home;
    CA customer;

    // GUI Components for ProfileSettingPage
    private final JButton completeButton;
    private final JButton backToHome;
    private final JTextField addressField;
    private final JTextField phoneNumField;
    private final JTextField emailField;

    /*
     * ProfileSettingPage Constructor
     * @param home HomePage object
     * @param BA BankAutomated object
     * @param customer CA object
     * 
     */
    public ProfileSettingPage(HomePage home, BankAutomated BA, CA customer)
    {

        // Set title of the frame
        this.setTitle("Profile Settings");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 30);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // GUI Components for email
        JLabel email = new JLabel("Update email:");
        email.setFont(labels);
        email.setBorder(emptyBorder);
        email.setForeground(Color.black);
        email.setBounds(290,150,300,40);
        this.add(email);

        // GUI Components for email field
        emailField = new JTextField(150);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setBorder(border);
        emailField.setBounds(690, 150, 350, 40);
        this.add(emailField);

        // GUI Components for phone number
        JLabel phoneNum = new JLabel("Update phone #:");
        phoneNum.setFont(labels);
        phoneNum.setBorder(emptyBorder);
        phoneNum.setForeground(Color.black);
        phoneNum.setBounds(290,275,300,40);
        this.add(phoneNum);

        // GUI Components for phone number field
        phoneNumField = new JTextField(150);
        phoneNumField.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneNumField.setBorder(border);
        phoneNumField.setBounds(690, 275, 350, 40);
        this.add(phoneNumField);

        // GUI Components for address
        JLabel address = new JLabel("Update address:");
        address.setFont(labels);
        address.setBorder(emptyBorder);
        address.setForeground(Color.black);
        address.setBounds(290,400,300,40);
        this.add(address);

        // GUI Components for address field
        addressField = new JTextField(150);
        addressField.setFont(new Font("Arial", Font.PLAIN, 20));
        addressField.setBounds(690, 400, 350, 40);
        addressField.setBorder(border);
        this.add(addressField);

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

        // Window Listener, logout when window is closed
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

        // Set the background color of the frame
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

        // Draw the background
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
        g2.drawString("Edit Profile Settings", 25, 110);
    }

    /*
     * actionPerformed method
     * @param e ActionEvent object
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // back to home button clicked, go back to home page
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }

        // complete button clicked, update profile settings
        else if (e.getSource() == completeButton)
        {
            // Get the new information
            String email = emailField.getText();
            String phoneNum = phoneNumField.getText();
            String address = addressField.getText();

            // Check if any changes were made
            if (email.equals("") && phoneNum.equals("") && address.equals(""))
            {
                JOptionPane.showMessageDialog(this, "No changes were made.");
                return;
            }

            // Check if the new email is valid
            if (!email.equals(""))
            {
                // Check if the email is valid
                if (!BA.validEmail(email))
                {
                    JOptionPane.showMessageDialog(this,"New email is invalid.");
                    emailField.setText("");
                    return;
                }
                // Check if the email is already registered
                else if (BA.existingEmail(email))
                {
                    JOptionPane.showMessageDialog(this, "Email is already registered with BCS.");
                    emailField.setText("");
                    return;
                }
                // Email is valid
                else
                {
                    customer.setEmail(email);
                }
            }

            // Check if the new phone number is valid
            if (!phoneNum.equals(""))
            {
                // Check if the phone number is valid
                if (phoneNum.length() != 10 || !BA.onlyNumeric(phoneNum))
                {
                    JOptionPane.showMessageDialog(this, "Phone number is invalid.");
                    phoneNumField.setText("");
                    return;
                }

                // set the new phone number
                else
                {
                    customer.setPhoneNum(phoneNum);
                }
            }

            // Set the new address
            if (!address.equals(""))
            {
                customer.setAddress(address);
            }

            JOptionPane.showMessageDialog(this, "Your account has been updated.");
            this.setVisible(false);
            home.setVisible(true);
        }

    }
    
}
