import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ProfileSettingPage extends JFrame implements ActionListener
{
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    BankAutomated BA;
    HomePage home;
    CA customer;

    private final JButton completeButton;
    private final JButton backToHome;
    private final JTextField addressField;
    private final JTextField phoneNumField;
    private final JTextField emailField;
    public ProfileSettingPage(HomePage home, BankAutomated BA, CA customer)
    {
        this.setTitle("Profile Settings");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        Font labels = new Font("Raleway", Font.BOLD, 30);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        JLabel email = new JLabel("Update email:");
        email.setFont(labels);
        email.setBorder(emptyBorder);
        email.setForeground(Color.black);
        email.setBounds(290,150,300,40);
        this.add(email);

        emailField = new JTextField(150);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setBorder(border);
        emailField.setBounds(690, 150, 350, 40);
        this.add(emailField);

        JLabel phoneNum = new JLabel("Update phone #:");
        phoneNum.setFont(labels);
        phoneNum.setBorder(emptyBorder);
        phoneNum.setForeground(Color.black);
        phoneNum.setBounds(290,275,300,40);
        this.add(phoneNum);

        phoneNumField = new JTextField(150);
        phoneNumField.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneNumField.setBorder(border);
        phoneNumField.setBounds(690, 275, 350, 40);
        this.add(phoneNumField);

        JLabel address = new JLabel("Update address:");
        address.setFont(labels);
        address.setBorder(emptyBorder);
        address.setForeground(Color.black);
        address.setBounds(290,400,300,40);
        this.add(address);

        addressField = new JTextField(150);
        addressField.setFont(new Font("Arial", Font.PLAIN, 20));
        addressField.setBounds(690, 400, 350, 40);
        addressField.setBorder(border);
        this.add(addressField);

        completeButton = new JButton("Complete Change");
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
        g2.drawString("Edit Profile Settings", 25, 110);
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
            String phoneNum = phoneNumField.getText();
            String address = addressField.getText();

            if (email.equals("") && phoneNum.equals("") && address.equals(""))
            {
                JOptionPane.showMessageDialog(this, "No changes were made.");
                return;
            }
            if (!email.equals(""))
            {
                if (!BA.validEmail(email))
                {
                    JOptionPane.showMessageDialog(this,"New email is invalid.");
                    emailField.setText("");
                    return;
                }
                else if (BA.existingEmail(email))
                {
                    JOptionPane.showMessageDialog(this, "Email is already registered with BCS.");
                    emailField.setText("");
                    return;
                }
                else
                {
                    customer.setEmail(email);
                }
            }

            if (!phoneNum.equals(""))
            {
                if (phoneNum.length() != 10 || !BA.onlyNumeric(phoneNum))
                {
                    JOptionPane.showMessageDialog(this, "Phone number is invalid.");
                    phoneNumField.setText("");
                    return;
                }
                else
                {
                    customer.setPhoneNum(phoneNum);
                }
            }

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
