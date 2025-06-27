package publicTransport;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BookingInfo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBox; //combo box to select routes

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	//Check if a user is logged in before proceeding
            if (Session.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(null, "Please log in to make a booking.");
                System.exit(0); // Ensure user is logged in
            } else {
                new BookingInfo().setVisible(true); //show booking info window
            }
        });
    }

    public BookingInfo() {
        setTitle("Transport Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1113, 776);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 222, 73));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //Create a panel for the booking controls

        JPanel panel = new JPanel();
        panel.setBackground(new Color(254, 222, 73));
        panel.setBounds(0, 0, 420, 708);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Select your Route!");
        lblNewLabel.setBounds(103, 179, 286, 44);
        panel.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));

        JLabel lblRouteSelection = new JLabel("Route Selection");
        lblRouteSelection.setBounds(25, 277, 150, 25);
        panel.add(lblRouteSelection);
        lblRouteSelection.setBackground(new Color(0, 0, 0));
        lblRouteSelection.setForeground(new Color(0, 0, 0));
        lblRouteSelection.setFont(new Font("Tahoma", Font.BOLD, 16));

        // Initialize ComboBox for routes
        comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(254, 222, 73));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox.setBounds(200, 277, 210, 25);
        panel.add(comboBox);

        // Load Routes from Database
        loadRoutesFromDatabase();
        
        //Label for date selection
        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(25, 352, 119, 20);
        panel.add(lblDate);
        lblDate.setBackground(new Color(0, 0, 0));
        lblDate.setForeground(new Color(0, 0, 0));
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));

        //Spinner for day selection
        JSpinner daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        daySpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        daySpinner.setBounds(200, 349, 50, 25);
        panel.add(daySpinner);
        
      //Spinner for month selection
        JSpinner monthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        monthSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        monthSpinner.setBounds(260, 349, 50, 25);
        panel.add(monthSpinner);

      //Spinner for year selection
        JSpinner yearSpinner = new JSpinner(new SpinnerNumberModel(2024, 2023, 2030, 1));
        yearSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        yearSpinner.setBounds(320, 349, 90, 25);
        panel.add(yearSpinner);
        
      //label for time selection
        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(25, 435, 119, 20);
        panel.add(lblTime);
        lblTime.setBackground(new Color(0, 0, 0));
        lblTime.setForeground(new Color(0, 0, 0));
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 16));

        JSpinner timeSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        timeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        timeSpinner.setBounds(200, 432, 80, 25);
        panel.add(timeSpinner);
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));

        JButton btnBook = new JButton("Book");
        btnBook.setForeground(new Color(255, 255, 255));
        btnBook.setBackground(new Color(0, 0, 0));
        btnBook.setBounds(145, 529, 135, 44);
        panel.add(btnBook);
        btnBook.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(BookingInfo.class.getResource("/Icons/BusImage1.jpg")));
        lblNewLabel_1.setBounds(419, 0, 680, 755);
        contentPane.add(lblNewLabel_1);

        //Action listener for the book button
        btnBook.addActionListener((ActionEvent e) -> {
            // Validate date
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set((Integer) yearSpinner.getValue(), (Integer) monthSpinner.getValue() - 1, (Integer) daySpinner.getValue());

            //Check if the selected date is in the past
            if (selectedDate.before(Calendar.getInstance())) {
                JOptionPane.showMessageDialog(null, "Please select a future date.");
                return;
            }

            String selectedRoute = (String) comboBox.getSelectedItem();
            String selectedDateStr = String.format("%04d-%02d-%02d", yearSpinner.getValue(), monthSpinner.getValue(), daySpinner.getValue());
            
            // Validate route availability with time
            String time = new SimpleDateFormat("HH:mm:ss").format(timeSpinner.getValue());
            if (!isRouteAvailableOnDate(selectedRoute,time)) {
                JOptionPane.showMessageDialog(null, "The selected route is not available at the chosen time", "Invalid Booking", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create the booking if all validations pass
            createBooking(Session.getCurrentUser().getId(), selectedRoute, selectedDateStr, time);
        });
    }

    // Method to load routes from the database
    private void loadRoutesFromDatabase() {
        String query = "SELECT routeName FROM routes";  

        try (Connection conn = DatabaseConnection.getConnection(); //Establish database connection
             PreparedStatement stmt = conn.prepareStatement(query); //Prepare the SQL statement
             ResultSet rs = stmt.executeQuery()) { // Execute the query

            comboBox.removeAllItems(); 

            //Add routes to the combobox
            while (rs.next()) {
                comboBox.addItem(rs.getString("routeName"));
            }

        } catch (SQLException e) {
        	//Handle any SQL exceptions that occur while loading routes
            JOptionPane.showMessageDialog(null, "Error loading routes: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to check if the selected route is available on the chosen time
    private boolean isRouteAvailableOnDate(String route, String time) {
        String query = "SELECT COUNT(*) FROM routes WHERE routeName = ?  AND time = ?";  

        try (Connection conn = DatabaseConnection.getConnection(); // Establish database connection
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, route);
            pstmt.setString(2, time);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	// Check if any routes are available
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
        	// Handle any SQL exceptions that occur while checking route availability
            JOptionPane.showMessageDialog(null, "Error checking route availability: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Return false if an error occurs or no routes are found
    }

    // Method to create a booking in the database
    private void createBooking(int userId, String route, String date, String time) {
        String sql = "INSERT INTO bookings (user_id, route, booking_date, booking_time) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();// Establish database connection
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, route);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int bookingId = generatedKeys.getInt(1);
                    JOptionPane.showMessageDialog(null, "Booking successful! Booking ID: " + bookingId);

                    // Open ViewBookingInfo frame
                    new ViewBookingInfo(userId).setVisible(true);
                    dispose();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error creating booking: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}