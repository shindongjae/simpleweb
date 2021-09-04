package visitor.dao;

import visitor.dto.Visitor;

import java.sql.*;
import java.util.*;


public class visitorDao {
	private static String dbUrl="jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf8";
	
	
	
	private static String dbUser="myuser";
	private static String dbPasswd="4231";
	
	
	public List<Visitor> getvisitors(){
		List<Visitor> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
			String sql="SELECT * FROM guestbook";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Long id=rs.getLong("id");
				String name=rs.getString("name");
				String content=rs.getString("content");
				java.util.Date regdate = rs.getTimestamp("regdate");

	
				Visitor visitor=new Visitor(id,name,content,regdate);
				list.add(visitor);
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public void addvisitor(Visitor visitor) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
			String sql="INSERT INTO guestbook (id,name,content, regdate) VALUES (id,?, ?, now())";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setString(1,visitor.getName());
			ps.setString(2, visitor.getContent());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
}
