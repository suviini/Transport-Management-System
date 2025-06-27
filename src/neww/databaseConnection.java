package neww;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
	
	public static Connection getConnection() {
			
			Connection conn = null; 
			
			String url = "jdbc:mysql://localhost:3306/transport_db";
			String username = "root";
			String password = "";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				conn = DriverManager.getConnection(url, username, password);
				System.out.println("Database connection successful");				
			} catch (SQLException e) {
				System.out.println("Failed to connect to the database");
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				System.out.println("JDBC Driver not found");
				e.printStackTrace();
			}
			
			return conn;
	
		}
	}

