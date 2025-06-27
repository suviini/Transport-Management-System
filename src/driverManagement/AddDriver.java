package driverManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

// Custom button class that extends JButton to create rounded buttons
class RoundedButton extends JButton {
    private static final long serialVersionUID = 1L;
    public RoundedButton(String label) {
        super(label); // Call the constructor of JButton with the button label
        setFocusPainted(false); // Disable the focus rectangle inside
        setOpaque(false); // Make the button transparent
        setBackground(Color.BLACK); // Default background
        setForeground(Color.WHITE); // Default text color
    }

    // Override paintComponent to create a rounded appearance
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // Cast to Graphics2D for advanced features
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing for smooth edges
        g2.setColor(getBackground()); // Set the color to the button's background color
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Adjust radius for curvature
        super.paintComponent(g); // Call the superclass's method to handle painting
    }

    // Override paintBorder to create a rounded border
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Optional: draw border
    }
}


public class AddDriver extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JTextField tage, tfname, tlname, taddress, tphone, did, vid, temail;
    JComboBox<String> Boxstatus; // Specify type for JComboBox
    RoundedButton login, back; // Declare buttons as instance variables
    
   

    AddDriver() {
    	
    	
        getContentPane().setBackground(new Color(255, 255, 255));

        // Create labels and text fields
        createLabelsAndTextFields();

        setSize(1113, 776);

        JPanel headingPanel = new JPanel();
        headingPanel.setBounds(0, 0, 525, 749);
        headingPanel.setBackground(new Color(255, 224, 73));
        headingPanel.setLayout(null);

        JLabel heading = new JLabel("Add Driver Details and Vehicle Details");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setForeground(new Color(0, 0, 0)); // Text color
        heading.setBounds(25, 102, 500, 50); // Centered in the panel
        headingPanel.add(heading);
        getContentPane().add(headingPanel);
        JLabel fname = new JLabel("First Name:");
        fname.setBounds(24, 162, 150, 30);
        headingPanel.add(fname);
        fname.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel age = new JLabel("Age:");
        age.setBounds(24, 326, 74, 30);
        headingPanel.add(age);
        age.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel phone = new JLabel("Phone Number:");
        phone.setBounds(24, 246, 150, 30);
        headingPanel.add(phone);
        phone.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel vehicleId = new JLabel("Vehicle Number:");
        vehicleId.setBounds(24, 459, 180, 30);
        headingPanel.add(vehicleId);
        vehicleId.setFont(new Font("Tahoma", Font.BOLD, 16));

        // Drop Down to check the status of the driver
        JLabel available = new JLabel("Driver Status:");
        available.setBounds(25, 499, 150, 30);
        headingPanel.add(available);
        available.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lname = new JLabel("Last Name:");
        lname.setBounds(24, 206, 150, 30);
        headingPanel.add(lname);
        lname.setFont(new Font("Tahoma", Font.BOLD, 16));

        vid = new JTextField();
        vid.setFont(new Font("Tahoma", Font.BOLD, 16));
        vid.setBounds(299, 453, 150, 30);
        headingPanel.add(vid);
        vid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        String items[] = {"Active", "Inactive", "On Duty", "Off Duty", "Pending"};
        Boxstatus = new JComboBox<>(items);
        Boxstatus.setFont(new Font("Tahoma", Font.BOLD, 16));
        Boxstatus.setBounds(299, 499, 150, 30);
        headingPanel.add(Boxstatus);
        Boxstatus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel address = new JLabel("Address:");
        address.setBounds(24, 286, 150, 30);
        headingPanel.add(address);
        address.setFont(new Font("Tahoma", Font.BOLD, 16));

        tlname = new JTextField();
        tlname.setFont(new Font("Tahoma", Font.BOLD, 16));
        tlname.setBounds(299, 206, 150, 30);
        headingPanel.add(tlname);
        tlname.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        tphone = new JTextField();
        tphone.setFont(new Font("Tahoma", Font.BOLD, 16));
        tphone.setBounds(299, 251, 150, 30);
        headingPanel.add(tphone);
        tphone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        tage = new JTextField();
        tage.setFont(new Font("Tahoma", Font.BOLD, 16));
        tage.setBounds(299, 331, 150, 30);
        headingPanel.add(tage);
        tage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        tfname = new JTextField();
        tfname.setFont(new Font("Tahoma", Font.BOLD, 16));
        tfname.setBounds(299, 167, 150, 30);
        headingPanel.add(tfname);
        tfname.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        taddress = new JTextField();
        taddress.setFont(new Font("Tahoma", Font.BOLD, 16));
        taddress.setBounds(299, 291, 150, 30);
        headingPanel.add(taddress);
        taddress.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel driverId = new JLabel("Driver NIC:");
        driverId.setBounds(24, 373, 150, 30);
        headingPanel.add(driverId);
        driverId.setFont(new Font("Tahoma", Font.BOLD, 16));

        did = new JTextField();
        did.setFont(new Font("Tahoma", Font.BOLD, 16));
        did.setBounds(299, 371, 150, 30);
        headingPanel.add(did);
        did.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Button for the Submit
        login = new RoundedButton("SUBMIT");
        login.setBounds(48, 601, 150, 39);
        headingPanel.add(login);
        login.setBackground(Color.BLACK); // Set background for LOGIN button

        // Button for the Back
        back = new RoundedButton("BACK");
        back.setBounds(268, 601, 150, 39);
        headingPanel.add(back);
        back.setBackground(Color.RED); // Set background for BACK button

        JLabel email = new JLabel("Driver Email:");
        email.setBounds(24, 419, 150, 30);
        headingPanel.add(email);
        email.setFont(new Font("Tahoma", Font.BOLD, 16));

        temail = new JTextField();
        temail.setFont(new Font("Tahoma", Font.BOLD, 16));
        temail.setBounds(299, 413, 150, 30);
        headingPanel.add(temail);
        temail.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setIcon(new ImageIcon(AddDriver.class.getResource("/Icons/driverlogin.jpg")));
        lblNewLabel.setBounds(479, 10, 610, 719);
        getContentPane().add(lblNewLabel);
        back.addActionListener(this);
        login.addActionListener(this);
        setLocation(300, 50);
        setVisible(true);
    }

    private void createLabelsAndTextFields() {
        getContentPane().setLayout(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose(); // Close the current window
            new DashBoard(); // Return to Dashboard
        }

        if (e.getSource() == login) {
            // Get values from text fields
            String fname = tfname.getText().trim();
            String lname = tlname.getText().trim();
            String ageStr = tage.getText().trim();
            String phone = tphone.getText().trim();
            String address = taddress.getText().trim();
            String nic = did.getText().trim();
            String email = temail.getText().trim();
            String status = (String) Boxstatus.getSelectedItem(); // Retrieve selected status from dropdown
            String vidStr = vid.getText().trim();

            // Validation for empty fields
            if (fname.isEmpty() || lname.isEmpty() || ageStr.isEmpty() || phone.isEmpty() || address.isEmpty() || nic.isEmpty() || email.isEmpty() || status.isEmpty() || vidStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate first name and last name
            if (!fname.matches("[a-zA-Z]{2,}")) {
                JOptionPane.showMessageDialog(this, "First name must contain only letters and be at least 2 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!lname.matches("[a-zA-Z]{2,}")) {
                JOptionPane.showMessageDialog(this, "Last name must contain only letters and be at least 2 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate age as a positive integer within a reasonable range
            int age;
            try {
                age = Integer.parseInt(ageStr);
                if (age < 18 || age > 65) {
                    JOptionPane.showMessageDialog(this, "Age must be between 18 and 65.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid age. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate phone number 
            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Phone number must be 10 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate email format
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
         // Validate NIC format (12 digits followed by 'V' or 'X')
            if (!nic.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(this, "NIC must be 12 digits" , "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate address 
            if (address.length() < 10) {
                JOptionPane.showMessageDialog(this, "Address must be at least 10 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Vehicle ID 
            if (vidStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vehicle ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Prepare SQL statement
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/transport_db", "root", "")) {
                String sql = "INSERT INTO addd (tfname, tlname, tage, tphone, taddress, did, temail, Boxstatus, vid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);

                // Set parameters for the query
                pst.setString(1, fname);
                pst.setString(2, lname);
                pst.setInt(3, age);
                pst.setString(4, phone);
                pst.setString(5, address);
                pst.setString(6, nic);
                pst.setString(7, email);
                pst.setString(8, status);
                pst.setString(9, vidStr);

                // Execute the query
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Driver added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add driver.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new AddDriver(); 
    }
}
