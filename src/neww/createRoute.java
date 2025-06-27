package neww;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner.DateEditor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class createRoute {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
				try {
					createRoute window = new createRoute();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		});
	}

	/**
	 * Create the application.
	 */
	public createRoute() {
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
		
        // Disable maximize and full screen
        frame.setResizable(false); // Disable resizing the window
        frame.setExtendedState(JFrame.NORMAL); // Set to normal mode, not maximized
		
		
		JLabel lblNewLabel = new JLabel("CREATE ROUTES");
		lblNewLabel.setBounds(732, 165, 201, 47);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Route Number");
		lblNewLabel_1.setBounds(633, 297, 137, 13);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBackground(new Color(255, 224, 73));
		textField.setBounds(841, 291, 194, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Route Name");
		lblNewLabel_2.setBounds(633, 352, 121, 13);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setBackground(new Color(255, 224, 73));
		textField_1.setBounds(841, 346, 194, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Starting Location");
		lblNewLabel_3.setBounds(633, 407, 194, 24);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setBackground(new Color(255, 224, 73));
		textField_2.setBounds(841, 406, 194, 30);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Destination");
		lblNewLabel_4.setBounds(632, 473, 137, 13);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_3.setBackground(new Color(255, 224, 73));
		textField_3.setBounds(841, 467, 194, 30);
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_5 = new JLabel("Time");
		lblNewLabel_5.setBounds(633, 539, 45, 13);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel_5);
		
		JSpinner timeSpinner = new JSpinner(new SpinnerDateModel (new Date(), null, null, java.util.Calendar.HOUR_OF_DAY));
		timeSpinner.setForeground(new Color(255, 224, 73));
		timeSpinner.setBackground(new Color(255, 224, 73));
		timeSpinner.setFont(new Font("Tahoma", Font.BOLD, 14));
		timeSpinner.setBounds(841, 533, 194, 30);
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,"HH:mm");
		timeSpinner.setEditor(timeEditor);
		frame.getContentPane().add(timeSpinner);
		
		
		RoundedButton btnNewButton = new RoundedButton("Submit",20);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(750, 631, 104, 30);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton);
		
		
		btnNewButton.addActionListener (e -> {
			
			if (validateForm()) {
				routeManager routeManager = new routeManager();
				
				try {
					int routeNumber = Integer.parseInt(textField.getText());
					String routeName = textField_1.getText();
					String startingLocation = textField_2.getText();
					String destination = textField_3.getText();
					
					DateEditor timeEditor1 = (DateEditor) timeSpinner.getEditor();
					String time = timeEditor1.getFormat().format(timeSpinner.getValue());

					routeManager.addRoute(routeNumber, routeName, startingLocation, destination, time);
					JOptionPane.showMessageDialog(frame, "Route added successfully");
					
					Routes routesWindow = new Routes();
					frame.dispose();
					((Window) routesWindow.frame).setVisible(true);
					
					
					
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "route number must be a valid number");
				}
				
			}
			
		});
			
			frame.getContentPane().add(btnNewButton);
			
			JLabel lblNewLabel_6 = new JLabel("");
			lblNewLabel_6.setBounds(58, 410, 45, 13);
			frame.getContentPane().add(lblNewLabel_6);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(197, 238, 254));
			panel.setBounds(0, 0, 603, 739);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setBackground(new Color(169, 231, 254));
			lblNewLabel_7.setIcon(new ImageIcon(createRoute.class.getResource("/Icons/buuus (1).jpg")));
			lblNewLabel_7.setBounds(-58, 133, 661, 542);
			panel.add(lblNewLabel_7);
	}
		//validate the fields
		private boolean validateForm() {
	    // Check if any field is empty
	    if (textField.getText().isEmpty() || textField_1.getText().isEmpty() || 
	        textField_2.getText().isEmpty() || textField_3.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(frame, "All fields are required");
	        return false; // Return false if any field is empty
	    }

	    // Now that we know no fields are empty, check other validations
	    if (!isNumeric(textField.getText())) {
	        JOptionPane.showMessageDialog(frame, "Route Number must be numeric");
	        return false; // Return false if Route Number is not numeric
	    }

	    // Add any additional validation checks here if necessary

	    return true; // Return true if all validations pass
	}

		// to check if the string is numeric
		private boolean isNumeric(String str) {
			return str.matches("\\d+");
			
	}
		
		

		// Custom rounded button class
		class RoundedButton extends JButton {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private int radius;

		    public RoundedButton(String label, int radius) {
		        super(label);
		        this.radius = radius;
		        setOpaque(false);  // Make the button background transparent
		        setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding inside the button
		        setContentAreaFilled(false);  // Disable default content area painting
		    }

		    @Override
		    protected void paintComponent(Graphics g) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // Enable anti-aliasing
		        g2.setColor(getBackground());  // Set the background color
		        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));  // Create a rounded rectangle
		        super.paintComponent(g);
		        g2.dispose();  // Dispose of graphics context
		    }

		    @Override
		    public void paintBorder(Graphics g) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(getForeground());  // Set the color for the border
		        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));  // Draw the border
		        g2.dispose();
		    }
		}



		public void setVisible(boolean b) {
			frame.setVisible(b);
	
			
		}

		
}


		
