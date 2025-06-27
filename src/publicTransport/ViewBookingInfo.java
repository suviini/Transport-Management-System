package publicTransport;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.*;
import transport.home;

public class ViewBookingInfo extends JFrame {

    private static final long serialVersionUID = 5540802098422592677L;
    private JPanel contentPane;
    private JTable table;
    private int userId;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            if (Session.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(null, "Please log in to view your bookings.");
                System.exit(0);
            } else {
                new ViewBookingInfo(Session.getCurrentUser().getId()).setVisible(true);
            }
        });
    }

    /**
     * Create the frame.
     */
    public ViewBookingInfo(int userId) {
        this.userId = userId;
        setTitle("View Booking Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1113, 776);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 224, 73));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Display logged-in user's name
        JLabel lblUserName = new JLabel("Logged in as: " + Session.getCurrentUser().getName());
        lblUserName.setBackground(new Color(0, 0, 0));
        lblUserName.setForeground(new Color(0, 0, 0));
        lblUserName.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUserName.setBounds(50, 20, 300, 25);
        contentPane.add(lblUserName);

        // Table setup
        String[] columnNames = {"Booking ID", "Date", "Time", "Route"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 16));
        header.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(130, 135, 898, 250);
        contentPane.add(scrollPane);

        // Load booking data
        loadBookingData(model);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setBackground(new Color(0, 0, 0));
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnUpdate.setBounds(230, 432, 120, 40);
        contentPane.add(btnUpdate);

        JButton btnCancel = new JButton("Cancel Booking");
        btnCancel.setForeground(new Color(255, 255, 255));
        btnCancel.setBackground(new Color(0, 0, 0));
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancel.setBounds(469, 432, 200, 40);
        contentPane.add(btnCancel);

        JButton btnBack = new JButton("Back");
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBack.setBackground(new Color(0, 0, 0));
        btnBack.setBounds(765, 432, 200, 40);
        contentPane.add(btnBack);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 53, 1099, 40);
        contentPane.add(panel);

        JLabel lblTitle = new JLabel("Your Bookings");
        panel.add(lblTitle);
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));

        // Action Listeners
        btnUpdate.addActionListener(e -> handleUpdateAction());
        btnCancel.addActionListener(e -> handleCancelAction(model));
        btnBack.addActionListener(e -> {
            new home().setVisible(true); // Open Homepage frame
            dispose(); // Close the current frame
        });
    }

    /**
     * Loads booking data from the database into the table model.
     */
    private void loadBookingData(DefaultTableModel model) {
        String sql = "SELECT booking_id, booking_date, booking_time, route FROM bookings WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("booking_id"),
                        rs.getDate("booking_date").toString(),
                        rs.getTime("booking_time").toString(),
                        rs.getString("route")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handle update booking action.
     */
    private void handleUpdateAction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int bookingId = (Integer) table.getValueAt(selectedRow, 0);
            new UpdateBookingInfo(bookingId, this).setVisible(true); // Pass `this` reference
        } else {
            JOptionPane.showMessageDialog(this, "Please select a booking to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Handle cancel booking action.
     */
    private void handleCancelAction(DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int bookingId = (Integer) table.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this booking?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cancelBooking(bookingId, model, selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Cancels a booking from the database and removes it from the table.
     */
    private void cancelBooking(int bookingId, DefaultTableModel model, int selectedRow) {
        String sql = "DELETE FROM bookings WHERE booking_id = ? AND user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.setInt(2, userId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Booking cancelled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to cancel booking. Please try again.", "Cancellation Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error cancelling booking: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Refreshes the table data after an update.
     */
    public void refreshTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data
        loadBookingData(model); // Reload the data
    }
}
