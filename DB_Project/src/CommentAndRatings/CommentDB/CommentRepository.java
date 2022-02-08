package CommentAndRatings.CommentDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import CommentAndRatings.StandardDB.StandardDB;

public class CommentRepository {
	public void createCommentTable() {
		String createCommentTable = "CREATE TABLE IF NOT EXISTS comment "
				+ "(no INT PRIMARY KEY AUTO_INCREMENT"
				+ ", comment TEXT"
				+ ", rating DOUBLE);";
		System.out.println(createCommentTable);
		
		try (Connection conn = StandardDB.getConnection();
			Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(createCommentTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(CommentInput commentInput) {
		String insert = "INSERT INTO comment (comment, rating)"
				+ "VALUES (?, ?);";
		try (Connection conn = StandardDB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(insert);) {
				stmt.setString(1, commentInput.getComment());
				stmt.setDouble(2, commentInput.getRating());
			
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
}
