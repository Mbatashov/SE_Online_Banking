import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PrivacySettingPage extends JFrame implements ActionListener
{
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;
    JPanel changePasswordPanel = new JPanel();
    private final JPasswordField oldPasswordField;
    private final JPasswordField newPasswordField_one;
    private final JPasswordField newPasswordField_two;

    BankAutomated BA;
    HomePage home;
    CA customer;

    private final JButton backToHome;
    private final JButton completeButton;
    private final JButton changePasswordButton;
    private final JComboBox<String> selectTrack; private final JComboBox<String> selectDataCollection;
    private final JComboBox<String> selectSensitiveData; private final JComboBox<String> selectKeyLogger;
    public PrivacySettingPage(HomePage home, BankAutomated BA, CA customer)
    {
        this.setTitle("Privacy Settings");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

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

        JLabel allowTrack = new JLabel("Allow us to access your location:");
        allowTrack.setFont(labels);
        allowTrack.setBorder(emptyBorder);
        allowTrack.setForeground(Color.black);
        allowTrack.setBounds(200,150,375,40);
        this.add(allowTrack);

        selectTrack = new JComboBox<>(options);
        selectTrack.setSelectedIndex(indexLoc);
        selectTrack.setFont(new Font("Arial", Font.PLAIN, 20));
        selectTrack.setBounds(600, 150, 500, 40);
        selectTrack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectTrack.addActionListener(this);
        this.add(selectTrack);

        changePasswordPanel.add(new JLabel("Old Password:"));
        oldPasswordField = new JPasswordField();
        changePasswordPanel.add(oldPasswordField);
        changePasswordPanel.add(new JLabel("New Password:"));
        newPasswordField_one = new JPasswordField();
        changePasswordPanel.add(newPasswordField_one);
        changePasswordPanel.add(new JLabel("New Password Again:"));
        newPasswordField_two = new JPasswordField();
        changePasswordPanel.add(newPasswordField_two);

        JLabel allowBasicData = new JLabel("Allow us to access necessary data:");
        allowBasicData.setFont(labels);
        allowBasicData.setBorder(emptyBorder);
        allowBasicData.setForeground(Color.black);
        allowBasicData.setBounds(200,250,375,40);
        this.add(allowBasicData);

        selectDataCollection = new JComboBox<>(options);
        selectDataCollection.setSelectedIndex(indexData);
        selectDataCollection.setFont(new Font("Arial", Font.PLAIN, 20));
        selectDataCollection.setBounds(600, 250, 500, 40);
        selectDataCollection.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectDataCollection.addActionListener(this);
        this.add(selectDataCollection);

        JLabel allowSensitiveData = new JLabel("Allow us to access sensitive data:");
        allowSensitiveData.setFont(labels);
        allowSensitiveData.setBorder(emptyBorder);
        allowSensitiveData.setForeground(Color.black);
        allowSensitiveData.setBounds(200,350,375,40);
        this.add(allowSensitiveData);

        selectSensitiveData = new JComboBox<>(options);
        selectSensitiveData.setSelectedIndex(indexSensitiveData);
        selectSensitiveData.setFont(new Font("Arial", Font.PLAIN, 20));
        selectSensitiveData.setBounds(600, 350, 500, 40);
        selectSensitiveData.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectSensitiveData.addActionListener(this);
        this.add(selectSensitiveData);

        JLabel allowKeyLogger = new JLabel("Allow us to use a key logger:");
        allowKeyLogger.setFont(labels);
        allowKeyLogger.setBorder(emptyBorder);
        allowKeyLogger.setForeground(Color.black);
        allowKeyLogger.setBounds(200,450,375,40);
        this.add(allowKeyLogger);

        selectKeyLogger = new JComboBox<>(options);
        selectKeyLogger.setSelectedIndex(indexKeyLogger);
        selectKeyLogger.setFont(new Font("Arial", Font.PLAIN, 20));
        selectKeyLogger.setBounds(600, 450, 500, 40);
        selectKeyLogger.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectKeyLogger.addActionListener(this);
        this.add(selectKeyLogger);

        completeButton = new JButton("Save Preferences");
        completeButton.setFont(new Font("SansSerif", Font.PLAIN, 22));
        completeButton.setBounds(275, 525, 350, 40);
        completeButton.setBackground(Color.black);
        completeButton.setForeground(Color.white);
        completeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completeButton.setBorder(emptyBorder);
        completeButton.addActionListener(this);
        this.add(completeButton);

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
        g2.drawString("Select Your Privacy Settings", 25, 110);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }
        else if (e.getSource() == changePasswordButton)
        {
            // make JPanel changePassword visible
            boolean changePassword = false;
            boolean isSelected = false;

            do {

                String[] options = {"Change", "Cancel", "Show Passwords"};
                int result = JOptionPane.showOptionDialog(null, changePasswordPanel, "Change Password", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword_one = new String(newPasswordField_one.getPassword());
                String newPassword_two = new String(newPasswordField_two.getPassword()); 

                if (result == 0) {

                    if (oldPassword.equals("") || newPassword_one.equals("") || newPassword_two.equals("")) {

                        JOptionPane.showMessageDialog(this, "Please fill in all fields.");

                    } else if (oldPassword.equals(customer.getPassword())) {

                        if (oldPassword.equals(newPassword_one) || oldPassword.equals(newPassword_two)) {

                            JOptionPane.showMessageDialog(this, "New password cannot be the same as the old password.");

                        } else if (newPassword_one.equals(newPassword_two)) {

                            if (!BA.validPassword(newPassword_two)) {

                                JOptionPane.showMessageDialog(this, "Password is invalid. Password must contain:\n" +
                                        "\n-At least one lowercase and one uppercase character\n-At least one number\n-At least one" +
                                        " special character\n-At least 8 characters overall.");

                            } else {

                                BA.changePassword(customer, newPassword_two);

                                JOptionPane.showMessageDialog(this, "Password changed successfully.");

                                changePassword = true;

                                break;

                            }

                        } else {

                            JOptionPane.showMessageDialog(this, "New passwords do not match.");

                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "Old password is incorrect.");

                    }

                } else if (result == 1) {

                    int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to cancel password change?", "Password Change exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    
                    if (choice == JOptionPane.YES_OPTION) {

                        break;

                    }
    
                } else if (result == 2) {

                    if (!isSelected) {

                        oldPasswordField.setEchoChar((char) 0);
                        newPasswordField_one.setEchoChar((char) 0);
                        newPasswordField_two.setEchoChar((char) 0);

                        isSelected = true;

                    } else {

                        oldPasswordField.setEchoChar('*');
                        newPasswordField_one.setEchoChar('*');
                        newPasswordField_two.setEchoChar('*');

                        isSelected = false;

                    }

                }

            }while (!changePassword);

        } else if (e.getSource() == completeButton)
        {
            if(selectTrack.getSelectedIndex()==0 || selectDataCollection.getSelectedIndex()==0 ||
                    selectSensitiveData.getSelectedIndex()==0 || selectKeyLogger.getSelectedIndex()==0)
            {
                JOptionPane.showMessageDialog(this, "Please select all preferences before saving.");
            }
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
