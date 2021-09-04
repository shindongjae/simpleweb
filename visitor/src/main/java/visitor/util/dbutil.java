package visitor.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbutil {
	public static Connection getConnection() {
		return getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Seoul&useSSL=false","myuser","4231");
	}
	
	public static Connection getConnection(String dbURL, String dbId, String dbPasswd) {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL, dbId, dbPasswd);
			return conn;
		}catch(Exception ex) {
			throw new RuntimeException("Connection Error");
		}
		
	}
}
