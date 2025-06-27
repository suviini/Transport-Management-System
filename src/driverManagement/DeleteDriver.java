package driverManagement;

import java.awt.Color;//class for setting the color
import java.awt.Graphics;//class for drawing components
import java.awt.Graphics2D;//Graphics2D for advanced graphics
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Custom button class that extends JButton to create rounded buttons
class RuButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuButton(String label) {
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

public class DeleteDriver extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField driverIdField; // Text field for inputting driver ID
    RuButton deleteButton, cancelButton;
    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/transport_db"; //  database name
    private static final String USER = "root"; //  DB user name
    private static final String PASSWORD = ""; // DB password

    DeleteDriver() {
        getContentPane().setBackground(new Color(255, 182, 10));
        setLayout(null);

        setSize(400, 300);
        setLocation(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            // Handle delete action
            String driverId = driverIdField.getText().trim(); // Get the driver ID from the text field
            deleteDriver(driverId); // Call the delete method
        } else if (e.getSource() == cancelButton) {
            // Handle cancel action
            setVisible(false);
            dispose();
        }
    }

    // Method to delete the driver from the database
    private void deleteDriver(String driverId) {
        String deleteQuery = "DELETE FROM addd WHERE did = ?"; // Change table name and column if needed

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
             
            pstmt.setString(1, driverId); // Set the driver ID in the prepared statement
            int affectedRows = pstmt.executeUpdate(); // Execute the delete operation
            
            // Show popup message based on the operation result
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Driver with NIC " + driverId + " has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No driver found with NIC Number " + driverId + ".", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print any SQL exceptions
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the driver: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new DeleteDriver();
    }
}
