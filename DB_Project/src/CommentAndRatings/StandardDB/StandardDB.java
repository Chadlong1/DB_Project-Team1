package CommentAndRatings.StandardDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StandardDB {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/my_db";
	private static final String ID = "root";
	private static final String PASSWORD = "root";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, ID, PASSWORD);
	}
	
		// 상호명 / 주소/ 전화번호/ 영업시간/ 대표메뉴
	static {
		String createRestaurantDB = "CREATE TABLE IF NOT EXISTS restaurantDB "
				+ "(no INT PRIMARY KEY AUTO_INCREMENT"
				+ ", nameOfTheStore VARCHAR(30) NOT NULL"
				+ ", address VARCHAR(100) NOT NULL"
				+ ", phoneNumber VARCHAR(13) NOT NULL"
				+ ", businessHour VARCHAR(30) NOT NULL"
				+ ", mainMenu VARCHAR(100) NOT NULL);";
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(createRestaurantDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("resaurantDB 생성");
	}
	
}
