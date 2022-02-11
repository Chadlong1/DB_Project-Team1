package SEARCHINFO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import GUI.GUI2;
import busan.ConnectionProvider;
import busan.Restaurant;

public class SEARCHTOOLS {
	// 임시 파라미터 (추가 예정)
	public static List<String> searchDB(String loca, String food) {
		GUI2 frame = new GUI2();
		List<String> list = new ArrayList<>();
		String searchDB = "";
		int caseKey = 0;
		if (loca.equals("부산 전체")) {
			if (food.equals("분류 없음")) {
				searchDB = "SELECT Title FROM BUSAN.BPM;";
				caseKey = 1;
			} else {
				searchDB = "SELECT Title FROM BUSAN.BPM WHERE type = ?;";
				caseKey = 2;
			}
		} else {
			if (food.equals("분류 없음")) {
				searchDB = "SELECT Title FROM BUSAN.BPM WHERE loca = ?;";
				caseKey = 3;
			} else {
				searchDB = "SELECT Title FROM BUSAN.BPM WHERE loca = ? and type = ?;";
				caseKey = 4;
			}

		}

		switch (caseKey) {
		case 1:
			try (Connection conn = ConnectionProvider.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(searchDB);) {

				while (rs.next()) {
					list.add(rs.getString("title"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try (Connection conn = ConnectionProvider.getConnection();
					PreparedStatement stmt = conn.prepareStatement(searchDB);) {
				stmt.setString(1, food);

				try (ResultSet rs = stmt.executeQuery();) {
					while (rs.next()) {
						list.add(rs.getString("title"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			try (Connection conn = ConnectionProvider.getConnection();
					PreparedStatement stmt = conn.prepareStatement(searchDB);) {
				stmt.setString(1, loca);

				try (ResultSet rs = stmt.executeQuery();) {
					while (rs.next()) {
						list.add(rs.getString("title"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			try (Connection conn = ConnectionProvider.getConnection();
					PreparedStatement stmt = conn.prepareStatement(searchDB);) {
				stmt.setString(1, loca);
				stmt.setString(2, food);

				try (ResultSet rs = stmt.executeQuery();) {
					while (rs.next()) {
						list.add(rs.getString("title"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

		return list;
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
			while (rs.next()) {
				list.add(RestaurantList(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> searchLoca() {
		String searchDB = "Select distinct loca from busan.bpm;";
		List<String> list = new ArrayList<>();

		try (Connection conn = ConnectionProvider.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(searchDB);) {

			while (rs.next()) {
				list.add(rs.getString("loca"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
