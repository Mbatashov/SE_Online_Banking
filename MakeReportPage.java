import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MakeReportPage extends JFrame implements ActionListener
{
    static final int WIDTH = 1920;
    static final int LENGTH = 1080;
    BankAutomated BA;
    HomePage home;
    CA customer;

    private final JButton backToHome;
    private final JButton completeButton;
    private final JTextArea reportField;
    public MakeReportPage(BankAutomated BA, HomePage home, CA customer)
    {
        this.setTitle("Report Suspicious Activity");
        this.setLayout(null);
        this.home = home;
        this.customer = customer;
        this.BA = BA;

        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        JLabel input = new JLabel("Please describe the situation:");
        input.setFont(new Font("Raleway", Font.BOLD, 22));
        input.setBackground(Color.white);
        input.setBounds(300, 150, 400, 50);
        this.add(input);

        reportField = new JTextArea();
        reportField.setFont(new Font("SansSerif", Font.PLAIN, 22));
        reportField.setBackground(Color.white);
        reportField.setBounds(300, 200, 700, 300);

        JScrollPane scroll = new JScrollPane(reportField);
        scroll.setBounds(300, 200, 700, 300);
        scroll.setBorder(border);
        reportField.setEditable(true);
        this.add(scroll);

        completeButton = new JButton("Submit Report");
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
        g2.drawString("Report Suspicious Activity To Admins", 25, 110);
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
            String reportDescription = reportField.getText();
            if (reportDescription.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please describe the situation before submitting.");
            }
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
