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

	// 검색버튼 액션리스너에서 사용됨
	public static List<String> searchDB(String loca, String food, String rating) {
		List<String> list = new ArrayList<>();

		String searchDB = "";
		int numOfStar = countChar(rating, "★");
		int caseKey = 0;

		if (numOfStar == 0) {
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

		} else {
			if (loca.equals("부산 전체")) {
				if (food.equals("분류 없음")) {
					searchDB = "SELECT Title FROM busan.bpm AS A "
							+ "LEFT JOIN (SELECT round(avg(rating),2) AS averageRating, bpmId "
							+ "FROM busan.review WHERE depth = 0 GROUP BY bpmId) AS B " + "ON A.id = bpmId "
							+ "WHERE averageRating >= ?;";
					caseKey = 5;
				} else {
					searchDB = "SELECT Title FROM busan.bpm AS A "
							+ "LEFT JOIN (SELECT round(avg(rating),2) AS averageRating, bpmId "
							+ "FROM busan.review WHERE depth = 0 GROUP BY bpmId) AS B " + "ON A.id = bpmId"
							+ " WHERE type = ? AND averageRating >= ?;";
					caseKey = 6;
				}
			} else {
				if (food.equals("분류 없음")) {
					searchDB = "SELECT Title FROM busan.bpm AS A "
							+ "LEFT JOIN (SELECT round(avg(rating),2) AS averageRating, bpmId "
							+ "FROM busan.review WHERE depth = 0 GROUP BY bpmId) AS B " + "ON A.id = bpmId "
							+ "WHERE loca = ? AND averageRating >= ?;";
					caseKey = 7;
				} else {
					searchDB = "SELECT Title FROM busan.bpm AS A "
							+ "LEFT JOIN (SELECT round(avg(rating),2) AS averageRating, bpmId "
							+ "FROM busan.review WHERE depth = 0 GROUP BY bpmId) AS B " + "ON A.id = bpmId "
							+ "WHERE loca = ? AND type = ? AND averageRating >= ?;";
					caseKey = 8;
				}
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

		case 5:
			try (Connection conn = ConnectionProvider.getConnection();
					PreparedStatement stmt = conn.prepareStatement(searchDB);) {
				stmt.setInt(1, numOfStar);

				try (ResultSet rs = stmt.executeQuery();) {
					while (rs.next()) {
						list.add(rs.getString("title"));
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case 6:
			try (Connection conn = ConnectionProvider.getConnection();
					PreparedStatement stmt = conn.prepareStatement(searchDB);) {
				stmt.setString(1, food);
				stmt.setInt(2, numOfStar);

				try (ResultSet rs = stmt.executeQuery();) {
					while (rs.next()) {
						list.add(rs.getString("title"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case 7:
			try (Connection conn = ConnectionProvider.getConnection();
					PreparedStatement stmt = conn.prepareStatement(searchDB);) {
				stmt.setString(1, loca);
				stmt.setInt(2, numOfStar);

				try (ResultSet rs = stmt.executeQuery();) {
					while (rs.next()) {
						list.add(rs.getString("title"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case 8:
			try (Connection conn = ConnectionProvider.getConnection();
					PreparedStatement stmt = conn.prepareStatement(searchDB);) {
				stmt.setString(1, loca);
				stmt.setString(2, food);
				stmt.setInt(3, numOfStar);

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

	public static Restaurant searchRestaurant(String title) {
		Restaurant tempRest = null;
		String searchRestaurant = "SELECT * FROM BUSAN.BPM WHERE title = ?;";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(searchRestaurant);) {
			stmt.setString(1, title);

			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					tempRest = RestaurantList(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(1);
		}

		return tempRest;
	}

	public static int countChar(String str, String ch) {
		return str.length() - str.replace(String.valueOf(ch), "").length();
	}

	private static Restaurant RestaurantList(ResultSet rs) throws SQLException {
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

	// 상호명 입력시 테이블id 번호 리턴
	public static int searchIdNum(String title) {
		String getIdNum = "SELECT ID FROM BUSAN.BPM WHERE title = ?;";
		int idNum = 0;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(getIdNum);) {
			stmt.setString(1, title);
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					idNum = rs.getInt("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idNum;
	}

}
