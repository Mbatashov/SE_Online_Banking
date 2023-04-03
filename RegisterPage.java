package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// This class is for the Register page
public class RegisterPage extends JFrame implements ActionListener
{
    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    BankAutomated BA;
    LoginPage login;
    CA customer;

    // GUI Components for RegisterPage
    private final JButton registerButton;
    private final JButton backToLogin;
    private final JComboBox<String> selectMonth;
    private final JComboBox<String> selectDay;
    private final JComboBox<String> selectYear;
    private final JComboBox<String> selectGender;
    private final JTextField fNameField;
    private final JTextField lNameField;
    private final JTextField cardNumField;
    private final JTextField cardExpiryField;
    private final JTextField cvvField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JCheckBox showPassword;
    private final JTextField telNumField;
    private final JTextField addressField;

    /*
     * RegisterPage Constructor
     * @param logic BankAutomated object
     * @param login LoginPage object
     * 
     */
    public RegisterPage(BankAutomated logic, LoginPage login)
    {
        // Set title of the frame
        this.setTitle("Sign Up For An Account");
        this.setLayout(null);

        // GUI objects
        this.BA = logic;
        this.login = login;

        // GUI Components
        Font labels = new Font("Raleway", Font.BOLD, 25);
        Font smallLabels = new Font("Raleway", Font.BOLD, 20);
        Font textFields = new Font("Raleway", Font.PLAIN, 20);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // GUI Components for first name
        JLabel fName = new JLabel("First Name:");
        fName.setFont(labels);
        fName.setBounds(25, 150, 150, 40);
        this.add(fName);

        // GUI Components for first name field
        fNameField = new JTextField(100);
        fNameField.setBounds(225, 150, 300, 40);
        fNameField.setFont(textFields);
        fNameField.setBorder(border);
        this.add(fNameField);

        // GUI Components for last name
        JLabel lName = new JLabel("Last Name:");
        lName.setFont(labels);
        lName.setBounds(25, 250, 150, 40);
        this.add(lName);

        // GUI Components for last name field
        lNameField = new JTextField(100);
        lNameField.setBounds(225, 250, 300, 40);
        lNameField.setFont(textFields);
        lNameField.setBorder(border);
        this.add(lNameField);

        // GUI Components for card number
        JLabel cardNum = new JLabel("Card Number:");
        cardNum.setFont(labels);
        cardNum.setBounds(25, 350, 175, 40);
        this.add(cardNum);

        // GUI Components for card number field
        cardNumField = new JTextField(100);
        cardNumField.setBounds(225, 350, 300, 40);
        cardNumField.setFont(textFields);
        cardNumField.setBorder(border);
        this.add(cardNumField);

        // GUI Components for expiry date
        JLabel expiryDate = new JLabel("Expiry Date (MMYY):");
        expiryDate.setFont(smallLabels);
        expiryDate.setBounds(100, 410, 200, 40);
        this.add(expiryDate);

        // GUI Components for expiry date field
        cardExpiryField = new JTextField(50);
        cardExpiryField.setBounds(100, 450, 200, 30);
        cardExpiryField.setFont(textFields);
        cardExpiryField.setBorder(border);
        this.add(cardExpiryField);

        // GUI Components for CVV
        JLabel cvv = new JLabel("CVV:");
        cvv.setFont(smallLabels);
        cvv.setBounds(350, 410, 225, 40);
        this.add(cvv);

        // GUI Components for CVV field
        cvvField = new JTextField(3);
        cvvField.setBounds(350, 450, 100, 30);
        cvvField.setFont(textFields);
        cvvField.setBorder(border);
        this.add(cvvField);

        // GUI Components for date of birth
        JLabel dob = new JLabel("Date of Birth (MM/DD/YYYY):");
        dob.setFont(smallLabels);
        dob.setBounds(25, 500, 300, 40);
        this.add(dob);

        // GUI Components for month
        String[] months = {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        selectMonth = new JComboBox<>(months);
        selectMonth.setFont(new Font("Arial", Font.PLAIN, 16));
        selectMonth.setBounds(25, 540, 100, 30);
        selectMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectMonth.addActionListener(this);
        this.add(selectMonth);

        // GUI Components for day
        String[] days = new String[32];
        days[0] = "Day";
        for (int i = 1; i <= 31; i++)
        {
            String day = String.valueOf(i);
            if (i < 10)
            {
                day = "0" + day;
            }
            days[i] = day;
        }

        // GUI Components for day field
        selectDay = new JComboBox<>(days);
        selectDay.setFont(new Font("Arial", Font.PLAIN, 16));
        selectDay.setBounds(125, 540, 100, 30);
        selectDay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectDay.addActionListener(this);
        this.add(selectDay);

        // GUI Components for year
        String[] years = new String[85];
        years[0] = "Year";
        int index = 1;
        for (int i = 2006; i >= 1923; i--)
        {
            String year = String.valueOf(i);
            years[index] = year;
            index++;
        }

        // GUI Components for year field
        selectYear = new JComboBox<>(years);
        selectYear.setFont(new Font("Arial", Font.PLAIN, 16));
        selectYear.setBounds(225, 540, 100, 30);
        selectYear.addActionListener(this);
        selectYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(selectYear);

        // GUI Components for gender
        JLabel gender = new JLabel("Gender:");
        gender.setFont(labels);
        gender.setBounds(25, 580, 150, 40);
        this.add(gender);

        // Gender options
        String[] genders = {"Gender", "Male", "Female", "Other"};
        selectGender = new JComboBox<>(genders);
        selectGender.setFont(new Font("Arial", Font.PLAIN, 16));
        selectGender.setBounds(150, 590, 300, 30);
        selectGender.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectGender.addActionListener(this);
        this.add(selectGender);

        // GUI Components for email label
        JLabel email = new JLabel("Email:");
        email.setFont(labels);
        email.setBounds(750, 150, 150, 40);
        this.add(email);

        // GUI Components for email field
        emailField = new JTextField(100);
        emailField.setBounds(920, 150, 350, 40);
        emailField.setFont(textFields);
        emailField.setBorder(border);
        this.add(emailField);

        // GUI Components for password label
        JLabel password = new JLabel("Password:");
        password.setFont(labels);
        password.setBounds(750, 250, 150, 40);
        this.add(password);

        // GUI Components for password field
        passwordField = new JPasswordField(100);
        passwordField.setBounds(920, 250, 350, 40);
        passwordField.setFont(textFields);
        passwordField.setBorder(border);
        this.add(passwordField);

        // GUI Components for show password checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(920, 290, 200, 40);
        showPassword.setBackground(Color.white);
        showPassword.setForeground(Color.black);
        showPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showPassword.addActionListener(this);
        this.add(showPassword);

        // GUI Components for phone number label
        JLabel telNum = new JLabel("Phone Number:");
        telNum.setFont(smallLabels);
        telNum.setBounds(750, 350, 150, 40);
        this.add(telNum);

        // GUI Components for phone number field
        telNumField = new JTextField(100);
        telNumField.setBounds(920, 350, 350, 40);
        telNumField.setFont(textFields);
        telNumField.setBorder(border);
        this.add(telNumField);

        // GUI Components for address label
        JLabel address = new JLabel("Address:");
        address.setFont(labels);
        address.setBounds(750, 425, 150, 40);
        this.add(address);

        // GUI Components for address field
        addressField = new JTextField(100);
        addressField.setBounds(920, 425, 350, 40);
        addressField.setFont(textFields);
        addressField.setBorder(border);
        this.add(addressField);

        // GUI Components for register button
        registerButton = new JButton("S i g n   U p");
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 25));
        registerButton.setBounds(750, 500, 520, 50);
        registerButton.setBackground(Color.black);
        registerButton.setForeground(Color.white);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(this);
        this.add(registerButton);

        // GUI Components for back to login button
        Border emptyBorder = BorderFactory.createEmptyBorder();
        backToLogin = new JButton("Back To Login");
        backToLogin.setFont(new Font("SansSerif", Font.PLAIN, 25));
        backToLogin.setBounds(750, 550, 520, 50);
        backToLogin.setBorder(emptyBorder);
        backToLogin.setBackground(Color.white);
        backToLogin.setForeground(new Color(57, 107, 170));
        backToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backToLogin.addActionListener(this);
        this.add(backToLogin);

        // Window listener, logout when window is closed
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

        // Set frame properties
        this.setSize(WIDTH, LENGTH);
        this.getContentPane().setBackground(Color.white);
        this.getRootPane().setDefaultButton(registerButton);
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
        Color myRed = new Color(232, 30, 30);
        Color myBlack = new Color(161, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        // Set the font
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(249, 185, 63));
        g2.drawString("S I G N   U P", WIDTH/3-155, 110);
    }

    /*
     * Method to handle the action events
     * @param e ActionEvent object
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If the show password checkbox was pressed, we show the password
        if (showPassword.isSelected())
        {
            passwordField.setEchoChar('\u0000');
        }
        // If the show password checkbox was unpressed, we hide the password
        else
        {
            passwordField.setEchoChar('*');
        }

        //If the register button was pressed, we validate every single field of input before creating an account
        if (e.getSource() == registerButton)
        {
            // Validate the first name
            String firstName = fNameField.getText();
            if (firstName.equals(""))
            {
                JOptionPane.showMessageDialog(this, "First Name is Required.");
                return;
            }

            // Validate the last name
            String lastName = lNameField.getText();
            if (lastName.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Last Name is Required.");
                return;
            }

            // Validate the card number
            String cardNum = cardNumField.getText();
            if (cardNum.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Card Number is Required.");
                return;
            }

            // Validate the card number
            else if (!BA.validCard(cardNum))
            {
                JOptionPane.showMessageDialog(this, "Card Number is invalid/already registered with an account.");
                cardNumField.setText("");
                return;
            }

            // Validate the card expiry date
            String cardExpiry = cardExpiryField.getText();
            if (cardExpiry.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Card Expiry Date is Required.");
                return;
            }
            else
            {
                // Validate the card expiry date
                if (cardExpiry.length() != 4 || !BA.onlyNumeric(cardExpiry))
                {
                    JOptionPane.showMessageDialog(this, "Card Expiry Date must be 4 digits only.");
                    cardExpiryField.setText("");
                    return;
                }
                else
                {
                    String month = cardExpiry.substring(0, 2);
                    String year = cardExpiry.substring(2, 4);
                    if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12)
                    {
                        JOptionPane.showMessageDialog(this, "Card Expiry Date is invalid.");
                        cardExpiryField.setText("");
                        return;
                    }

                    if (Integer.parseInt(year) < 23 || (Integer.parseInt(year) == 23 && Integer.parseInt(month) < 5))
                    {
                        JOptionPane.showMessageDialog(this, "Card is Expired");
                        cardExpiryField.setText("");
                        return;
                    }
                }
            }

            // Validate the CVV
            String cvv = cvvField.getText();
            if (cvv.equals(""))
            {
                JOptionPane.showMessageDialog(this, "CVV is Required.");
                return;
            }

            // Validate the CVV
            else if (!BA.validCVV(cvv))
            {
                JOptionPane.showMessageDialog(this, "CVV is Invalid. Reminder: CVV is the 3/4" +
                        "\ndigits on the back your card.");
                cvvField.setText("");
                return;
            }

            // Validate the date of birth month
            if (selectMonth.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Date of Birth is required.");
                return;
            }

            // Validate the date of birth day
            if (selectDay.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Date of Birth is required.");
                return;
            }

            // Validate the date of birth year
            if (selectYear.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Date of Birth is required.");
                return;
            }

            // Validate the date of birth
            String birthMonth = String.valueOf(selectMonth.getSelectedItem());
            String birthDay = String.valueOf(selectDay.getSelectedItem());
            String birthYear = String.valueOf(selectYear.getSelectedItem());
            String dob;

            // Makes sure the day and month match
            if (!BA.validDOB(birthMonth, birthDay, birthYear))
            {
                JOptionPane.showMessageDialog(this, "Month and day don't match.");
                selectDay.setSelectedIndex(0);
                return;
            }

            else
            {
                // Makes dob in the format MM/DD/YYYY
                dob = birthMonth + "/" + birthDay + "/" + birthYear;
            }

            // Gender is required
            if (selectGender.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Gender is required.");
                return;
            }


            String gender = String.valueOf(selectGender.getSelectedItem());

            // Email is required
            String email = emailField.getText();
            if (email.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Email Address is Required.");
                return;
            }

            // Email is valid
            else if (!BA.validEmail(email))
            {
                JOptionPane.showMessageDialog(this, "Email is invalid.");
                emailField.setText("");
                return;
            }

            // Email is unique
            else if (BA.existingEmail(email))
            {
                JOptionPane.showMessageDialog(this, "An account with this email already exists.");
                emailField.setText("");
                return;
            }

            // Password is required
            String password = String.valueOf(passwordField.getPassword());
            if (password.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Password is required.");
                return;
            }

            // Password is valid
            else if (!BA.validPassword(password))
            {
                JOptionPane.showMessageDialog(this,"Password is invalid. Password must contain:" +
                        "\n-At least one lowercase and one uppercase character\n-At least one number\n-At least one" +
                        " special character\n-At least 8 characters overall.");
                passwordField.setText("");
                return;
            }

            // Password is confirmed
            String phoneNum = telNumField.getText();
            if (phoneNum.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Phone Number is Required.");
                return;
            }

            // Phone number is valid
            else if (!BA.onlyNumeric(phoneNum) || phoneNum.length() != 10)
            {
                JOptionPane.showMessageDialog(this, "Phone Number is invalid");
                telNumField.setText("");
                return;
            }
            else
            {
                // Make phone number in the format XXX-XXX-XXXX
                phoneNum = phoneNum.substring(0,3) + "-" + phoneNum.substring(3,6) + "-" + phoneNum.substring(6,10);
            }

            // Address is required
            String address = addressField.getText();
            if (address.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Address is Required.");
                return;
            }

            // Create an account
            customer = BA.createAccount(firstName, lastName, phoneNum, address, gender, dob, email, password, cardNum, cardExpiry, cvv);

            // If account is created
            if (customer == null)
            {
                JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Thank you for register with BCS! Your account" +
                        "\nhas been created.");
                this.setVisible(false);
                login.setVisible(true);
            }
        }

        // If back to login button is clicked, go back to login page
        else if (e.getSource() == backToLogin)
        {
            this.setVisible(false);
            login.setVisible(true);
        }

    }

}
