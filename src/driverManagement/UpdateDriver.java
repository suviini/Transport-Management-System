package driverManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Custom button class that extends JButton to create rounded buttons
class RButton extends JButton {
    private static final long serialVersionUID = 1L;

    public RButton(String label) {
        super(label);
        setFocusPainted(false);
        setOpaque(false);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }
}

public class UpdateDriver extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JTextField did, tfname, tlname, tage, tphone, temail, vid;
    JComboBox<String> Boxstatus;
    RButton update;
    private JLabel lblUpdateDriverDetails;
    private JPanel panel;
    private JLabel lblNewLabel;

    // Constructor for the UpdateDriver page, taking selected driver details as parameters
    public UpdateDriver(String driverId, String firstName, String lastName, String age, String phone, String email, String vehicleId, String status) {
        setTitle("Update Driver");

        getContentPane().setBackground(new Color(255, 224, 73));
        getContentPane().setLayout(null);

        JLabel lblDid = new JLabel("Driver ID:");
        lblDid.setBounds(64, 212, 150, 30);
        lblDid.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblDid);

        did = new JTextField(driverId);
        did.setBackground(new Color(255, 255, 255));
        did.setBounds(264, 212, 150, 30);
        did.setFont(new Font("Tahoma", Font.BOLD, 16));
        did.setEditable(false);
        getContentPane().add(did);

        JLabel lblFname = new JLabel("First Name:");
        lblFname.setBounds(64, 262, 150, 30);
        lblFname.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblFname);

        tfname = new JTextField(firstName);
        tfname.setBounds(264, 262, 150, 30);
        tfname.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(tfname);

        JLabel lblLname = new JLabel("Last Name:");
        lblLname.setBounds(64, 312, 150, 30);
        lblLname.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblLname);

        tlname = new JTextField(lastName);
        tlname.setBounds(264, 312, 150, 30);
        tlname.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(tlname);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(64, 362, 150, 30);
        lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblAge);

        tage = new JTextField(age);
        tage.setBounds(264, 362, 150, 30);
        tage.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(tage);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(64, 412, 150, 30);
        lblPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblPhone);

        tphone = new JTextField(phone);
        tphone.setBounds(264, 412, 150, 30);
        tphone.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(tphone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(64, 462, 150, 30);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblEmail);

        temail = new JTextField(email);
        temail.setBounds(264, 462, 150, 30);
        temail.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(temail);

        JLabel lblVid = new JLabel("Vehicle ID:");
        lblVid.setBounds(64, 512, 150, 30);
        lblVid.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblVid);

        vid = new JTextField(vehicleId);
        vid.setBounds(264, 512, 150, 30);
        vid.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(vid);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(64, 562, 150, 30);
        lblStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
        getContentPane().add(lblStatus);

        String items[] = {"Active", "Inactive", "On Duty", "Off Duty", "Pending"};
        Boxstatus = new JComboBox<>(items);
        Boxstatus.setBounds(264, 562, 150, 30);
        Boxstatus.setFont(new Font("Tahoma", Font.BOLD, 16));
        Boxstatus.setSelectedItem(status);
        getContentPane().add(Boxstatus);

        update = new RButton("Update");
        update.setBounds(166, 657, 150, 40);
        update.setFont(new Font("Tahoma", Font.BOLD, 16));
        update.addActionListener(this);
        getContentPane().add(update);
        
        lblUpdateDriverDetails = new JLabel("Update Driver Details and Vehicle Details");
        lblUpdateDriverDetails.setBounds(22, 131, 500, 50);
        lblUpdateDriverDetails.setForeground(Color.BLACK);
        lblUpdateDriverDetails.setFont(new Font("Tahoma", Font.BOLD, 24));
        getContentPane().add(lblUpdateDriverDetails);
        
        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(524, 0, 575, 739);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setIcon(new ImageIcon(UpdateDriver.class.getResource("/Icons/driverlogin.jpg")));
        lblNewLabel.setBounds(10, 92, 569, 684);
        panel.add(lblNewLabel);

        setSize(1113,776);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            // Fetch updated driver details from the input fields
            String driverId = did.getText();
            String firstName = tfname.getText();
            String lastName = tlname.getText();
            String age = tage.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String vehicleId = vid.getText();
            String status = (String) Boxstatus.getSelectedItem();

            // Validate input before updating
            if (driverId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() ||
                    phone.isEmpty() || email.isEmpty() || vehicleId.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Establish database connection
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport_db", "root", "");

                // Update query to modify driver information
                String query = "UPDATE addd SET tfname = ?, tlname = ?, tage = ?, tphone = ?, temail = ?, vid = ?, Boxstatus = ? WHERE did = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, age);
                stmt.setString(4, phone);
                stmt.setString(5, email);
                stmt.setString(6, vehicleId);
                stmt.setString(7, status);
                stmt.setString(8, driverId);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Driver updated successfully!");

                // Close the window after successful update
                setVisible(false);
                new ViewDriver(); // Reopen the ViewDriver window to reflect changes
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UpdateDriver("", "", "", "", "", "", "", "");
    }
}
