package bank.core;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

// This class is for the login page
public class LoginPage extends JFrame implements ActionListener
{

    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // GUI Components for LoginPage
    private final JButton loginButton;
    private final JButton clearButton;
    private final JButton registerButton;
    private final JButton forgotButton;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JCheckBox showPassword;

    // Objects
    BankAutomated logic;
    CA customer;

    /*
     * LoginPage Constructor
     * @param BA BankAutomated object
     * 
     */
    public LoginPage(BankAutomated BA)
    {
        // Set title of the frame
        this.setTitle("Welcome! Login to Continue...");
        this.setLayout(null);

        // Set the bank automated object
        logic = BA;

        // Set the background image
        JLabel welcome = new JLabel("Welcome to The");
        welcome.setFont(new Font("Osward", Font.PLAIN, 38));
        welcome.setBounds(825, 50, 350, 40);
        this.add(welcome);

        // Set the header
        JLabel bcs = new JLabel("Bank Of Computer Science");
        bcs.setFont(new Font("Imprint MT Shadow", Font.BOLD, 38));
        bcs.setForeground(new Color(246, 160, 62));
        bcs.setBounds(720, 100, 500, 40);
        this.add(bcs);

        // Set the label for email field
        JLabel email = new JLabel("Email: ");
        email.setFont(new Font("Osward", Font.PLAIN, 28));
        email.setBounds(720, 250, 150, 30);
        this.add(email);

        // Set the email field
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        emailField = new JTextField(150);
        emailField.setBounds(875, 250, 350, 30);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setBorder(border);
        this.add(emailField);

        // Set the label for password field
        JLabel password = new JLabel("Password: ");
        password.setFont(new Font("Osward", Font.PLAIN, 28));
        password.setBounds(720, 350, 150, 30);
        this.add(password);

        // Set the password field
        passwordField = new JPasswordField(100);
        passwordField.setBounds(875, 350, 350, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(border);
        this.add(passwordField);

        // Set the show password checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(875, 380, 200, 30);
        showPassword.setBackground(Color.white);
        showPassword.setForeground(Color.black);
        showPassword.addActionListener(this);
        this.add(showPassword);

        // Set the login button
        loginButton = new JButton("SIGN IN");
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        loginButton.setBounds(825, 450, 120, 50);
        loginButton.setBackground(Color.black);
        loginButton.setForeground(Color.white);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);
        this.add(loginButton);

        // Set the clear button
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        clearButton.setBounds(1000, 450, 120, 50);
        clearButton.setBackground(Color.black);
        clearButton.setForeground(Color.white);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(this);
        this.add(clearButton);

        // Set the register button
        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        registerButton.setBounds(825, 525, 295, 50);
        registerButton.setBackground(Color.black);
        registerButton.setForeground(Color.white);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(this);
        this.add(registerButton);

        // Set the forgot password button
        Border emptyBorder = BorderFactory.createEmptyBorder();
        forgotButton = new JButton("Forgot Password?");
        forgotButton.setFont(new Font("SansSerif", Font.BOLD, 10));
        forgotButton.setBounds(1135, 380, 90, 30);
        forgotButton.setBorder(emptyBorder);
        forgotButton.setBackground(Color.white);
        forgotButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotButton.setHorizontalAlignment(SwingConstants.RIGHT);
        forgotButton.addActionListener(this);
        this.add(forgotButton);

        // Window listener, logout when the window is closed
        this.addWindowListener(new WindowEventHandler() {
            @Override
            public void windowClosing(WindowEvent evt) {
                //BA.logout (logic.logout) would be called here
                //Write all changes to the file
                logic.logout();
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    window.dispose();
                }
        
                System.exit(0);
            }
        });

        // Set the frame
        this.setSize(WIDTH, LENGTH);
        this.getContentPane().setBackground(Color.white);
        this.getRootPane().setDefaultButton(loginButton);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /*
     * paint method
     * @param Graphics g
     * 
     */
    public void paint(Graphics g)
    {

        // paint the background
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(232, 30, 30);
        Color myBlack = new Color(131, 26, 26);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 750, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, 650, LENGTH+5);

        // paint the logo
        try
        {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("BCS_logo.png")));
            g.drawImage(image, -25, 175, this);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /*
     * actionPerformed method
     * @param ActionEvent e
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        // Show password
        if (showPassword.isSelected())
        {
            passwordField.setEchoChar('\u0000');
        }

        // Hide password
        else
        {
            passwordField.setEchoChar('*');
        }

        // Clear the fields
        if (e.getSource() == clearButton)
        {
            emailField.setText("");
            passwordField.setText("");
            showPassword.setSelected(false);
        }

        // Login
        else if (e.getSource() == loginButton)
        {
            //Call the BA.login (logic.login in this case)
            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());

            //Check if email and password are blank
            if (email.equals("") || password.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Email/Password cannot be blank.");
                emailField.setText("");
                passwordField.setText("");
                showPassword.setSelected(false);
                return;
            }

            //Check validity of email and whether is exists
            if (logic.validEmail(email) && !logic.existingEmail(email))
            {
                JOptionPane.showMessageDialog(this, "Email is not registered with an " +
                        "account.\nSelect Register to create an account with us.");
                emailField.setText("");
                passwordField.setText("");
                showPassword.setSelected(false);
                return;
            }

            customer = logic.loginAccount(email, password);
            
            //Check if the login was successful, if so, show the homepage
            if (customer != null)
            {
                // Debug System.out.println("Success");
                //Show homepage for the customer
                HomePage home = new HomePage(this, logic, customer);
                emailField.setText("");
                passwordField.setText("");
                showPassword.setSelected(false);
                this.setVisible(false);
                home.setVisible(true);
            }

            //If login was unsuccessful, show error message
            else
            {
                // Debug System.out.println("Incorrect");
                JOptionPane.showMessageDialog(this, "Email/Password invalid. Try again.");
                emailField.setText("");
                passwordField.setText("");
                showPassword.setSelected(false);
            }
        }

        // Register
        else if (e.getSource() == registerButton)
        {
            RegisterPage register = new RegisterPage(logic, this);
            emailField.setText("");
            passwordField.setText("");
            showPassword.setSelected(false);
            this.setVisible(false);
            register.setVisible(true);
        }

        // Forgot password
        else if (e.getSource() == forgotButton)
        {
            emailField.setText("");
            passwordField.setText("");
            showPassword.setSelected(false);
            ForgotPage forgot = new ForgotPage(logic, this);
            this.setVisible(false);
            forgot.setVisible(true);
        }
    }
}

class WindowEventHandler extends WindowAdapter {
    public void windowClosing(WindowEvent evt) {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            window.dispose();
        }

        System.exit(0);
    }
}
