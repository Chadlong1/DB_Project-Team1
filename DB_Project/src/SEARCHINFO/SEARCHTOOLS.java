package SEARCHINFO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busan.ConnectionProvider;

public class SEARCHTOOLS {
	// 임시 파라미터 (추가 예정)
	public static void searchDB(String gugun) {
		String searchDB = "SELECT * FROM restaurantDB WHERE gugun = ?";
		
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(searchDB);) {
			stmt.setString(1, gugun);
			
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
