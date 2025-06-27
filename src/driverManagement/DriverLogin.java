package driverManagement;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import publicTransport.DatabaseConnection;
import publicTransport.User;
import transport.Admin;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class DriverLogin {

    public JFrame frame;
    private JPasswordField passwordField;
    private JTextField usernameField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DriverLogin window = new DriverLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DriverLogin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 1113, 776);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 528, 858);
        panel.setBackground(new Color(254, 222, 73));
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblDriverLogin = new JLabel("Welcome as Driver Manager");
        lblDriverLogin.setBounds(34, 197, 484, 73);
        panel.add(lblDriverLogin);
        lblDriverLogin.setForeground(new Color(0, 0, 0));
        lblDriverLogin.setFont(new Font("Georgia", Font.BOLD, 32));

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(44, 394, 100, 26);
        panel.add(lblUsername);
        lblUsername.setForeground(new Color(0, 0, 0));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));

        usernameField = new JTextField();
        usernameField.setBackground(new Color(254, 222, 73));
        usernameField.setBounds(175, 395, 194, 30);
        panel.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(44, 481, 100, 20);
        panel.add(lblPassword);
        lblPassword.setForeground(new Color(0, 0, 0));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(254, 222, 73));
        passwordField.setBounds(175, 479, 194, 30);
        panel.add(passwordField);

        // Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBackground(new Color(0, 0, 0));
        btnLogin.setBounds(136, 564, 116, 39);
        panel.add(btnLogin);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        JLabel lblLoginToYour = new JLabel("Login to your account to continue");
        lblLoginToYour.setForeground(Color.BLACK);
        lblLoginToYour.setFont(new Font("Georgia", Font.PLAIN, 20));
        lblLoginToYour.setBounds(85, 274, 443, 26);
        panel.add(lblLoginToYour);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(540, 10, 549, 700);
        lblNewLabel.setIcon(new ImageIcon(DriverLogin.class.getResource("/Icons/driverlogin.jpg")));
        frame.getContentPane().add(lblNewLabel);

        // Action listener for Login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Check for empty fields
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter both username and password", "Validation Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    User driver = validateLogin(username, password);
                    if (driver != null) {
                        JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + driver.getName() + ".", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Open HomePage frame
                        Admin adminPage = new Admin();  // Ensure Admin class exists and is imported
                        adminPage.setVisible(true);
                        frame.dispose(); // Close the login frame
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }
        });
    }

    private User validateLogin(final String username, final String password) {
        User driver = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT driver_id, name, username FROM drivers WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int driverId = rs.getInt("driver_id");
                String name = rs.getString("name");
                String uname = rs.getString("username");
                driver = new User(driverId, name, uname);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
