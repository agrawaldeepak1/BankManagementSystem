import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField t1;
    JButton b1, b2;
    JLabel l1, l2;
    String pin;

    Withdrawl(String pin) {
        this.pin = pin;

        setTitle("Withdraw Amount");
        setSize(1366, 768);
        setLocation(100, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Updated background image
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/icons/rupee2.jpg"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImage));
        background.setBounds(0, 0, 1366, 768);
        setContentPane(background);
        setLayout(null);

        // Label 1
        l1 = new JLabel("MAXIMUM WITHDRAWAL IS RS. 10,000");
        l1.setForeground(Color.CYAN);
        l1.setFont(new Font("System", Font.BOLD, 18));
        l1.setBounds(800, 240, 400, 30);
        background.add(l1);

        // Label 2
        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setForeground(Color.CYAN);
        l2.setFont(new Font("System", Font.BOLD, 18));
        l2.setBounds(800, 280, 400, 30);
        background.add(l2);

        // Text field
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 24));
        t1.setBounds(800, 320, 350, 35);
        background.add(t1);

        // Withdraw Button
        b1 = new JButton("WITHDRAW");
        b1.setBounds(800, 380, 160, 40);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.CYAN);
        b1.setFont(new Font("System", Font.BOLD, 16));
        background.add(b1);

        // Back Button
        b2 = new JButton("BACK");
        b2.setBounds(990, 380, 160, 40);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.CYAN);
        b2.setFont(new Font("System", Font.BOLD, 16));
        background.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = t1.getText().trim();
            Date date = new Date();

            if (ae.getSource() == b1) {
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to Withdraw");
                    return;
                }

                double balance = 0;
                Conn c1 = new Conn();
                ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Double.parseDouble(rs.getString("amount"));
                    } else {
                        balance -= Double.parseDouble(rs.getString("amount"));
                    }
                }

                double withdrawAmount = Double.parseDouble(amount);

                if (balance < withdrawAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                String query = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "')";
                c1.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                setVisible(false);
                new Transactions(pin).setVisible(true);

            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong.");
        }
    }

    public static void main(String[] args) {
        new Withdrawl("").setVisible(true);
    }
}
