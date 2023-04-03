package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// This class is for the Privacy Settings page
public class PrivacySettingPage extends JFrame implements ActionListener
{

    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // GUI for change password
    JPanel changePasswordPanel = new JPanel();
    private final JPasswordField oldPasswordField;
    private final JPasswordField newPasswordField_one;
    private final JPasswordField newPasswordField_two;

    // Objects
    BankAutomated BA;
    HomePage home;
    CA customer;

    // GUI Components for PrivacySettingPage
    private final JButton backToHome;
    private final JButton completeButton;
    private final JButton changePasswordButton;
    private final JComboBox<String> selectTrack; private final JComboBox<String> selectDataCollection;
    private final JComboBox<String> selectSensitiveData; private final JComboBox<String> selectKeyLogger;

    /*
     * PrivacySettingPage Constructor
     * @param home HomePage object
     * @param BA BankAutomated object
     * @param customer CA object
     * 
     */
    public PrivacySettingPage(HomePage home, BankAutomated BA, CA customer)
    {
        // Set title of the frame
        this.setTitle("Privacy Settings");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 22);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        String[] options = {"Select...", "Allow", "Don't Allow"};
        boolean custLoc = customer.getLocationServices(); int indexLoc;
        if (custLoc) {indexLoc = 1;} else {indexLoc = 2;}
        boolean custDataColl = customer.getRequireDataCollection(); int indexData;
        if (custDataColl) {indexData = 1;} else {indexData = 2;}
        boolean custSensitiveData = customer.getSensitiveDataCollection(); int indexSensitiveData;
        if (custSensitiveData) {indexSensitiveData = 1;} else {indexSensitiveData = 2;}
        boolean custKeyLogger = customer.getKeyLogger(); int indexKeyLogger;
        if (custKeyLogger) {indexKeyLogger = 1;} else {indexKeyLogger = 2;}

        // GUI Components for location services
        JLabel allowTrack = new JLabel("Allow us to access your location:");
        allowTrack.setFont(labels);
        allowTrack.setBorder(emptyBorder);
        allowTrack.setForeground(Color.black);
        allowTrack.setBounds(200,150,375,40);
        this.add(allowTrack);

        // GUI Components for setting location services to true or false
        selectTrack = new JComboBox<>(options);
        selectTrack.setSelectedIndex(indexLoc);
        selectTrack.setFont(new Font("Arial", Font.PLAIN, 20));
        selectTrack.setBounds(600, 150, 500, 40);
        selectTrack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectTrack.addActionListener(this);
        this.add(selectTrack);

        // GUI Components for change password
        changePasswordPanel.add(new JLabel("Old Password:"));
        oldPasswordField = new JPasswordField();
        changePasswordPanel.add(oldPasswordField);
        changePasswordPanel.add(new JLabel("New Password:"));
        newPasswordField_one = new JPasswordField();
        changePasswordPanel.add(newPasswordField_one);
        changePasswordPanel.add(new JLabel("New Password Again:"));
        newPasswordField_two = new JPasswordField();
        changePasswordPanel.add(newPasswordField_two);

        // GUI Components for allowing data collection
        JLabel allowBasicData = new JLabel("Allow us to access necessary data:");
        allowBasicData.setFont(labels);
        allowBasicData.setBorder(emptyBorder);
        allowBasicData.setForeground(Color.black);
        allowBasicData.setBounds(200,250,375,40);
        this.add(allowBasicData);

        // GUI Components for setting data collection to true or false
        selectDataCollection = new JComboBox<>(options);
        selectDataCollection.setSelectedIndex(indexData);
        selectDataCollection.setFont(new Font("Arial", Font.PLAIN, 20));
        selectDataCollection.setBounds(600, 250, 500, 40);
        selectDataCollection.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectDataCollection.addActionListener(this);
        this.add(selectDataCollection);

        // GUI Components for allowing sensitive data collection
        JLabel allowSensitiveData = new JLabel("Allow us to access sensitive data:");
        allowSensitiveData.setFont(labels);
        allowSensitiveData.setBorder(emptyBorder);
        allowSensitiveData.setForeground(Color.black);
        allowSensitiveData.setBounds(200,350,375,40);
        this.add(allowSensitiveData);

        // GUI Components for setting sensitive data collection to true or false
        selectSensitiveData = new JComboBox<>(options);
        selectSensitiveData.setSelectedIndex(indexSensitiveData);
        selectSensitiveData.setFont(new Font("Arial", Font.PLAIN, 20));
        selectSensitiveData.setBounds(600, 350, 500, 40);
        selectSensitiveData.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectSensitiveData.addActionListener(this);
        this.add(selectSensitiveData);

        // GUI Components for allowing key logger
        JLabel allowKeyLogger = new JLabel("Allow us to use a key logger:");
        allowKeyLogger.setFont(labels);
        allowKeyLogger.setBorder(emptyBorder);
        allowKeyLogger.setForeground(Color.black);
        allowKeyLogger.setBounds(200,450,375,40);
        this.add(allowKeyLogger);

        // GUI Components for setting key logger to true or false
        selectKeyLogger = new JComboBox<>(options);
        selectKeyLogger.setSelectedIndex(indexKeyLogger);
        selectKeyLogger.setFont(new Font("Arial", Font.PLAIN, 20));
        selectKeyLogger.setBounds(600, 450, 500, 40);
        selectKeyLogger.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectKeyLogger.addActionListener(this);
        this.add(selectKeyLogger);

        // GUI Components for saving preferences
        completeButton = new JButton("Save Preferences");
        completeButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
        completeButton.setBounds(275, 525, 350, 40);
        completeButton.setBackground(Color.black);
        completeButton.setForeground(Color.white);
        completeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completeButton.setBorder(emptyBorder);
        completeButton.addActionListener(this);
        this.add(completeButton);

        // GUI Components for changing password
        changePasswordPanel.setLayout(new GridLayout(0, 1));
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
        changePasswordButton.setBounds(650, 525, 350, 40);
        changePasswordButton.setBackground(Color.black);
        changePasswordButton.setForeground(Color.white);
        changePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changePasswordButton.setBorder(emptyBorder);
        changePasswordButton.addActionListener(this);
        this.add(changePasswordButton);

        // GUI Components for going back to home
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

        // Windows listener for logging out when the window is closed
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

        // GUI Components for the frame
        this.getContentPane().setBackground(Color.white);
        this.getRootPane().setDefaultButton(completeButton);
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

        // set font
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Select Your Privacy Settings", 25, 110);
    }

    /*
     * Method to handle the action events
     * @param e ActionEvent object
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // if the user clicks the back to home button
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }

        // If the user clicks the complete button
        else if (e.getSource() == changePasswordButton)
        {
            // make JPanel changePassword visible
            boolean changePassword = false;
            boolean isSelected = false;

            // GUI Components for changing password
            do {

                // GUI Components for changing password
                String[] options = {"Change", "Cancel", "Show Passwords"};
                int result = JOptionPane.showOptionDialog(null, changePasswordPanel, "Change Password", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword_one = new String(newPasswordField_one.getPassword());
                String newPassword_two = new String(newPasswordField_two.getPassword()); 

                // If the user clicks the change button
                if (result == 0) {

                    // If the user does not fill in all fields
                    if (oldPassword.equals("") || newPassword_one.equals("") || newPassword_two.equals("")) {

                        JOptionPane.showMessageDialog(this, "Please fill in all fields.");

                    // User enters the correct old password
                    } else if (oldPassword.equals(customer.getPassword())) {

                        // If the user enters the same new password twice
                        if (oldPassword.equals(newPassword_one) || oldPassword.equals(newPassword_two)) {

                            JOptionPane.showMessageDialog(this, "New password cannot be the same as the old password.");

                        // If the user enters two different new passwords
                        } else if (newPassword_one.equals(newPassword_two)) {

                            // If the new password is invalid
                            if (!BA.validPassword(newPassword_two)) {

                                JOptionPane.showMessageDialog(this, "Password is invalid. Password must contain:\n" +
                                        "\n-At least one lowercase and one uppercase character\n-At least one number\n-At least one" +
                                        " special character\n-At least 8 characters overall.");

                            // If the new password is valid
                            } else {

                                BA.changePassword(customer, newPassword_two);

                                JOptionPane.showMessageDialog(this, "Password changed successfully.");

                                changePassword = true;

                                break;

                            }

                        // If the user enters two different new passwords
                        } else {

                            JOptionPane.showMessageDialog(this, "New passwords do not match.");

                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "Old password is incorrect.");

                    }

                    // If the user clicks the cancel button
                } else if (result == 1) {

                    int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to cancel password change?", "Password Change exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    
                    if (choice == JOptionPane.YES_OPTION) {

                        break;

                    }
    
                    // If the user clicks the show passwords button
                } else if (result == 2) {

                    // If the passwords are not shown
                    if (!isSelected) {

                        oldPasswordField.setEchoChar((char) 0);
                        newPasswordField_one.setEchoChar((char) 0);
                        newPasswordField_two.setEchoChar((char) 0);

                        isSelected = true;

                    // If the passwords are shown
                    } else {

                        oldPasswordField.setEchoChar('*');
                        newPasswordField_one.setEchoChar('*');
                        newPasswordField_two.setEchoChar('*');

                        isSelected = false;

                    }

                }

            }while (!changePassword);

        // If the user clicks the complete button
        } else if (e.getSource() == completeButton)
        {

            // If the user does not select all preferences
            if(selectTrack.getSelectedIndex()==0 || selectDataCollection.getSelectedIndex()==0 ||
                    selectSensitiveData.getSelectedIndex()==0 || selectKeyLogger.getSelectedIndex()==0)
            {
                JOptionPane.showMessageDialog(this, "Please select all preferences before saving.");
            }

            // If the user selects all preferences
            else
            {
                if (selectTrack.getSelectedIndex() == 1)
                {
                    customer.setLocationServices(true);
                }
                else if (selectTrack.getSelectedIndex() == 2)
                {
                    customer.setLocationServices(false);
                }

                if (selectDataCollection.getSelectedIndex() == 1)
                {
                    customer.setRequireDataCollection(true);
                }
                else if (selectDataCollection.getSelectedIndex() == 2)
                {
                    customer.setRequireDataCollection(false);
                }

                if (selectSensitiveData.getSelectedIndex() == 1)
                {
                    customer.setSensitiveDataCollection(true);
                }
                else if (selectSensitiveData.getSelectedIndex() == 2)
                {
                    customer.setSensitiveDataCollection(false);
                }

                if (selectKeyLogger.getSelectedIndex() == 1)
                {
                    customer.setKeyLogger(true);
                }
                else if (selectKeyLogger.getSelectedIndex() == 2)
                {
                    customer.setKeyLogger(false);
                }

                JOptionPane.showMessageDialog(this, "Your privacy settings have been updated.");

                this.setVisible(false);
                home.setVisible(true);
            }

        }

    }
    
}
