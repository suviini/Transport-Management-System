package driverManagement;

import javax.swing.*;

import transport.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Custom button class that extends JButton to create rounded buttons
class RoundButton extends JButton {
    private static final long serialVersionUID = 1L;

    public RoundButton(String label) {
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

public class DashBoard extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    RoundButton addButton, viewButton,backButton;

    public DashBoard() {
        // Set up the main frame
        setTitle("Dashboard");
        setSize(1113,776);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Main content area
        JPanel mainContent = new JPanel();
        mainContent.setBackground(new Color(255, 224, 73)); 
        mainContent.setLayout(null); 
        mainContent.setBorder(null); 

        // Create buttons without a decorative box
        addButton = new RoundButton("Add Driver");
        addButton.setForeground(new Color(255, 255, 255));
        addButton.setBounds(777, 186, 150, 50); 
        addButton.setBackground(new Color(0, 0, 0));
        addButton.addActionListener(this);
        mainContent.add(addButton); 

        viewButton = new RoundButton("View Driver");
        viewButton.setBounds(777, 290, 150, 50); 
        viewButton.setBackground(new Color(0, 0, 0));
        viewButton.addActionListener(this);
        mainContent.add(viewButton); 
        
     // Create and configure the Back button
        backButton = new RoundButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setBounds(777, 398, 150, 50);
        backButton.addActionListener(this);  // Add ActionListener for Back button
        mainContent.add(backButton); 
        
        // Add components to the frame
        getContentPane().add(mainContent, BorderLayout.CENTER);
        
        // Create a heading panel
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(new Color(255, 255, 255)); 
        headingPanel.setBounds(667, 54, 395, 56); 
        headingPanel.setLayout(null); 
        
        // Add the heading panel to the main content
        mainContent.add(headingPanel);
        
        JLabel lblNewLabel_1 = new JLabel("Manage Drivers");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(78, 10, 307, 40);
        headingPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(DashBoard.class.getResource("/Icons/drivermanage.png")));
        lblNewLabel.setBounds(61, -30, 536, 581);
        mainContent.add(lblNewLabel);
        
        
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            AddDriver addDriverFrame = new AddDriver(); // Create an instance of AddDriver
            addDriverFrame.setVisible(true); // Make it visible
            
        } else if (ae.getSource() == viewButton) {
            ViewDriver viewDriverFrame = new ViewDriver(); 
            viewDriverFrame.setVisible(true); // Make it visible
            
        } else if (ae.getSource() == backButton) {  // Back button functionality
            Admin adminFrame = new Admin();  // Assuming Admin is the class name for the Admin screen
            adminFrame.setVisible(true); 
            dispose();  // Close the current Dashboard frame
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashBoard().setVisible(true));
    }
}
