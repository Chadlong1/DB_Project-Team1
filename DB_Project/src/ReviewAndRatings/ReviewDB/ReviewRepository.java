package ReviewAndRatings.ReviewDB;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import busan.ConnectionProvider;

public class ReviewRepository {
	// 코멘트 테이블 생성 (코멘트, 평점) - 0208 (정창훈)
	public void createReviewTable() {
		String createReviewTable = "CREATE TABLE IF NOT EXISTS review" 
				+ "(reviewId INT PRIMARY KEY AUTO_INCREMENT"
				+ ", review TEXT" 
				+ ", rating DOUBLE" 
				+ ", bundleNum INT"
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
		String insert = "INSERT INTO review (review, rating, bundleNum, depth, bpmId)" + "VALUES (?, ?, ?, ?, ?);";
		System.out.println("리뷰 입력 완료");
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(insert);) {
			stmt.setString(1, reviewInput.getReview());
			stmt.setDouble(2, reviewInput.getRating());
			stmt.setInt(3, reviewInput.getBundleNum());
			stmt.setInt(4, reviewInput.getDepth());
			stmt.setDouble(5, reviewInput.getBpmId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	// busan.bpm과 busan.review를 조인하여 리뷰와 평점만 리스트로 리턴
//	public List<ReviewOutput> joinBpmReview() {
//		String join = "SELECT * FROM busan.bpm AS A " + "LEFT JOIN busan.review AS B " + "ON A.id = B.bpm_id;";
//		List<ReviewOutput> list = new ArrayList<>();
//		try (Connection conn = ConnectionProvider.getConnection();
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(join);) {
//			while (rs.next()) {
//				list.add(returnReview(rs));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	// ResultSet 입력받으면 depth = 0(원댓글)인 ReviewInput객체 반환
	private static ReviewOutput returnReview(ResultSet rs) throws SQLException {
		int reviewId = rs.getInt("reviewId");
		String review = rs.getString("review");
		double rating = rs.getDouble("rating");
		int bundleNum = rs.getInt("bundleNum");
		int depth = rs.getInt("depth");
		int bpmId = rs.getInt("bpmId");
		Timestamp writingTime = rs.getTimestamp("writingTime");
		if (depth == 0) {
			return new ReviewOutput(reviewId, review, rating, bundleNum, depth, bpmId, writingTime);
		} else {
			return null;
		}
	}

	// 음식점의 id를 입력받으면 해당 id의 depth = 0인 ReviewOutput 객체(후기, 평점)를 반환
	public static List<ReviewOutput> viewReviewAtBpmId(int bpmIdNum) {
		List<ReviewOutput> list = new ArrayList<>();
		String view = "SELECT * FROM busan.review" + " WHERE bpmId = ?;";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(view);) {
			stmt.setInt(1, bpmIdNum);

			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					if (returnReview(rs) != null) {
						list.add(returnReview(rs));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// ResultSet 입력받으면 depth = 1인(대댓글) ReviewInput객체 반환
	private static ReviewOutput returnReReview(ResultSet rs) throws SQLException {
		int reviewId = rs.getInt("reviewId");
		String review = rs.getString("review");
		double rating = rs.getDouble("rating");
		int bundleNum = rs.getInt("bundleNum");
		int depth = rs.getInt("depth");
		int bpmId = rs.getInt("bpmId");
		Timestamp writingTime = rs.getTimestamp("writingTime");
		if (depth == 1) {
			return new ReviewOutput(reviewId, review, rating, bundleNum, depth, bpmId, writingTime);
		} else {
			return null;
		}
	}

	// 음식점의 id를 입력받으면 해당 id의 depth = 1인(대댓글) ReviewInput 객체(후기, 평점)를 반환
	public static List<ReviewOutput> viewReReviewAtBpmId(int bpmIdNum) {
		List<ReviewOutput> list = new ArrayList<>();
		String view = "SELECT * FROM busan.review" + " WHERE bpmId = ?;";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(view);) {
			stmt.setInt(1, bpmIdNum);

			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					if (returnReReview(rs) != null) {
						list.add(returnReReview(rs));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// depth상관없이 전체 리뷰노출 ------------------------------------
	private static ReviewOutput returnReviewAll(ResultSet rs) throws SQLException {
		int reviewId = rs.getInt("reviewId");
		String review = rs.getString("review");
		double rating = rs.getDouble("rating");
		int bundleNum = rs.getInt("bundleNum");
		int depth = rs.getInt("depth");
		int bpmId = rs.getInt("bpmId");
		Timestamp writingTime = rs.getTimestamp("writingTime");
		
		return new ReviewOutput(reviewId, review, rating, bundleNum, depth, bpmId, writingTime);
	}
	// bpmIdNum를 입력받으면 bpmId, bundleNum순으로 정렬
	public static List<ReviewOutput> viewReviewAll(int bpmIdNum) {
		List<ReviewOutput> list = new ArrayList<>();
		String viewReviewAll = "SELECT * FROM BUSAN.review "
				+ "WHERE bpmId = ? "
				+ "ORDER BY bpmId, bundleNum;";
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(viewReviewAll);) {
			stmt.setInt(1, bpmIdNum);
			
			try(ResultSet rs = stmt.executeQuery();) {
				while(rs.next()) {
					list.add(returnReviewAll(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// ----------------------------------------------------------
	
	
	
	// 음식점 이름 옆에 나타낼 평점
	public static double viewRating(int id) {
		double ratingAverage = 0.0;
		String viewR = "select round(avg(rating),2)" 
				+ "from busan.review group by bpmId having bpmId =?";
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
	// bpmIdNum , selectedBundleNum 입력시 List<ReviewOuput> 반환
	public static List<ReviewOutput> getReplyWithSelectedBundleNum (int bpmIdNum, int selectedBundleNum) {
		List<ReviewOutput> list = new ArrayList<>();
		String getReplyWithSelectedBundleNum = "SELECT * FROM BUSAN.review "
						+ "WHERE bpmId = ? AND bundleNum = ?;";
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(getReplyWithSelectedBundleNum);) {
			stmt.setInt(1, bpmIdNum);
			stmt.setInt(2, selectedBundleNum);
			
			try(ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					list.add(returnReReview(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
