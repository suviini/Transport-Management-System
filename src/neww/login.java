package neww;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import transport.Admin;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(254, 222, 73));
		frame.setBounds(100, 100, 1113, 776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        // Disable maximize and full screen
        frame.setResizable(false); // Disable resizing the window
        frame.setExtendedState(JFrame.NORMAL); // Set to normal mode, not maximized
		
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Admin Portal ");
		lblNewLabel.setBounds(21, 135, 450, 43);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setBounds(50, 260, 135, 13);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBackground(new Color(254, 222, 73));
		textField.setBounds(175, 254, 235, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(50, 359, 93, 13);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JPasswordField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setBackground(new Color(254, 222, 73));
		textField_1.setBounds(175, 353, 235, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(114, 472, 110, 36);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton);
		
		ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/Icons/pic.jpg"));
		JLabel imageLabel1 = new JLabel(imageIcon1);
		frame.getContentPane().add(imageLabel1);
		
		ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/Icons/pic.jpg"));
		JLabel imageLabel2 = new JLabel(imageIcon2);
		frame.getContentPane().add(imageLabel2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(468, 0, 894, 763);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(login.class.getResource("/Icons/admin1 (2).jpg")));
		lblNewLabel_3.setBounds(0, 133, 1118, 605);
		panel.add(lblNewLabel_3);
		
		btnNewButton.addActionListener(e -> {
		    String username = textField.getText();
		    String password = new String(textField_1.getPassword());

		    // Check if username or password is empty
		    if (username.isEmpty() || password.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "All fields are required");
		        return;  // Exit the method early if either field is empty
		    }

		    // This part will only run if both fields are filled
		    if (checkLogin(username, password)) {
		        JOptionPane.showMessageDialog(null, "Login successful, Welcome Jayakody");
		        

		        // Navigate to Admin window
		        Admin adminFrame = new Admin();
		        adminFrame.setVisible(true);
		        frame.dispose(); // Close login frame
		    } else {
		        JOptionPane.showMessageDialog(null, "Invalid Username or Password");
		    }
		});


	}
	
	private boolean checkLogin(String username , String password) {
		
		String url = "jdbc:mysql://localhost:3306/transport_db";
		String dbuser = "root";
		String dbPassword = "";
		
		try(Connection conn = DriverManager.getConnection(url,dbuser, dbPassword)){
			String sql = "SELECT * FROM tlogin WHERE UserName =? AND Password =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
		
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
		
	}
}


