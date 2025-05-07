import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    FastCash(String pin) {
        this.pin = pin;

        // Background Image
        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/card2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1366, 768);
        add(background);

        // Title
        l1 = new JLabel("SELECT WITHDRAWAL AMOUNT");
        l1.setForeground(Color.CYAN);
        l1.setFont(new Font("System", Font.BOLD, 20));
        l1.setBounds(850, 220, 400, 30);
        background.add(l1);

        // Buttons aligned to right side
        int x1 = 850, x2 = 1030, y = 270, gap = 50, w = 150, h = 40;

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("BACK");

        // Style buttons
        JButton[] buttons = { b1, b2, b3, b4, b5, b6, b7 };
        for (JButton b : buttons) {
            b.setBackground(Color.BLACK);
            b.setForeground(Color.CYAN);
            b.setFont(new Font("System", Font.BOLD, 16));
            b.setFocusPainted(false);
        }

        // Add buttons to background with layout
        b1.setBounds(x1, y, w, h); background.add(b1);
        b2.setBounds(x2, y, w, h); background.add(b2);
        b3.setBounds(x1, y + gap, w, h); background.add(b3);
        b4.setBounds(x2, y + gap, w, h); background.add(b4);
        b5.setBounds(x1, y + gap * 2, w, h); background.add(b5);
        b6.setBounds(x2, y + gap * 2, w, h); background.add(b6);
        b7.setBounds(x2, y + gap * 3 + 10, w, h); background.add(b7);

        // Listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        // Frame settings
        setLayout(null);
        setSize(1366, 768);
        setLocation(100, 100);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = ((JButton)ae.getSource()).getText().substring(3);
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            double balance = 0.0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Double.parseDouble(rs.getString("amount"));
                } else {
                    balance -= Double.parseDouble(rs.getString("amount"));
                }
            }

            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            if (ae.getSource() == b7) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            } else {
                Date date = new Date();
                String query = "INSERT INTO bank(pin, date, type, amount) VALUES('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "')";
                c.s.executeUpdate(query); 
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}
