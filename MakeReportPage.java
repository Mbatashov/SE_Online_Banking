package bank.core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// This class is for the Report Suspicious Activity page
public class MakeReportPage extends JFrame implements ActionListener
{

    // Constants
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;

    // Objects
    BankAutomated BA;
    HomePage home;
    CA customer;

    // GUI Components for MakeReportPage
    private final JButton backToHome;
    private final JButton completeButton;
    private final JTextArea reportField;

    /*
     * MakeReportPage Constructor
     * @param BA BankAutomated object
     * @param home HomePage object
     * @param customer CA object
     * 
     */
    public MakeReportPage(BankAutomated BA, HomePage home, CA customer)
    {
        // Set title of the frame
        this.setTitle("Report Suspicious Activity");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        // GUI Components
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        // GUI Components for report
        JLabel input = new JLabel("Please describe the situation:");
        input.setFont(new Font("Raleway", Font.BOLD, 22));
        input.setBackground(Color.white);
        input.setBounds(300, 150, 400, 50);
        this.add(input);

        // GUI Components for report field
        reportField = new JTextArea();
        reportField.setFont(new Font("SansSerif", Font.PLAIN, 22));
        reportField.setBackground(Color.white);
        reportField.setBounds(300, 200, 700, 300);

        // GUI Components for scroll bar
        JScrollPane scroll = new JScrollPane(reportField);
        scroll.setBounds(300, 200, 700, 300);
        scroll.setBorder(border);
        reportField.setEditable(true);
        this.add(scroll);

        // GUI Components for submit button
        completeButton = new JButton("Submit Report");
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

        // Set background color
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
        
        // Set background color
        Graphics2D g2 = (Graphics2D) g;
        Color myRed = new Color(230, 30, 30);
        Color myBlack = new Color(160, 32, 32);
        GradientPaint redToBlack = new GradientPaint(0, 0, myRed, 0, 150, myBlack);
        g2.setPaint(redToBlack);
        g2.fillRect(0, 0, WIDTH+1, 150);

        // Set font and color
        Font regFont = new Font("Raleway", Font.BOLD, 60);
        g2.setFont(regFont);
        g2.setColor(new Color(250, 185, 60));
        g2.drawString("Report Suspicious Activity To Admins", 25, 110);
    }

    /*
     * actionPerformed method
     * @param e ActionEvent object
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If back to home button is clicked, go back to home page
        if (e.getSource() == backToHome)
        {
            this.setVisible(false);
            home.setVisible(true);
        }

        // If submit button is clicked, submit report
        else if (e.getSource() == completeButton)
        {
            String reportDescription = reportField.getText();

            // If no description is entered, display error message
            if (reportDescription.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please describe the situation before submitting.");
            }

            // If description is entered, submit report
            else
            {
                JOptionPane.showMessageDialog(this, "Thank you for reporting suspicious on your" +
                        " account. An admin will review it and\ncontact you shortly as per your notification preferences.");
                BA.makeReport(customer, reportDescription);
                this.setVisible(false);
                home.setVisible(true);
            }

        }

    }
    
}
