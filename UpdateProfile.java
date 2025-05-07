import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateProfile extends JFrame implements ActionListener {
    JTextField tname, taddress, tpin, tcity, tstate, tform;
    JRadioButton r1, r2;
    JButton bsearch, bupdate, bback;
    JLabel background;
    String formno;

    UpdateProfile() {
        setLayout(null);

        // Set Background Image
        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/rupee2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        background = new JLabel(i3);
        background.setBounds(0, 0, 1366, 768);
        add(background);

        // Right side container layout
        int x = 850, width = 250, h = 30, gap = 50;
        int y = 60;

        JLabel ltitle = new JLabel("Update Customer Profile");
        ltitle.setFont(new Font("System", Font.BOLD, 20));
        ltitle.setForeground(Color.CYAN);
        ltitle.setBounds(x, y, 300, h);
        background.add(ltitle);

        y += gap;
        JLabel lform = new JLabel("Form No:");
        lform.setForeground(Color.WHITE);
        lform.setBounds(x, y, 100, h);
        background.add(lform);

        tform = new JTextField();
        tform.setBounds(x + 100, y, width, h);
        background.add(tform);

        bsearch = new JButton("Search");
        bsearch.setBounds(x + 100, y + h + 10, 100, h);
        background.add(bsearch);

        y += gap + 30;
        JLabel lname = new JLabel("Name:");
        lname.setForeground(Color.WHITE);
        lname.setBounds(x, y, 100, h);
        background.add(lname);

        tname = new JTextField();
        tname.setBounds(x + 100, y, width, h);
        background.add(tname);

        y += gap;
        JLabel laddress = new JLabel("Address:");
        laddress.setForeground(Color.WHITE);
        laddress.setBounds(x, y, 100, h);
        background.add(laddress);

        taddress = new JTextField();
        taddress.setBounds(x + 100, y, width, h);
        background.add(taddress);

        y += gap;
        JLabel lpin = new JLabel("Pincode:");
        lpin.setForeground(Color.WHITE);
        lpin.setBounds(x, y, 100, h);
        background.add(lpin);

        tpin = new JTextField();
        tpin.setBounds(x + 100, y, width, h);
        background.add(tpin);

        y += gap;
        JLabel lcity = new JLabel("City:");
        lcity.setForeground(Color.WHITE);
        lcity.setBounds(x, y, 100, h);
        background.add(lcity);

        tcity = new JTextField();
        tcity.setBounds(x + 100, y, width, h);
        background.add(tcity);

        y += gap;
        JLabel lstate = new JLabel("State:");
        lstate.setForeground(Color.WHITE);
        lstate.setBounds(x, y, 100, h);
        background.add(lstate);

        tstate = new JTextField();
        tstate.setBounds(x + 100, y, width, h);
        background.add(tstate);

        y += gap;
        JLabel lmarital = new JLabel("Marital Status:");
        lmarital.setForeground(Color.WHITE);
        lmarital.setBounds(x, y, 120, h);
        background.add(lmarital);

        r1 = new JRadioButton("Married");
        r2 = new JRadioButton("Unmarried");
        r1.setBounds(x + 130, y, 100, h);
        r2.setBounds(x + 230, y, 100, h);
        r1.setOpaque(false);
        r2.setOpaque(false);
        r1.setForeground(Color.WHITE);
        r2.setForeground(Color.WHITE);
        background.add(r1);
        background.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        y += gap + 10;
        bupdate = new JButton("Update");
        bupdate.setBounds(x + 40, y, 100, h);
        background.add(bupdate);

        bback = new JButton("Back");
        bback.setBounds(x + 160, y, 100, h);
        background.add(bback);

        // Actions
        bsearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                formno = tform.getText();
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("SELECT * FROM signup WHERE formno = '" + formno + "'");
                    if (rs.next()) {
                        tname.setText(rs.getString("name"));
                        taddress.setText(rs.getString("address"));
                        tpin.setText(rs.getString("pincode"));
                        tcity.setText(rs.getString("city"));
                        tstate.setText(rs.getString("state"));
                        String marital = rs.getString("marital");
                        if (marital.equals("Married")) {
                            r1.setSelected(true);
                        } else {
                            r2.setSelected(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Form No not found.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bupdate.addActionListener(this);
        bback.addActionListener(this);

        // Frame Settings
        setSize(1366, 768);
        setLocation(100, 50);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bupdate) {
            try {
                String name = tname.getText();
                String address = taddress.getText();
                String pincode = tpin.getText();
                String city = tcity.getText();
                String state = tstate.getText();
                String marital = r1.isSelected() ? "Married" : "Unmarried";

                Conn c = new Conn();
                String query = "UPDATE signup SET name='" + name + "', address='" + address + "', pincode='" + pincode + "', city='" + city + "', state='" + state + "', marital='" + marital + "' WHERE formno='" + formno + "'";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bback) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateProfile();
    }
}
