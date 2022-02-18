package busan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionProvider {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL
								= "jdbc:mysql://localhost:3306/?useUnicode=true&characterEncoding=utf8";
	private static final String ID = "root";
	private static final String PASSWORD = "root";
	
	static {
		try {
			Class.forName(DRIVER);
			System.out.println("드라이버 로드");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, ID, PASSWORD);
		Statement stmt = conn.createStatement();
		String createDatabase = "CREATE DATABASE if not exists busan";
		String useDatabase = "USE busan";
		stmt.executeUpdate(createDatabase); 
		stmt.executeUpdate(useDatabase);
		
		stmt.close(); 
		
		return conn;
	}
	
}
