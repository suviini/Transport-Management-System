package publicTransport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class Signup {

    private JFrame frame;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Signup window = new Signup();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Signup() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(254, 222, 73));
        frame.setBounds(100, 100, 1098, 633);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblTitle = new JLabel("Create an account");
        lblTitle.setBounds(89, 141, 386, 44);
        frame.getContentPane().add(lblTitle);
        lblTitle.setBackground(new Color(0, 0, 0));
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(52, 218, 100, 20);
        frame.getContentPane().add(lblName);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        nameField = new JTextField();
        nameField.setBackground(new Color(254, 222, 73));
        nameField.setBounds(202, 218, 200, 25);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);
        
        JLabel lblEmailAddress = new JLabel("Email Address");
        lblEmailAddress.setBounds(52, 266, 127, 20);
        frame.getContentPane().add(lblEmailAddress);
        lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        emailField = new JTextField();
        emailField.setBackground(new Color(254, 222, 73));
        emailField.setBounds(202, 266, 200, 25);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(52, 308, 100, 20);
        frame.getContentPane().add(lblUsername);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        usernameField = new JTextField();
        usernameField.setBackground(new Color(254, 222, 73));
        usernameField.setBounds(202, 308, 200, 25);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(52, 349, 100, 20);
        frame.getContentPane().add(lblPassword);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(254, 222, 73));
        passwordField.setBounds(202, 349, 200, 25);
        frame.getContentPane().add(passwordField);
        
        JButton btnSubmit = new JButton("Sign Up");
        btnSubmit.setForeground(new Color(255, 255, 255));
        btnSubmit.setBackground(new Color(0, 0, 0));
        btnSubmit.setBounds(169, 425, 100, 30);
        frame.getContentPane().add(btnSubmit);
        btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        JLabel lblAlreadyHaveAn = new JLabel("Already have an account?");
        lblAlreadyHaveAn.setBounds(52, 487, 187, 20);
        frame.getContentPane().add(lblAlreadyHaveAn);
        lblAlreadyHaveAn.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        JButton btnLogIn = new JButton("Login");
        btnLogIn.setBackground(new Color(0, 0, 0));
        btnLogIn.setForeground(new Color(255, 255, 255));
        btnLogIn.setBounds(251, 482, 100, 30);
        frame.getContentPane().add(btnLogIn);
        btnLogIn.addActionListener(e -> {
            openLoginFrame();
            frame.dispose();
        });
        btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(440, 0, 660, 596);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(21, 50, 599, 501);
        panel.add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon(Signup.class.getResource("/Icons/uSign.jpg")));
        
        btnSubmit.addActionListener(e -> {
            String password = new String(passwordField.getPassword()).trim();
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String name = nameField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields", "Validation Error", JOptionPane.WARNING_MESSAGE);
            } else {
                // Call method to register user
                if (registerUser(name, email, username, password)) {
                    JOptionPane.showMessageDialog(frame, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    openLoginFrame(); // Open the login frame
                    frame.dispose(); // Close the signup form after success
                } else {
                    JOptionPane.showMessageDialog(frame, "Signup failed. Username or email might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean registerUser(String name, String email, String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();

            // Check if username or email already exists
            String checkSql = "SELECT * FROM users WHERE username = ? OR email = ?";
            stmt = conn.prepareStatement(checkSql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false; // Username or email already exists
            }

            // Insert new user into the database
            String sql = "INSERT INTO users (name, email, username, password) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Opens the login frame after successful signup.
     */
    private void openLoginFrame() {
        Login loginWindow = new Login(); 
        loginWindow.setVisible(true); 
    }
}