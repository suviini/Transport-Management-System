package driverManagement;

import javax.swing.*;

import transport.home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AboutUsandFeedback extends JFrame {

    private static final long serialVersionUID = 1L;

    // Constructor for the AboutUsandFeedback JFrame
    @SuppressWarnings("null")
	public AboutUsandFeedback() {
        setTitle("Online Transport System - About Us and Feedback");
        setSize(1113,776 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close application when the window is closed
        setLocationRelativeTo(null); // Center the window on the screen
        

        // Create a split pane to divide the window into two sections: About Us and Feedback
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400); 
        splitPane.setLeftComponent(createAboutUsPanel());  
        splitPane.setRightComponent(createFeedbackPanel());  

        // Add the split pane to the main frame
        getContentPane().add(splitPane);
    }

    // Method to create the About Us panel
    private JPanel createAboutUsPanel() {
        JPanel aboutUsPanel = new JPanel(); 
        aboutUsPanel.setBackground(new Color(255, 224, 73)); 

        // Create and style the title for About Us section
        JLabel titleLabel = new JLabel("About Us", JLabel.CENTER);
        titleLabel.setBounds(0, 34, 399, 34);
        titleLabel.setBackground(new Color(255, 255, 255)); 
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28)); 
        titleLabel.setForeground(new Color(0, 0, 0)); 

        // Detailed description of the system
        JLabel aboutUsContent = new JLabel(
            "<html><div style='text-align: center; font-size: 14px; color: #000000\r\n;'><h1>Welcome to CATCHME ! </h1><p><br>We are revolutionizing the way you travel by providing a seamless bus booking system. With our platform, you can:</p><ul style='text-align: left;'><li>Search for available buses</li><li>Manage your reservations</li><li>Track your bus in real-time</li></ul><p>Our goal is to make your journey smooth and hassle-free. Thank you for choosing us!</p></div></html>"
        );
        aboutUsContent.setBounds(24, 254, 365, 455);
        aboutUsContent.setForeground(new Color(254, 224, 73));  
        aboutUsContent.setHorizontalAlignment(SwingConstants.CENTER);  
        aboutUsPanel.setLayout(null);

        // Add title and content to the About Us panel
        aboutUsPanel.add(titleLabel);  
        aboutUsPanel.add(aboutUsContent);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(87, 160, 45, 13);
        aboutUsPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(AboutUsandFeedback.class.getResource("/Icons/icon.png")));
        lblNewLabel_1.setBounds(-220, 111, 631, 192);
        aboutUsPanel.add(lblNewLabel_1);

        return aboutUsPanel;  // Return the configured About Us panel
    }

    // Method to create the Feedback panel
    private JPanel createFeedbackPanel() {
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setBackground(new Color(254, 224, 73));  

        // Title for Feedback section
        JLabel feedbackTitle = new JLabel("Your Feedback", JLabel.CENTER);
        feedbackTitle.setBounds(-90, 48, 937, 33);
        feedbackTitle.setFont(new Font("Arial", Font.BOLD, 28));  // Set font and size
        feedbackTitle.setForeground(new Color(0, 0, 0));  // Set text color for contrast

        // Text area for users to enter their feedback
        JTextArea feedbackTextArea = new JTextArea(10, 30);  // Text area with 10 rows and 30 columns
        feedbackTextArea.setBackground(new Color(255, 255, 255));  
        feedbackTextArea.setLineWrap(true);  
        feedbackTextArea.setWrapStyleWord(true);  
        feedbackTextArea.setFont(new Font("Arial", Font.PLAIN, 16));  
        JScrollPane scrollPane = new JScrollPane(feedbackTextArea);
        scrollPane.setBounds(32, 117, 634, 359);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Enter your feedback"));
        feedbackPanel.setLayout(null);

        // Add components to the feedback panel
        feedbackPanel.add(feedbackTitle);  
        feedbackPanel.add(scrollPane);  

        // Submit button for feedback submission
        JButton submitButton = new JButton("Submit ");
        submitButton.setBounds(144, 562, 130, 40);
        feedbackPanel.add(submitButton);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));  
        submitButton.setBackground(new Color(0, 0, 0));  
        submitButton.setForeground(Color.WHITE); 
        submitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));  
        submitButton.setFocusPainted(false);  
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnBack.setBackground(Color.BLACK);
        btnBack.setBounds(466, 562, 130, 40);
        feedbackPanel.add(btnBack);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String feedback = feedbackTextArea.getText();  // Get the entered feedback
                if (feedback.isEmpty()) {
                    // Show an error message if feedback is empty
                    JOptionPane.showMessageDialog(null, "Please enter feedback before submitting.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Save the feedback to the database
                    saveFeedback(feedback);
                    // Show a success message
                    JOptionPane.showMessageDialog(null, "Thank you for your feedback!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    feedbackTextArea.setText("");  // Clear the text area after submission
                }
            }
        });
        
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to Home.java
                home homeScreen = new home(); // Create a new instance of the Home class
                homeScreen.setVisible(true);   // Make the Home screen visible
                dispose();                     // Close the current AboutUsandFeedback window
            }
        });


        return feedbackPanel;  // Return the configured Feedback panel
    }

    // Method to save feedback to the database
    private void saveFeedback(String feedback) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        // Database connection parameters
        String DB_URL= "jdbc:mysql://localhost:3306/transport_db";  // Database URL
        String USER = "root";  // Database username
        String Password = "";  // Database password (empty in this case)

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, USER, Password);

            // SQL statement to insert feedback into the database
            String sql = "INSERT INTO feedback (message) VALUES (?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, feedback);  // Set the feedback message in the SQL query

            // Execute the SQL update
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();  
            // Show an error message if there is a database issue
            JOptionPane.showMessageDialog(null, "Error saving feedback to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close the prepared statement and connection to avoid memory leaks
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AboutUsandFeedback app = new AboutUsandFeedback();  
            app.setVisible(true);  // Make the frame visible
        });
    }
}
