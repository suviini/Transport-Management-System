package neww;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import transport.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Routes extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable routesTable;
    private DefaultTableModel tableModel;
    private JButton updateButton, deleteButton, refreshButton;
	public Object frame;

    

    public Routes() {
        setTitle("Routes Management");

        // Set background color for the entire frame
        getContentPane().setBackground(new Color(255, 224, 73)); // Set background color of the frame
        getContentPane().setLayout(null);

        // Add a label for the "ROUTES" topic at the top
        JLabel titleLabel = new JLabel("ROUTES", SwingConstants.CENTER);
        titleLabel.setBounds(0, 27, 1099, 41);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK); // Set title color to black
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(255, 255, 255)); // Set background color of title label
        getContentPane().add(titleLabel);

        // Initialize table model and table
        tableModel = new DefaultTableModel(new String[]{"Route Number", "Route Name", "Starting Location", "Destination", "Time"}, 0);
        routesTable = new JTable(tableModel) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                // Set the background color for the rows
                if (!isRowSelected(row)) {
                    comp.setBackground(Color.WHITE); // Set row background to white
                }
                return comp;
            }
        };

        // Set table properties
        routesTable.setBackground(Color.WHITE); // Set table background color
        routesTable.setForeground(Color.BLACK); // Set table text color
        routesTable.setFont(new Font("Arial", Font.PLAIN, 16)); // Set content font size to 16
        routesTable.setRowHeight(30); // Set the preferred row height to 30 pixels
        routesTable.getTableHeader().setForeground(Color.BLACK); // Set header text color

        // Set bold font for the table header and increase font size
        Font headerFont = new Font("Arial", Font.BOLD, 18); // Set header font size to 18
        routesTable.getTableHeader().setFont(headerFont);
        routesTable.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set header height to 40 pixels

        JScrollPane scrollPane = new JScrollPane(routesTable);
        scrollPane.setBounds(52, 101, 990, 542);
        getContentPane().add(scrollPane);

        // Initialize buttons
        updateButton = createRoundedButton("Update");
        deleteButton = createRoundedButton("Delete");
        refreshButton = createRoundedButton("Refresh");
        JButton backButton = createRoundedButton("Back");

        // Set button panel background color
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 668, 1073, 50);
        buttonPanel.setBackground(new Color(255, 224, 73)); // Set background color of the button panel
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        getContentPane().add(buttonPanel);

        // Action listeners
        updateButton.addActionListener(new UpdateButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        refreshButton.addActionListener(e -> loadRoutes());
        backButton.addActionListener(e ->{
        	// Dispose of the Routes frame
            dispose();
            
            // Open the Admin frame
            Admin adminFrame = new Admin();
            adminFrame.setVisible(true);
        });

        // Load initial route data
        loadRoutes();

        setSize(1113,776);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK); // Set button background color to black
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Set rounded corners
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.PLAIN, 16)); // Set button font size
        button.setForeground(Color.WHITE); // Set button text color to white
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false); // Make the button transparent for rounded corners
        button.setPreferredSize(new Dimension(100, 40)); // Set button size
        return button;
    }

    private void loadRoutes() {
        // Clear the existing table data
        tableModel.setRowCount(0);
        String query = "SELECT * FROM routes"; // Adjust your query as needed
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int routeNumber = rs.getInt("routeNumber");
                String routeName = rs.getString("routeName");
                String startingLocation = rs.getString("startingLocation");
                String destination = rs.getString("destination");
                String time = rs.getString("time");
                tableModel.addRow(new Object[]{routeNumber, routeName, startingLocation, destination, time});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = routesTable.getSelectedRow();
            if (selectedRow != -1) {
                int routeNumber = (int) routesTable.getValueAt(selectedRow, 0);
                String routeName = (String) routesTable.getValueAt(selectedRow, 1);
                String startingLocation = (String) routesTable.getValueAt(selectedRow, 2);
                String destination = (String) routesTable.getValueAt(selectedRow, 3);
                String time = (String) routesTable.getValueAt(selectedRow, 4);

                // Open the dialog for updating
                new RouteUpdateDialog(routeNumber, routeName, startingLocation, destination, time);
            } else {
                JOptionPane.showMessageDialog(Routes.this, "Please select a route to update.");
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = routesTable.getSelectedRow();
            if (selectedRow != -1) {
                int routeNumber = (int) routesTable.getValueAt(selectedRow, 0);
                deleteRoute(routeNumber);
                loadRoutes(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(Routes.this, "Please select a route to delete.");
            }
        }
    }

    private void deleteRoute(int routeNumber) {
        String deleteQuery = "DELETE FROM routes WHERE routeNumber = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, routeNumber);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inner class for the update dialog
    private class RouteUpdateDialog extends JDialog {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextField routeNameField, startingLocationField, destinationField, timeField;
        private JButton saveButton;
        private int routeNumber;

        public RouteUpdateDialog(int routeNumber, String routeName, String startingLocation, String destination, String time) {
            this.routeNumber = routeNumber;
            setTitle("Update Route");
            
            getContentPane().setBackground(new Color(255, 255, 204));
            setLayout(new GridLayout(5, 2, 10, 10)); // Add gaps between components

            // Fields for updating
            add(new JLabel("Route Name:"));
            routeNameField = new JTextField(routeName);
            add(routeNameField);

            add(new JLabel("Starting Location:"));
            startingLocationField = new JTextField(startingLocation);
            add(startingLocationField);

            add(new JLabel("Destination:"));
            destinationField = new JTextField(destination);
            add(destinationField);

            add(new JLabel("Time:"));
            timeField = new JTextField(time);
            add(timeField);

            // Save button
            saveButton = createRoundedButton("Save");
            saveButton.setPreferredSize(new Dimension(10, 10));
            add(saveButton);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateRoute();
                }
            });

            setSize(400, 300);
            setLocationRelativeTo(Routes.this);
            setVisible(true);
        }

        private void updateRoute() {
            String routeName = routeNameField.getText();
            String startingLocation = startingLocationField.getText();
            String destination = destinationField.getText();
            String time = timeField.getText();

            String updateQuery = "UPDATE routes SET routeName=?, startingLocation=?, destination=?, time=? WHERE routeNumber=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                pstmt.setString(1, routeName);
                pstmt.setString(2, startingLocation);
                pstmt.setString(3, destination);
                pstmt.setString(4, time);
                pstmt.setInt(5, routeNumber);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(Routes.this, "Route updated successfully.");
                dispose(); // Close the dialog
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Routes::new);
    }
}
