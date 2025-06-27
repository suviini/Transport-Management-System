package publicTransport;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateBookingInfo extends JFrame {

    private static final long serialVersionUID = 6205891674476003053L;
    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JSpinner daySpinner, monthSpinner, yearSpinner, timeSpinner;
    private JButton btnUpdate;
    private int bookingId;
    private ViewBookingInfo viewBookingInfo;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new UpdateBookingInfo(1, new ViewBookingInfo(1)).setVisible(true)); 
    }

    public UpdateBookingInfo(int bookingId, ViewBookingInfo viewBookingInfo) {
        this.bookingId = bookingId;
        this.viewBookingInfo = viewBookingInfo;
        setTitle("Update Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1113, 776);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 224, 73));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setupComponents();
        loadRoutesFromDatabase();  // Load routes into comboBox
        populateFields(); // Populate fields with existing booking data

        btnUpdate.addActionListener(e -> updateBooking());
    }

    private void setupComponents() {
        JLabel lblTitle = new JLabel("Update Your Booking", JLabel.CENTER);
        lblTitle.setBounds(10, 201, 456, 41);
        contentPane.add(lblTitle);
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 27));

        JLabel lblRouteSelection = new JLabel("Route Selection");
        lblRouteSelection.setBounds(32, 292, 150, 25);
        contentPane.add(lblRouteSelection);
        lblRouteSelection.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        comboBox = new JComboBox<>();
        comboBox.setBounds(230, 292, 211, 25);
        contentPane.add(comboBox);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(32, 360, 119, 20);
        contentPane.add(lblDate);
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        daySpinner.setBounds(227, 357, 50, 25);
        contentPane.add(daySpinner);
        daySpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        monthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        monthSpinner.setBounds(311, 357, 50, 25);
        contentPane.add(monthSpinner);
        monthSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        yearSpinner = new JSpinner(new SpinnerNumberModel(2024, 2023, 2030, 1));
        yearSpinner.setBounds(397, 357, 73, 25);
        contentPane.add(yearSpinner);
        yearSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(32, 449, 119, 20);
        contentPane.add(lblTime);
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        timeSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        timeSpinner.setBounds(230, 446, 80, 25);
        contentPane.add(timeSpinner);
        timeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));
        
        btnUpdate = new JButton("Update");
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setBackground(new Color(0, 0, 0));
        btnUpdate.setBounds(126, 542, 120, 30);
        contentPane.add(btnUpdate);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(UpdateBookingInfo.class.getResource("/Icons/BusImage1.jpg")));
        lblNewLabel.setBounds(498, 0, 670, 710);
        contentPane.add(lblNewLabel);
    }

    private void loadRoutesFromDatabase() {
        String query = "SELECT routeName FROM routes";  

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            comboBox.removeAllItems();// Clear comboBox

            while (rs.next()) {
                comboBox.addItem(rs.getString("routeName"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading routes: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateFields() {
        String sql = "SELECT route, booking_date, booking_time FROM bookings WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    comboBox.setSelectedItem(rs.getString("route"));

                    // Set date
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(rs.getDate("booking_date"));
                    daySpinner.setValue(cal.get(Calendar.DAY_OF_MONTH));
                    monthSpinner.setValue(cal.get(Calendar.MONTH) + 1); 
                    yearSpinner.setValue(cal.get(Calendar.YEAR));

                    // Set time
                    timeSpinner.setValue(rs.getTime("booking_time"));
                } else {
                    showError("Booking not found.");
                }
            }
        } catch (SQLException e) {
            showError("Error retrieving booking data: " + e.getMessage());
        }
    }

    private void updateBooking() {
        String date = String.format("%04d-%02d-%02d", yearSpinner.getValue(), monthSpinner.getValue(), daySpinner.getValue());
        String time = new SimpleDateFormat("HH:mm:ss").format(timeSpinner.getValue());
        String route = (String) comboBox.getSelectedItem();

        if (!isDateValid()) {
            showError("Please select a future date.");
            return;
        }

        String sql = "UPDATE bookings SET route = ?, booking_date = ?, booking_time = ? WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, route);
            pstmt.setString(2, date);
            pstmt.setString(3, time);
            pstmt.setInt(4, bookingId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Booking updated successfully!");
                viewBookingInfo.refreshTableData(); // Refresh the table data in ViewBookingInfo
                dispose(); // Close update window
            } else {
                showError("No booking found with the provided ID.");
            }
        } catch (SQLException ex) {
            showError("Error updating booking: " + ex.getMessage());
        }
    }

    private boolean isDateValid() {
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set((int) yearSpinner.getValue(), (int) monthSpinner.getValue() - 1, (int) daySpinner.getValue());

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return !selectedDate.before(today);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
