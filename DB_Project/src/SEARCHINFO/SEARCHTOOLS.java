package SEARCHINFO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import busan.ConnectionProvider;
import busan.Restaurant;

public class SEARCHTOOLS {
	// 임시 파라미터 (추가 예정)
	public static void searchDB(String gugun) {
		String searchDB = "SELECT * FROM BUSAN.BPM WHERE gugun = ?";

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

	private Restaurant RestaurantList(ResultSet rs) throws SQLException {
		String title = rs.getString("title");
		String type = rs.getString("type");
		String menu = rs.getString("menu");
		String loca = rs.getString("loca");
		String addr = rs.getString("addr");
		String tel = rs.getString("tel");
		String time = rs.getString("time");
		String comment = rs.getString("comment");
		String img = rs.getString("img");
		String thumb = rs.getString("thumb");

		return new Restaurant(title, type, menu, loca, addr, tel, time, comment, img, thumb);
	}

	List<Restaurant> searchDBAll() {
		String searchDBAll = "SELECT & FROM BUSAN.BPM;";
		List<busan.Restaurant> list = new ArrayList<>();
		
		try (Connection conn = ConnectionProvider.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(searchDBAll);) {
			while(rs.next()) {
				list.add(RestaurantList(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
