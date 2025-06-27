package driverManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Custom button class that extends JButton to create rounded buttons
class RounButton extends JButton {
    private static final long serialVersionUID = 1L;

    public RounButton(String label) {
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

public class ViewDriver extends JFrame {

    private static final long serialVersionUID = 1L;
    JTable table;
    RounButton update, back, delete, refresh;
    DefaultTableModel model;

    public ViewDriver() {
        getContentPane().setBackground(new Color(156, 195, 213));
        getContentPane().setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // Create the heading panel similar to AddDriver
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(Color.BLACK);
        headingPanel.setLayout(new FlowLayout()); // Use FlowLayout for the heading panel

        JLabel heading = new JLabel("Driver Details");
        heading.setFont(new Font("Serif", Font.BOLD, 25));
        heading.setForeground(Color.WHITE); // Text color
        heading.setHorizontalAlignment(SwingConstants.CENTER); // Center text
        headingPanel.add(heading);
        getContentPane().add(headingPanel, BorderLayout.NORTH); // Add heading panel to the north

        model = new DefaultTableModel(new String[]{"Driver ID", "First Name", "Last Name", "Age", "Phone", "Email", "Vehicle ID", "Status"}, 0);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(254, 224, 73));
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(null);
        table = new JTable(model);

        // Center the table in a JScrollPane with square size
        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(44, 99, 1002, 277);
        jp.setPreferredSize(new Dimension(400, 400)); 
        jp.setAlignmentX(Component.CENTER_ALIGNMENT); 
        centerPanel.add(jp);

        // Button panel for actions
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setBounds(0, 550, 1099, 65);
        centerPanel.add(buttonPanel);
        buttonPanel.setBackground(new Color(255, 182, 10));

        refresh = new RounButton("Refresh");
        refresh.setFont(new Font("Tahoma", Font.BOLD, 11));
        refresh.setBounds(338, 20, 94, 35);
        refresh.setBackground(new Color(0, 0, 0));
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                refreshDrivers(); 
            }
        });
        buttonPanel.setLayout(null);
        buttonPanel.add(refresh); 

        update = new RounButton("Update");
        update.setBounds(460, 20, 85, 35);
        update.setBackground(new Color(0, 0, 0));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                updateDriver();
            }
        });
        buttonPanel.add(update);

        delete = new RounButton("Delete");
        delete.setBounds(569, 20, 78, 35);
        delete.setBackground(new Color(0, 0, 0));
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteDriver();
            }
        });
        buttonPanel.add(delete);

        back = new RounButton("Back");
        back.setBounds(671, 19, 78, 36);
        back.setBackground(new Color(0, 0, 0));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        });
        buttonPanel.add(back);

        setSize(1113,776);
        setLocationRelativeTo(null); 
        setVisible(true);

        // Automatically refresh the driver details on startup
        refreshDrivers();
    }

    private void deleteDriver() {
        int selectedRow = table.getSelectedRow(); // Get the selected row index
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        // Fetch the driver ID from the selected row
        String driverId = (String) model.getValueAt(selectedRow, 0);

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this driver?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String url = "jdbc:mysql://localhost:3306/transport_db"; 
                String username = "root"; 
                String password = ""; 
                Connection conn = DriverManager.getConnection(url, username, password);

                // Execute delete query using the selected driver's ID
                String query = "DELETE FROM addd WHERE did = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, driverId);
                stmt.executeUpdate();

                // Remove the row from the table model after deletion
                model.removeRow(selectedRow);

                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateDriver() {
        int selectedRow = table.getSelectedRow(); // Get the selected row index
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        // Fetch the values from the selected row
        String did = (String) model.getValueAt(selectedRow, 0); 
        String tfname = (String) model.getValueAt(selectedRow, 1);
        String tlname = (String) model.getValueAt(selectedRow, 2);
        String tage = (String) model.getValueAt(selectedRow, 3);
        String tphone = (String) model.getValueAt(selectedRow, 4);
        String temail = (String) model.getValueAt(selectedRow, 5);
        String vid = (String) model.getValueAt(selectedRow, 6);
        String status = (String) model.getValueAt(selectedRow, 7);

        // Pass the selected driver's data to the UpdateDriver page
        new UpdateDriver(did, tfname, tlname, tage, tphone, temail, vid, status);
        setVisible(false); // Close the current ViewDriver window
    }

    private void refreshDrivers() {
        model.setRowCount(0); // Clear the existing data in the table
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/transport_db"; // Your database URL
            String username = "root"; // Your database user name
            String password = ""; // Your database password
            conn = DriverManager.getConnection(url, username, password);

            // Query to fetch all drivers
            String query = "SELECT * FROM addd"; 
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(); // Execute the query

            // Iterate through the ResultSet and populate the table model
            while (rs.next()) {
                String driverId = rs.getString("did"); // Assuming 'did' is the Driver ID column
                String firstName = rs.getString("tfname");
                String lastName = rs.getString("tlname");
                String age = rs.getString("tage");
                String phone = rs.getString("tphone");
                String email = rs.getString("temail");
                String vehicleId = rs.getString("vid");
                String status = rs.getString("Boxstatus");

                // Add the data to the table model
                model.addRow(new Object[]{driverId, firstName, lastName, age, phone, email, vehicleId, status});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close(); // Close the connection
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ViewDriver();
    }
}

