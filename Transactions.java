import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transactions extends JFrame implements ActionListener {

    JLabel l1,l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    String pin;

    Transactions(String pin) {
        this.pin = pin;

        setTitle("Transactions");
        setSize(1366, 768);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/icons/w.jpg"));
        Image bgImg = bgIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImg));
        background.setBounds(0, 0, 1366, 768);
        setContentPane(background);
        setLayout(null);
        
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/logo.jpg"));
        Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(logoImage));
        logo.setBounds(340, 30, 100, 100);
        add(logo);

        // Title
        l2 = new JLabel("WELCOME TO BANK");
        l2.setFont(new Font("Osward", Font.BOLD, 36));
        l2.setForeground(Color.red);
        l2.setBounds(460, 30, 800, 40);
        add(l2);

        // Title Label
        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(new Color(20, 20, 20));
        l1.setFont(new Font("System", Font.BOLD, 24));
        l1.setBounds(460, 80, 600, 30);
        background.add(l1);

        Font btnFont = new Font("System", Font.BOLD, 18);
        Color btnBg = new Color(0, 0, 0);
        Color btnFg = new Color(255, 255, 255);

        // Buttons
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWAL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("EXIT");
        b8 = new JButton("UPDATE PROFILE");

        JButton[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8};
        int x1 = 460, y1 = 150;

        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];
            btn.setBounds(x1, y1 + i * 50, 400, 40);
            btn.setBackground(btnBg);
            btn.setForeground(btnFg);
            btn.setFont(btnFont);
            background.add(btn);
            btn.addActionListener(this);
        }

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            setVisible(false);
            new Deposit(pin).setVisible(true);
        } else if (ae.getSource() == b2) {
            setVisible(false);
            new Withdrawl(pin).setVisible(true);
        } else if (ae.getSource() == b3) {
            setVisible(false);
            new FastCash(pin).setVisible(true);
        } else if (ae.getSource() == b4) {
            new MiniStatement(pin).setVisible(true);
        } else if (ae.getSource() == b5) {
            setVisible(false);
            new Pin(pin).setVisible(true);
        } else if (ae.getSource() == b6) {
            setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);
        } else if (ae.getSource() == b8) {
            setVisible(false);
            new UpdateProfile().setVisible(true);
        } else if (ae.getSource() == b7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("").setVisible(true);
    }
}
