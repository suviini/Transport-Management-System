package driverManagement;

import java.sql.Connection;//Import the connection class from the java.sql package
import java.sql.SQLException;//Import the SQLException class for handling SQL exception
import java.sql.DriverManager;//Import the DriverManager class for managing database connections

public class DataBaseConn {//Define a public class named DataBaseConn
	

	public static void main(String[] args) {//Main method the entry point of the program
		@SuppressWarnings("unused")
		String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";//Define the JDBC name for MySQL
		String DB_URL="jdbc:mysql://localhost:3306/transport_db"; //Define the database url
		String USER="root";//Define the user name for connecting to the database 
		String Password="";//Define the password for the database user
		
		Connection conn=null;//Initialize the connection object to null
		
		try {
			//Load the JDBC driver
			Class.forName("JDBC_DRIVER");//Attempt to load the JDBC driver class
			//Establish the connection to the database
			conn=DriverManager.getConnection(DB_URL,USER,Password);//get the connection using the specified URL, user name,and password
			System.out.println("Connected to the database!");//Print success message if connection is established
		}catch(SQLException e) {//Catch SQL Exception
			System.out.println("Failed to connect to the database");//Print failure message
			e.printStackTrace();//Print the stack trace for debugging 
		}catch(ClassNotFoundException e) {//catch classNotFound exception
			System.out.println("MySQL JDBC Driver not found");//Print the message if the driver class not found
			e.printStackTrace();//Print the stack trace for debugging
		}finally {//Block the executes regardless of whether an exception was thrown
			try {
				if(conn!=null) {//Check if the connection is not null
					conn.close();//close the connection to free up resources
				}
			}catch(SQLException e) {//catch any SQL exceptions that occur during connection closure
				e.printStackTrace();//Print the stack trace for debugging
			}
		}

	}

}
