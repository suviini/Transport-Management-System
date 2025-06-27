package transport;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import publicTransport.Signup;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import driverManagement.DriverLogin;


//class to make the buttons round
class RoundedButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int radius;
    private Color borderColor;

    public RoundedButton(String text, int radius, Color borderColor) {
        super(text);
        this.radius = radius;
        this.borderColor = borderColor;
        setOpaque(false); // Make it transparent
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        // Do nothing here to make sure it's not filled by default
    }
}

public class Page {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page window = new Page();
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
	public Page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1113, 776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255, 224, 73));
		
		
		JLabel lblNewLabel = new JLabel("CATCH ");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 38));
		lblNewLabel.setBounds(737, 337, 157, 70);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMe = new JLabel("Me");
		lblMe.setForeground(new Color(255, 255, 255));
		lblMe.setFont(new Font("Bookman Old Style", Font.BOLD, 38));
		lblMe.setBounds(884, 343, 61, 58);
		frame.getContentPane().add(lblMe);
		
		

		//login button for customer
		transport.RoundedButton rndbtnLoginAsCustomer = new transport.RoundedButton("Login as Customer", 20, Color.BLACK);
		rndbtnLoginAsCustomer.setText("Login as Customer");
		rndbtnLoginAsCustomer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Open the login page for the customer
		        publicTransport.Login loginPage = new publicTransport.Login(); // Create an instance of Login class
		        loginPage.setVisible(true); // Show the login page
		        frame.dispose(); // Close the current page (Page frame)
		    }
		});
		
		
		rndbtnLoginAsCustomer.setForeground(Color.WHITE);
		rndbtnLoginAsCustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rndbtnLoginAsCustomer.setBackground(Color.BLACK);
		rndbtnLoginAsCustomer.setBounds(716, 489, 213, 44);
		frame.getContentPane().add(rndbtnLoginAsCustomer);

		
		JLabel lblNewLabel_1 = new JLabel("No Account?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(677, 563, 107, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		// Define your yellow color for consistency
		@SuppressWarnings("unused")
		Color yellowColor = new Color(255, 182, 10);
		
		
		JButton btnNewButton_1_2 = new JButton("Sign In Customer");
		btnNewButton_1_2.setForeground(new Color(0, 0, 0));
		btnNewButton_1_2.setBackground(new Color(255, 224, 73));
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_2.setBorder(null);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				Signup signupWindow = new Signup();
		        Signup.main(null);  
		        frame.dispose(); 
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_2.setBounds(816, 565, 144, 21);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JLabel lblNewLabel_3 = new JLabel("On Time, Every Time!");
		lblNewLabel_3.setFont(new Font("Lucida Handwriting", Font.ITALIC, 15));
		lblNewLabel_3.setBounds(819, 395, 216, 22);
		frame.getContentPane().add(lblNewLabel_3);
		

		//login as a administrator
		JButton btnNewButton_1_1 = new JButton("Administrator ?");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the login page for the customer
		        neww.login loginPage = new neww.login(); // Create an instance of Login class
		        loginPage.setVisible(true); // Show the login page
		        frame.dispose(); // Close the current page (Page frame)
			}
			
		});
		btnNewButton_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_1.setBackground(new Color(255, 224, 73));
		btnNewButton_1_1.setBounds(622, 626, 171, 22);
		btnNewButton_1_1.setBorder(null);
		frame.getContentPane().add(btnNewButton_1_1);
		

		
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Icons/icon.png"));
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(518, 166, 621, 160); // Adjust bounds as needed
		frame.getContentPane().add(imageLabel);
		
		ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/Icons/doo.jpg"));
		JLabel imageLabel1 = new JLabel(imageIcon1);
		imageLabel1.setBounds(-79, -32, 587, 821); // Adjust bounds as needed
		frame.getContentPane().add(imageLabel1);
		
		// Button to open Driver Login page
		JButton btnDriverManager = new JButton("Driver Manager?");
		btnDriverManager.setForeground(Color.BLACK);
		btnDriverManager.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDriverManager.setBorder(null);
		btnDriverManager.setBackground(new Color(255, 224, 73));
		btnDriverManager.setBounds(816, 626, 171, 22);
		btnDriverManager.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Open the Driver Login page
		        DriverLogin driverLoginPage = new DriverLogin(); // Create an instance of DriverLogin class
		        driverLoginPage.frame.setVisible(true); // Show the Driver Login page
		        frame.dispose(); // Close the current page (Page frame)
		    }
		});
		frame.getContentPane().add(btnDriverManager);

		
		JButton btnNewButton_1_1_1_1 = new JButton("|");
		btnNewButton_1_1_1_1.setForeground(Color.BLACK);
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_1_1_1.setBorder(null);
		btnNewButton_1_1_1_1.setBackground(new Color(255, 224, 73));
		btnNewButton_1_1_1_1.setBounds(769, 626, 61, 22);
		frame.getContentPane().add(btnNewButton_1_1_1_1);
		
		

	}
}
