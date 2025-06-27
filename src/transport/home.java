package transport;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import driverManagement.AboutUsandFeedback;
import neww.DisplayRoutes;
import publicTransport.BookingInfo;
import publicTransport.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;


import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchField; // Search bar

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    home frame = new home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           
        });
    }

    /**
     * Create the frame.
     */
    public home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1113, 776);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 225, 73));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Side navigation panel
        JPanel sideNav = new JPanel();
        sideNav.setBackground(new Color(255, 255, 210)); // Set background color
        sideNav.setBounds(0, 98, 216, 685); // Positioned to the right side
        contentPane.add(sideNav);
        sideNav.setLayout(null);

        // Add navigation buttons or labels
        JButton btnHome = new JButton("Home");
        btnHome.setForeground(new Color(255, 255, 255));
        btnHome.setBackground(new Color(0, 0, 0));
        btnHome.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnHome.setBounds(26, 43, 174, 40);
        btnHome.setFocusPainted(false); // Disable focus outline
        btnHome.setBorderPainted(false); // Disable border outline
        sideNav.add(btnHome);

        JButton btnAccount = new JButton("Account");
        btnAccount.setForeground(new Color(255, 255, 255));
        btnAccount.setBackground(new Color(0, 0, 0));
        btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAccount.setBounds(26, 188, 174, 40);
        btnAccount.setFocusPainted(false); // Disable focus outline
        btnAccount.setBorderPainted(false); // Disable border outline
        sideNav.add(btnAccount);

        JButton btnTickets = new JButton("Tickets");
        btnTickets.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnTickets.setForeground(new Color(255, 255, 255));
        btnTickets.setBackground(new Color(0, 0, 0));
        btnTickets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnTickets.setBounds(26, 113, 174, 40);
        btnTickets.setFocusPainted(false); // Disable focus outline
        btnTickets.setBorderPainted(false); // Disable border outline
        sideNav.add(btnTickets);

        JButton btnContact = new JButton("Feedback");
        btnContact.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Navigate to the AboutUsandFeedback frame
                AboutUsandFeedback aboutUsAndFeedbackFrame = new AboutUsandFeedback();
                aboutUsAndFeedbackFrame.setVisible(true); // Show the AboutUsandFeedback frame
                dispose(); // Close the current home frame
        	}
        });
        
        btnContact.setForeground(new Color(255, 255, 255));
        btnContact.setBackground(new Color(0, 0, 0));
        btnContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnContact.setBounds(26, 263, 174, 40);
        btnContact.setFocusPainted(false); // Disable focus outline
        btnContact.setBorderPainted(false); // Disable border outline
        sideNav.add(btnContact);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Display a message that the user is logged out
                JOptionPane.showMessageDialog(null, "The user is logged out");
                
                // Navigate to the Login frame
                Login loginFrame = new Login();
                loginFrame.setVisible(true); // Show the Login frame
                dispose(); // Close the current home frame
            }
        });
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLogout.setBackground(new Color(255, 0, 0));
        btnLogout.setBounds(26, 567, 174, 40);
        btnLogout.setFocusPainted(false); // Disable focus outline
        btnLogout.setBorderPainted(false); // Disable border outline
        sideNav.add(btnLogout);
        
        JButton btnAboutUs = new JButton("About Us");
        btnAboutUs.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Navigate to the AboutUsandFeedback frame
                AboutUsandFeedback aboutUsAndFeedbackFrame = new AboutUsandFeedback();
                aboutUsAndFeedbackFrame.setVisible(true); // Show the AboutUsandFeedback frame
                dispose(); // Close the current home frame
        	}
        });
        btnAboutUs.setForeground(Color.WHITE);
        btnAboutUs.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAboutUs.setBackground(Color.BLACK);
        btnAboutUs.setBounds(26, 338, 174, 40);
        btnAboutUs.setFocusPainted(false); // Disable focus outline
        btnAboutUs.setBorderPainted(false); // Disable border outline
        sideNav.add(btnAboutUs);
        
        JButton btnContactUs = new JButton("Contact Us");
        btnContactUs.setForeground(Color.WHITE);
        btnContactUs.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnContactUs.setBackground(Color.BLACK);
        btnContactUs.setBounds(26, 411, 174, 40);
        btnContactUs.setFocusPainted(false); // Disable focus outline
        btnContactUs.setBorderPainted(false); // Disable border outline
        sideNav.add(btnContactUs);

        // Header Labels
        JLabel lblNewLabel_1 = new JLabel("CATCH");
        lblNewLabel_1.setBounds(10, 0, 138, 63);
        contentPane.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 35));

        JLabel lblNewLabel_1_1 = new JLabel("ME");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1_1.setFont(new Font("Bookman Old Style", Font.BOLD, 35));
        lblNewLabel_1_1.setBounds(143, 0, 58, 63);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("On time, Every time");
        lblNewLabel_1_2.setFont(new Font("Lucida Handwriting", Font.PLAIN, 16));
        lblNewLabel_1_2.setBounds(94, 33, 202, 63);
        contentPane.add(lblNewLabel_1_2);
        
        // Bottom search bar panel
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(204, 187, 939, 82);
        contentPane.add(panel_1);
        panel_1.setBackground(new Color(0, 0, 0));
        panel_1.setLayout(null);
                
        // Add search bar (JTextField)
        searchField = new JTextField();
        searchField.setBounds(50, 28, 587, 30);
        panel_1.add(searchField);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchField.setColumns(10);
                        
        // Add search button
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(255, 255, 255));
        searchButton.setBounds(666, 28, 100, 30);
        panel_1.add(searchButton);
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Adjusted Button with Icon for Rides
        JButton btnRides = new JButton("Rides");
        btnRides.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Navigate to the BookingInfo frame
                BookingInfo bookingInfoFrame = new BookingInfo();
                bookingInfoFrame.setVisible(true); // Show the BookingInfo frame
                dispose(); // Close the current home frame
        	}
        });
        btnRides.setIcon(new ImageIcon(new ImageIcon(home.class.getResource("/Icons/ride.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        btnRides.setBounds(628, 527, 236, 192);
        btnRides.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRides.setVerticalTextPosition(JButton.BOTTOM);
        btnRides.setBackground(new Color(255, 255, 255));
        btnRides.setHorizontalTextPosition(JButton.CENTER);
        btnRides.setForeground(new Color(0, 0, 0)); // Text color to match other buttons
        contentPane.add(btnRides);
        
        JButton btnBookingHistory = new JButton("Bookings");
        btnBookingHistory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnBookingHistory.setIcon(new ImageIcon(new ImageIcon(home.class.getResource("/Icons/history.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        btnBookingHistory.setBounds(628, 311, 236, 200);
        btnBookingHistory.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBookingHistory.setVerticalTextPosition(JButton.BOTTOM);
        btnBookingHistory.setBackground(new Color(255, 255, 255));
        btnBookingHistory.setHorizontalTextPosition(JButton.CENTER);
        contentPane.add(btnBookingHistory);
                                                  
        JButton btnRoutes = new JButton("Routes");
        btnRoutes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Open the DisplayRoutes frame
                DisplayRoutes displayRoutesFrame = new DisplayRoutes();
                displayRoutesFrame.setVisible(true); // Show the DisplayRoutes frame
                dispose(); // Close the current home frame
        	}
        });
        btnRoutes.setIcon(new ImageIcon(new ImageIcon(home.class.getResource("/Icons/routes.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        btnRoutes.setBounds(351, 527, 236, 192);
        btnRoutes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRoutes.setBackground(new Color(255, 255, 255));
        btnRoutes.setVerticalTextPosition(JButton.BOTTOM);
        btnRoutes.setHorizontalTextPosition(JButton.CENTER);
        contentPane.add(btnRoutes);
                                                  
        // Adjusted Button with Icon for Bookings
        JButton btnBookings = new JButton("Ticket Prices");
        btnBookings.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Navigate to the TicketPriceTable frame
                TicketPriceTable ticketPriceTableFrame = new TicketPriceTable();
                ticketPriceTableFrame.setVisible(true); // Show the TicketPriceTable frame
                dispose(); // Close the current home frame
        		
        	}
        });
        btnBookings.setIcon(new ImageIcon(new ImageIcon(home.class.getResource("/Icons/booking.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        btnBookings.setBounds(351, 319, 236, 192); // Adjusted dimensions to match other buttons
        btnBookings.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBookings.setBackground(new Color(255, 255, 255));
        btnBookings.setVerticalTextPosition(JButton.BOTTOM);
        btnBookings.setHorizontalTextPosition(JButton.CENTER);
        btnBookings.setForeground(new Color(0, 0, 0)); // Text color to match other buttons
        contentPane.add(btnBookings);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(home.class.getResource("/Icons/icon.png")));
        lblNewLabel.setBounds(306, 10, 692, 180);
        contentPane.add(lblNewLabel);
        
        
    }
}




