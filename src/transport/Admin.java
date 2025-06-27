package transport;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import driverManagement.DashBoard;
import driverManagement.ViewDriver;
import neww.Routes;
import neww.createRoute;
import neww.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;

public class Admin {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Admin window = new Admin();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public Admin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 224, 73));
        frame.setBounds(100, 100, 1113, 776);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(255, 255, 210)); // Set background color
        sidebar.setBounds(0, 0, 191, 739); // Adjust size
        frame.getContentPane().add(sidebar);
        sidebar.setLayout(null);

     // Add label to sidebar with the logged-in user's name
        JLabel lblUser = new JLabel("JAYAKODY"); // Use the username parameter
        lblUser.setBackground(new Color(0, 0, 0));
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser.setForeground(new Color(0, 0, 0));
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUser.setBounds(20, 52, 200, 40);
        sidebar.add(lblUser);
        

        RoundedButton btnNewButton = new RoundedButton ("Dashboard", 25, null);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(20, 168, 148, 33);
        sidebar.add(btnNewButton);
        
        RoundedButton btnCustomers = new RoundedButton("Customers", 25, null);
        btnCustomers.setForeground(Color.WHITE);
        btnCustomers.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCustomers.setBackground(Color.BLACK);
        btnCustomers.setBounds(20, 227, 148, 33);
        sidebar.add(btnCustomers);
        
        RoundedButton btnNewButton_1_1 = new RoundedButton("Drivers", 25, null);
        btnNewButton_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Close the current Admin frame
                frame.dispose();

                // Open the DashBoard frame
                DashBoard dashboard = new DashBoard();
                dashboard.setVisible(true);
        	}
        });
        btnNewButton_1_1.setForeground(Color.WHITE);
        btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1_1.setBackground(Color.BLACK);
        btnNewButton_1_1.setBounds(20, 294, 148, 33);
        sidebar.add(btnNewButton_1_1);
        
        RoundedButton btnNewButton_1_1_1 = new RoundedButton("Routes", 25, null);
        btnNewButton_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Close the current Admin frame
                frame.dispose();

                // Open the CreateRoute frame
                createRoute createRouteFrame = new createRoute();
                createRouteFrame.setVisible(true);
        	}
        });
        btnNewButton_1_1_1.setForeground(Color.WHITE);
        btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1_1_1.setBackground(Color.BLACK);
        btnNewButton_1_1_1.setBounds(20, 363, 148, 33);
        sidebar.add(btnNewButton_1_1_1);
        
        RoundedButton btnNewButton_1_1_1_1 = new RoundedButton("Logout", 25, null);
        btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
            	// Display a message that the user is logged out
                JOptionPane.showMessageDialog(null, "The Admin is logged out");
                
                // Navigate to the Login frame
                login loginFrame = new login();
                loginFrame.setVisible(true); // Show the Login frame
                frame.dispose(); // Close the current home frame
            
        		
        	}
        });
        btnNewButton_1_1_1_1.setForeground(Color.WHITE);
        btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1_1_1_1.setBackground(new Color(255, 0, 0));
        btnNewButton_1_1_1_1.setBounds(20, 657, 148, 33);
        sidebar.add(btnNewButton_1_1_1_1);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Admin.class.getResource("/Icons/icon-removebg-preview (1).png")));
        lblNewLabel_1.setBounds(20, 42, 61, 50);
        sidebar.add(lblNewLabel_1);

        // Content area (right panel)
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 224, 73));
        contentPanel.setBounds(192, 10, 907, 670);
        frame.getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 10, 941, 46);
        panel_1.setBackground(new Color(255, 255, 210));
        contentPanel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Dashboard");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        lblNewLabel.setBounds(20, 10, 221, 24);
        panel_1.add(lblNewLabel);
        
        // Dashboard Buttons
        JButton btnCustomerDetails = new JButton("<html><center>Customer Details</center></html>");
        btnCustomerDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCustomerDetails.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCustomerDetails.setBounds(343, 224, 215, 193);
        btnCustomerDetails.setIcon(new ImageIcon(Admin.class.getResource("/Icons/cicon.png")));
        btnCustomerDetails.setForeground(new Color(0, 0, 0));
        btnCustomerDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCustomerDetails.setBackground(new Color(255, 255, 210));
        contentPanel.add(btnCustomerDetails);

        JButton btnDrivers = new JButton("<html><center>Driver Details</center></html>");
        btnDrivers.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnDrivers.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDrivers.setBounds(108, 224, 194, 193);
        btnDrivers.setIcon(new ImageIcon(Admin.class.getResource("/Icons/dcon (8).png")));
        btnDrivers.setForeground(Color.BLACK);
        btnDrivers.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDrivers.setBackground(new Color(255, 255, 210));
        contentPanel.add(btnDrivers);
        
        btnDrivers.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Close the current Admin frame
                frame.dispose();

                // Open the ViewDriver frame
                ViewDriver viewDriverFrame = new ViewDriver();
                viewDriverFrame.setVisible(true);
   
        	}
        });
        JButton btnRoutes = new JButton("<html><center>Route Details</center></html>");
        btnRoutes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Close the current Admin frame
                frame.dispose();

                // Open the Routes frame
                Routes routesFrame = new Routes();
                routesFrame.setVisible(true);
        	}
        });
        btnRoutes.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRoutes.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRoutes.setBounds(595, 224, 209, 190);
        btnRoutes.setIcon(new ImageIcon(Admin.class.getResource("/Icons/ricon.png")));
        btnRoutes.setForeground(Color.BLACK);
        btnRoutes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnRoutes.setBackground(new Color(255, 255, 210));
        contentPanel.add(btnRoutes);

        JButton btnReports = new JButton("<html><center>View Reports</center></html>");
        btnReports.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnReports.setHorizontalTextPosition(SwingConstants.CENTER);
        btnReports.setBounds(595, 450, 209, 190);
        btnReports.setIcon(new ImageIcon(Admin.class.getResource("/Icons/report.png")));
        btnReports.setForeground(Color.BLACK);
        btnReports.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnReports.setBackground(new Color(255, 255, 210));
        contentPanel.add(btnReports);

        JButton btnHistory = new JButton("<html><center>Booking History</center></html>");
        btnHistory.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnHistory.setHorizontalTextPosition(SwingConstants.CENTER);
        btnHistory.setBounds(342, 447, 204, 193);
        btnHistory.setIcon(new ImageIcon(Admin.class.getResource("/Icons/history2.png")));
        btnHistory.setForeground(Color.BLACK);
        btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnHistory.setBackground(new Color(255, 255, 210));
        contentPanel.add(btnHistory);

        JButton btnBookings = new JButton("<html><center>Booking</center></html>");
        btnBookings.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBookings.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBookings.setBounds(108, 453, 194, 187);
        btnBookings.setIcon(new ImageIcon(Admin.class.getResource("/Icons/bookingI.png")));
        btnBookings.setForeground(Color.BLACK);
        btnBookings.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnBookings.setBackground(new Color(255, 255, 210));
        contentPanel.add(btnBookings);
        
     // Create the search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(0, 58, 907, 86);
        contentPanel.add(searchPanel);
        searchPanel.setBackground(new Color(0, 0, 0));
        searchPanel.setLayout(null);

        // Create JTextField for search input
        JTextField searchField = new JTextField();
        searchField.setBackground(new Color(255, 255, 255));
        searchField.setBounds(32, 30, 683, 30); // Adjust size as needed
        searchPanel.add(searchField);

        // Create JButton for search action
        JButton searchButton = new JButton("Search");
        searchButton.setForeground(new Color(0, 0, 0));
        searchButton.setBackground(new Color(255, 255, 255));
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        searchButton.setBounds(746, 29, 100, 31); // Adjust size as needed
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                System.out.println("Search query: " + searchQuery); // Implement search logic here
            }
        });
        searchPanel.add(searchButton);


    }



	public void setVisible(boolean b) {
		frame.setVisible(b);
		
	}
		
	}


class RoundButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int diameter;

    public RoundButton() {
        this.diameter = 50; // Set default diameter for the round button
        setContentAreaFilled(false); // Avoid filling the area with default color
        setFocusPainted(false); // Remove the focus border
        setBorderPainted(false); // Remove the border
        setBackground(Color.BLACK); // Default background color
        setForeground(Color.WHITE); // Default text color
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(getBackground().darker()); // Darker color when pressed
        } else {
            g.setColor(getBackground());
        }

        // Draw the round shape
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(new RoundRectangle2D.Double(0, 0, diameter, diameter, diameter, diameter));
        super.paintComponent(g);
    }

    // Set the diameter of the button
    public void setDiameter(int diameter) {
        this.diameter = diameter;
        repaint();
    }
}

