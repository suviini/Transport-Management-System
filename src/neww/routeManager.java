package neww;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class routeManager {
	
	public void addRoute (int routeNumber, String routeName, String startingLocation, String destination, String time) {
		String query = "Insert INTO routes(routeNumber, routeName, startingLocation, destination, time) VALUES (?,?,?,?,?)";
	
	
	try (Connection conn = databaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query)){
		
		pstmt.setInt(1, routeNumber);
		pstmt.setString(2, routeName);
		pstmt.setString(3, startingLocation);
		pstmt.setString(4, destination);
		pstmt.setString(5, time);
		
		pstmt.executeUpdate();
		System.out.println("Route added successfully");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}
}


//this for creating rows in routes
