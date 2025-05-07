import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField t1;
    JButton b1, b2;
    JLabel l1;
    String pin;

    Deposit(String pin) {
        this.pin = pin;

        // Set frame properties
        setTitle("Deposit");
        setSize(1366, 768);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/icons/w.jpg"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImage));
        background.setBounds(0, 0, 1366, 768);
        setContentPane(background);
        setLayout(null);

        // Title Label
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("System", Font.BOLD, 22));
        l1.setBounds(420, 250, 600, 30);
        background.add(l1);

        // Text Field
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        t1.setBounds(470, 300, 400, 30);
        background.add(t1);

        // Deposit Button
        b1 = new JButton("DEPOSIT");
        b1.setBounds(470, 360, 180, 35);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        background.add(b1);

        // Back Button
        b2 = new JButton("BACK");
        b2.setBounds(690, 360, 180, 35);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        background.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = t1.getText();
            Date date = new Date();
            if (ae.getSource() == b1) {
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    Conn c1 = new Conn();
                    c1.s.executeUpdate("INSERT INTO bank VALUES ('" + pin + "', '" + date + "', 'Deposit', '" + amount + "', 'Cash')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("").setVisible(true);
    }
}
