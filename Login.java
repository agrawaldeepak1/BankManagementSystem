import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    Login() {
        setTitle("BANK MANAGEMENT SYSTEM");
        setSize(1366, 768);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image setup
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/icons/rupee.jpg"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        ImageIcon bgScaledIcon = new ImageIcon(bgImage);
        JLabel background = new JLabel(bgScaledIcon);
        background.setBounds(0, 0, 1366, 768);
        setContentPane(background);
        setLayout(null);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/logo.jpg"));
        Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(logoImage));
        logo.setBounds(630, 30, 100, 100);
        add(logo);

        // Title
        l1 = new JLabel("WELCOME TO BANK");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setForeground(Color.WHITE);
        l1.setBounds(480, 150, 500, 40);
        add(l1);

        // Card No
        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setForeground(Color.WHITE);
        l2.setBounds(400, 250, 150, 30);
        add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(570, 250, 300, 30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);

        // PIN
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setForeground(Color.WHITE);
        l3.setBounds(400, 320, 150, 30);
        add(l3);

        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(570, 320, 300, 30);
        add(pf2);

        // Buttons
        b1 = new JButton("SIGN IN");
        b2 = new JButton("CLEAR");
        b3 = new JButton("SIGN UP");

        JButton[] buttons = { b1, b2, b3 };
        for (JButton btn : buttons) {
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
        }

        b1.setBounds(570, 380, 100, 30);
        b2.setBounds(770, 380, 100, 30);
        b3.setBounds(570, 430, 300, 30);

        add(b1);
        add(b2);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                Conn c1 = new Conn();
                String cardno = tf1.getText();
                String pin = pf2.getText();
                String q = "select * from login where card_number = '" + cardno + "' and pin = '" + pin + "'";
                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (ae.getSource() == b2) {
                tf1.setText("");
                pf2.setText("");
            } else if (ae.getSource() == b3) {
                setVisible(false);
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
