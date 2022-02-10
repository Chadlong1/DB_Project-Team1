package CommentAndRatings.CommentDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import busan.ConnectionProvider;

public class CommentRepository {
	// 코멘트 테이블 생성 (코멘트, 평점) - 0208 (정창훈)
	public void createCommentTable() {
		String createCommentTable = "CREATE TABLE IF NOT EXISTS comment "
				+ "(no INT PRIMARY KEY AUTO_INCREMENT"
				+ ", comment TEXT"
				+ ", rating DOUBLE);";
		System.out.println(createCommentTable);
		
		try (Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(createCommentTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 코멘트, 평점 삽입 메소드 - 0208 (정창훈) 
	public void insert(CommentInput commentInput) {
		String insert = "INSERT INTO comment (comment, rating)"
				+ "VALUES (?, ?);";
		try (Connection conn = ConnectionProvider.getConnection();
			PreparedStatement stmt = conn.prepareStatement(insert);) {
				stmt.setString(1, commentInput.getComment());
				stmt.setDouble(2, commentInput.getRating());
			
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
}
