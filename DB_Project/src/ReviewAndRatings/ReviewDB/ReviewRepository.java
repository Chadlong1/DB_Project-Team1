package ReviewAndRatings.ReviewDB;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import busan.ConnectionProvider;

public class ReviewRepository {
	// 코멘트 테이블 생성 (코멘트, 평점) - 0208 (정창훈)
	public void createReviewTable() {
		String createReviewTable = "CREATE TABLE IF NOT EXISTS review" + "(no INT PRIMARY KEY AUTO_INCREMENT"
				+ ", review TEXT" + ", rating DOUBLE" + ", BPM_id INT" + ", FOREIGN KEY (BPM_id) REFERENCES BPM(id));";
		System.out.println("리뷰 테이블 생성");

		try (Connection conn = ConnectionProvider.getConnection(); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(createReviewTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 코멘트, 평점 삽입 메소드 - 0208 (정창훈)
	public static void insert(ReviewInput reviewInput, int idNum) {
		String insert = "INSERT INTO review (review, rating, BPM_id)" + "VALUES (?, ?, ?);";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(insert);) {
			stmt.setString(1, reviewInput.getReview());
			stmt.setDouble(2, reviewInput.getRating());
			stmt.setDouble(3, idNum);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// busan.bpm과 busan.review를 조인하여 리뷰와 평점만 리스트로 리턴
	public List<ReviewInput> joinBpmReview() {
		String join = "SELECT * FROM busan.bpm AS A " + "LEFT JOIN busan.review AS B " + "ON A.id = B.bpm_id;";
		List<ReviewInput> list = new ArrayList<>();
		try (Connection conn = ConnectionProvider.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(join);) {
			while (rs.next()) {
				list.add(returnReview(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//ResultSet 입력받으면 ReviewInput객체 반환
	private static ReviewInput returnReview(ResultSet rs) throws SQLException {
		String review = rs.getString("review");
		double rating = rs.getDouble("rating");
		
		return new ReviewInput(review, rating);
	}
	
	// 음식점의 id를 입력받으면 해당 id에 해당하는 ReviewInput 객체(후기, 평점)를 반환
	public static List<ReviewInput> viewReviewAtBpmId(int id) {
		List<ReviewInput> list = new ArrayList<>();
		String view = "SELECT * FROM busan.review" 
				+ " WHERE bpm_id = ?;";
		System.out.println(view);
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(view);) {
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					list.add(returnReview(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}



