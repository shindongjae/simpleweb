package carddao;

import cardtao.*;
import java.util.*;
import java.sql.*;

public class CardDao {
	private static String dbUrl="jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser="myuser";
	private static String dbPasswd="4231";
	
	public List<Card> searchBusinessCard(String keyword){
		List<Card> cardList=new ArrayList<>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			String sql="SELECT * FROM card WHERE name=?";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, keyword);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString("name");
				String phone=rs.getString("phone");
				String company=rs.getString("company");
				Card card=new Card(name, phone, company);
				cardList.add(card);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps!=null) try {ps.close();}catch(Exception ex) {}
			if(conn!=null) try {conn.close();}catch(Exception ex) {}
			if(rs!=null) try {rs.close();}catch(Exception ex) {}
		}
		return cardList;
	}
	
	public int addCard(Card card) {
		int insertCount=0;
		
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
			String sql="INSERT INTO card (name, phone, company) VALUES (?,?,?)";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1,card.getName());
			ps.setString(2,card.getPhone());
			ps.setString(3, card.getCompanyName());
			
			insertCount=ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
			if(ps!=null) 
				try {ps.close();}catch(SQLException ex) {}
		}
		
		return insertCount;
	}
}
