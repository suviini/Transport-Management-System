package transport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicketPriceTable extends JFrame {

    private static final long serialVersionUID = 1L;

    public TicketPriceTable() {
        setBackground(new Color(255, 224, 73));
        setTitle("Ticket Prices");
        setSize(1113, 776);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Define column names for the table
        String[] columnNames = {"Route Name", "Starting Location", "Destination", "Ticket Price"};

        // Sample data for the table
        Object[][] data = {
            {"Colombo - Kandy", "Colombo", "Kandy", "Rs 150"},
            {"Kaduwela - Kollupitiya", "Kaduwela", "Kollupitiya", "Rs 80"},
            {"Gampaha - Kaduwela", "Gampaha", "Kaduwela", "Rs 100"},
            {"Pettah - Kaduwela", "Pettah", "Kaduwela", "Rs 90"},
            {"Kurunegala - Panadura", "Kurunegala", "Panadura", "Rs 200"},
            {"Kandy - Panadura", "Kandy", "Panadura", "Rs 180"},
            {"Galle - Kaduwela", "Galle", "Kaduwela", "Rs 500"}
        };

        // Create the table model and set it to the table
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// Override the getColumnClass method to set the header renderer
            @Override
            public Class<?> getColumnClass(int column) {
                return String.class;
            }
        };

        // Set table properties
        table.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Updated font style and size
        table.setRowHeight(40); // Increased row height for better visibility
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.BLACK);

        // Set the header properties
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16)); // Bold font for headers
        table.getTableHeader().setBackground(new Color(255, 215, 0)); // Gold color for header
        table.getTableHeader().setForeground(Color.BLACK);

        // Set column widths for better spacing
        table.getColumnModel().getColumn(0).setPreferredWidth(250); // Route Name
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Starting Location
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Destination
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Ticket Price

        getContentPane().setLayout(null);

        // Create a JScrollPane to make the table scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 113, 851, 307);
        scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(scrollPane);

        // Set background color for the frame
        getContentPane().setBackground(new Color(255, 224, 73)); // Updated background color

        JLabel lblNewLabel = new JLabel("Ticket Information");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblNewLabel.setBounds(345, 37, 321, 39);
        getContentPane().add(lblNewLabel);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
       
        	}
        });
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setBackground(new Color(0, 0, 0));
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnBack.setBounds(489, 560, 112, 39);
        getContentPane().add(btnBack);
        
     // Add action listener to the Back button
        btnBack.addActionListener(e -> {
            // Create an instance of the home frame
            home homeFrame = new home();
            
            // Make the home frame visible
            homeFrame.setVisible(true);
            
            // Close the current TicketPriceTable frame
            dispose();
        });


        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicketPriceTable());
    }
}
