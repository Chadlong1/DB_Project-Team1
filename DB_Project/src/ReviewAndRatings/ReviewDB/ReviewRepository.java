package ReviewAndRatings.ReviewDB;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ReviewAndRatings.ReviewDB.*;
import busan.ConnectionProvider;

public class ReviewRepository {
	// 코멘트 테이블 생성 (코멘트, 평점) - 0208 (정창훈)
	public void createReviewTable() {
		String createReviewTable = "CREATE TABLE IF NOT EXISTS review" + "(no INT PRIMARY KEY AUTO_INCREMENT"
				+ ", review TEXT" + ", rating DOUBLE" + ", BPM_id INT" + ", FOREIGN KEY (BPM_id) REFERENCES BPM(id));";
		System.out.println(createReviewTable);

		try (Connection conn = ConnectionProvider.getConnection(); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(createReviewTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 코멘트, 평점 삽입 메소드 - 0208 (정창훈)
	public void insert(ReviewInput reviewInput) {
		String insert = "INSERT INTO comment (comment, rating, BPM_id)" + "VALUES (?, ?, ?);";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(insert);) {
			stmt.setString(1, reviewInput.getReview());
			stmt.setDouble(2, reviewInput.getRating());
//			stmt.setDouble(3, '선택한 음식점의 id');

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void joinBpmReview() {
//		String join = "SELECT * FROM busan.bpm AS A"
//				+ "LEFT JOIN busan.review AS B "
//				+ "ON A.id = B.bpm_id;";
//		List<ReviewInput> list = new ArrayList<>();
//		try (Connection conn = ConnectionProvider.getConnection();
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(join);) {
//			while (rs.next()) {
//				String review = rs.getString("review");
//				Double rating = rs.getDouble("rating");
//				List.add(new ReviewInput(review, rating));
//			}
//			
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
