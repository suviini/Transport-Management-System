package neww;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class routeUpdate {

		public static void updateRoute(int routeNumber, String routeName, String startingLocation, String destination, String time){
			
			String updateQuery = "UPDATE routes SET routeName = ?, startingLocation = ?, destination =?, time = ? WHERE routeNumber =?";
			
			try(Connection conn = databaseConnection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(updateQuery)){
				
				pstmt.setString(1, routeName);
				pstmt.setString(2, startingLocation);
				pstmt.setString(3, destination);
				pstmt.setString(4, time);
				pstmt.setInt(5, routeNumber);
				
				int rowsAffected = pstmt.executeUpdate();
				System.out.println(rowsAffected +"row(s) updated");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}

}
