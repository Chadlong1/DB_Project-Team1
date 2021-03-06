package busan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.simple.JSONObject;

public class Repository {
	public void createTable() {
		String createTable = "create table if not exists BPM ("
				+ "	id INT PRIMARY KEY AUTO_INCREMENT"
				+ "	, title VARCHAR(300) NOT NULL"
				+ " , type VARCHAR (10) NOT NULL"
				+ "	, menu VARCHAR(200) NOT NULL"
				+ "	, loca VARCHAR(20) NOT NULL"
				+ "	, addr VARCHAR(200) NOT NULL"
				+ "	, tel VARCHAR(200) NOT NULL"
				+ "	, time VARCHAR(500) NOT NULL"
				+ "	, comment VARCHAR(1000) NOT NULL"
				+ "	, img VARCHAR(500) NOT NULL"
				+ "	, thumb VARCHAR(500) NOT NULL"
				+ ");";

		try (Connection conn = ConnectionProvider.getConnection(); 
				Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(createTable);
			System.out.println("테이블 생성");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int[] insertAll(List<Restaurant> apis) {
		String insertTable = "INSERT INTO BPM (title, type, menu, loca,"
				+ "addr, tel, time, comment, img, thumb) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		int[] result = null;
		// 연결 만들기
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			// 트랜잭션 시작
			conn.setAutoCommit(false);
			// 여러개의 음식점 row Insert
			try (PreparedStatement stmt = conn.prepareStatement(insertTable)) {
				// batch
				for (Restaurant res : apis) {					
					stmt.setString(1, res.getTitle());
					stmt.setString(2, res.getType());
					stmt.setString(3, res.getMenu());
					stmt.setString(4, res.getLoca());
					stmt.setString(5, res.getAddr());
					stmt.setString(6, res.getTel());
					stmt.setString(7, res.getTime());
					stmt.setString(8, res.getComment());
					stmt.setString(9, res.getImg());
					stmt.setString(10, res.getThumb());
					stmt.addBatch();
				}
				result = stmt.executeBatch();
				// 완료시 commit
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			// 실패시 rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 연결 해제
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public void dropTable() {
		String dropTable = "drop table if exists BPM ;";
		try (Connection conn = ConnectionProvider.getConnection(); 
				Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(dropTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// DB중복 insert방지 판별
	public boolean discriminant() {
		String query = "SELECT id FROM BUSAN.BPM WHERE id = 1;";
		boolean b = true;
		try(Connection conn = ConnectionProvider.getConnection(); 
				Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int idNum = rs.getInt("id");
				if (idNum == 1) {
					b = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
