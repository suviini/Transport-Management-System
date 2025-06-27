package neww;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteRoute {
		
		public static void deleteroute(int routeNumber) {
			String deleteQuery = "DELETE FROM routes WHERE routeNumber = ?";
			
			try (Connection conn = databaseConnection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(deleteQuery)){
				
				pstmt.setInt(1, routeNumber);
				
				int rowsAffected = pstmt.executeUpdate();
				System.out.println(rowsAffected + "row(s) deleted.");
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}


}
