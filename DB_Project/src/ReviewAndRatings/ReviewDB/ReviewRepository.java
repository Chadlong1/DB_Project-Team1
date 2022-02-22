package ReviewAndRatings.ReviewDB;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import busan.ConnectionProvider;

public class ReviewRepository {
	// 코멘트 테이블 생성 (코멘트, 평점) - 0208 (정창훈)
	public void createReviewTable() {
		String createReviewTable = "CREATE TABLE IF NOT EXISTS review" + "(reviewId INT PRIMARY KEY AUTO_INCREMENT"
				+ ", review TEXT" 
				+ ", rating DOUBLE" 
				+ ", depth INT" 
				+ ", bpmId INT"
				+ ", writingTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
				+ ", FOREIGN KEY (bpmId) REFERENCES BPM(id));";
		System.out.println("리뷰 테이블 생성");
		
		try (Connection conn = ConnectionProvider.getConnection(); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(createReviewTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 코멘트, 평점 삽입 메소드 - 0208 (정창훈)
	public static void insert(ReviewInput reviewInput) {
		String insert = "INSERT INTO review (review, rating, depth, bpmId)" + "VALUES (?, ?, ?, ?);";
		System.out.println(insert);
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(insert);) {
			stmt.setString(1, reviewInput.getReview());
			stmt.setDouble(2, reviewInput.getRating());
			stmt.setInt(3, reviewInput.getDepth());
			stmt.setDouble(4, reviewInput.getBpmId());

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

	// ResultSet 입력받으면 ReviewInput객체 반환
	private static ReviewInput returnReview(ResultSet rs) throws SQLException {
		String review = rs.getString("review");
		double rating = rs.getDouble("rating");
		int depth = rs.getInt("depth");
		int bpmId = rs.getInt("bpmId");

		return new ReviewInput(review, rating, depth, bpmId);
	}

	// 음식점의 id를 입력받으면 해당 id에 해당하는 ReviewInput 객체(후기, 평점)를 반환
	public static List<ReviewInput> viewReviewAtBpmId(int bpmId) {
		List<ReviewInput> list = new ArrayList<>();
		String view = "SELECT * FROM busan.review" + " WHERE bpmId = ?;";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(view);) {
			stmt.setInt(1, bpmId);

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

	// 음식점 이름 옆에 나타낼 평점
	public static double viewRating(int id) {
		double ratingAverage = 0.0;
		String viewR = "select round(avg(rating),2)" + "from busan.review group by bpmId having bpmId =?";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(viewR);) {
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					ratingAverage = rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ratingAverage;
	}

	// BUSAN.review 테이블의 no 번호를 입력시 작성 일자 리턴
	public static LocalDateTime getTimeStamp(int reviewNo) {
		String getTimeStamp = "SELECT writingTime FROM BUSAN.review WHERE no = ?;";
		LocalDateTime timeStamp = null;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(getTimeStamp);) {
			stmt.setInt(1, reviewNo);

			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					timeStamp = rs.getTimestamp("writingTime").toLocalDateTime();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeStamp;
	}
	
	// 가장 최근에 삽입된 row의 id반환
	public static int getLastSelectReviewId() {
		String getLastSelectReviewId = "SELECT LAST_INSERT_ID;";
		int id = 0;
		try(Connection conn = ConnectionProvider.getConnection();
				Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(getLastSelectReviewId);
			while(rs.next()) {
				id = rs.getInt("reviewId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
}
