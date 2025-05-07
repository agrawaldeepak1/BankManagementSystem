import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

class BalanceEnquiry extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        // Background image
        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/rupee.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1366, 768, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1366, 768);
        add(background);

        // Balance label
        l1 = new JLabel();
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 22));
        l1.setBounds(100, 200, 600, 40);  // Left aligned
        background.add(l1);

        // BACK button
        b1 = new JButton("BACK");
        b1.setBounds(100, 280, 120, 35);  // Just below balance
        b1.setBackground(new Color(0, 102, 204));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("System", Font.BOLD, 16));
        b1.addActionListener(this);
        background.add(b1);

        // Calculate balance
        double balance = 0.0;
        try {
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + pin + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Double.parseDouble(rs.getString("amount"));
                } else {
                    balance -= Double.parseDouble(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        l1.setText("Your Current Account Balance is Rs " + String.format("%.2f", balance));

        setLayout(null);
        setSize(1366, 768);
        setLocation(100, 100); // Adjusted for larger resolution
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}
