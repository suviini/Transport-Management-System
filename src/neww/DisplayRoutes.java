package neww;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import transport.home;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayRoutes extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable routesTable;
    private DefaultTableModel tableModel;

    public DisplayRoutes() {
        setTitle("Routes Viewer");

        // Set background color for the entire frame
        getContentPane().setBackground(new Color(255, 224, 73));
        getContentPane().setLayout(null);

        // Add a label for the "ROUTES" topic at the top
        JLabel titleLabel = new JLabel("ROUTES", SwingConstants.CENTER);
        titleLabel.setBounds(0, 27, 1099, 41);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(255, 255, 255));
        getContentPane().add(titleLabel);

        // Initialize table model and table
        tableModel = new DefaultTableModel(new String[]{"Route Number", "Route Name", "Starting Location", "Destination", "Time"}, 0);
        routesTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    comp.setBackground(Color.WHITE);
                }
                return comp;
            }
        };

        // Set table properties
        routesTable.setBackground(Color.WHITE);
        routesTable.setForeground(Color.BLACK);
        routesTable.setFont(new Font("Arial", Font.PLAIN, 16));
        routesTable.setRowHeight(30);
        routesTable.getTableHeader().setForeground(Color.BLACK);

        // Set bold font for the table header
        Font headerFont = new Font("Arial", Font.BOLD, 18);
        routesTable.getTableHeader().setFont(headerFont);
        routesTable.getTableHeader().setPreferredSize(new Dimension(100, 40));

        JScrollPane scrollPane = new JScrollPane(routesTable);
        scrollPane.setBounds(72, 120, 946, 523);
        getContentPane().add(scrollPane);

        // Button panel with only a back button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 668, 1073, 50);
        buttonPanel.setBackground(new Color(255, 224, 73));
        JButton backButton = createRoundedButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            // Open the Admin frame
            new home().setVisible(true);
        });
        buttonPanel.add(backButton);
        getContentPane().add(buttonPanel);

        // Load initial route data
        loadRoutes();

        setSize(1113, 776);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }

    private void loadRoutes() {
        tableModel.setRowCount(0);
        String query = "SELECT * FROM routes";
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DisplayRoutes::new);
    }
}
