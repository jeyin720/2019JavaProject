package minesweeper;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.sql.PreparedStatement;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.sql.DataSource;




public class Rank {
	 Statement stmt = null;
	 Connection conn = null;
	 private ResultSet rs,rs2;
	 String table="rank";
	 Rank(){
		 try {

				String driver = "com.mysql.jdbc.Driver"; // 2. 데이터베이스에 연결하기 위한 정보 
				String url = "jdbc:mysql://localhost:3306/2019javaproject?useSSL=false&serverTimezone=UTC"; // 연결문자열, localhost - 127.0.0.1 
				String user = "root"; // 데이터베이스 ID 
				String pw = "mirim2";
				

				

				conn = DriverManager.getConnection(url,user,pw);

				try {
		            this.stmt = conn.createStatement();
		        } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }

				if (conn != null){}
				else{System.out.println("실패");}
				
				 
			} catch (SQLException sqex) {

				System.out.println("SQLException: " + sqex.getMessage());

				System.out.println("SQLState: " + sqex.getSQLState());

			}
			
	 }
	 public void insert(String name, int score) {
	        StringBuilder sb = new StringBuilder();
	        String sql = sb.append("insert into " + table + " values('")
	                .append(name + "',")
	                .append("" + score )
	                .append(");")
	                .toString();
	        try {
	            stmt.executeUpdate(sql);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	 public void delete(String name) {
	        StringBuilder sb = new StringBuilder();
	        String sql = sb.append("delete from " + table + " where name = '")
	                .append(name)
	                .append("';")
	                .toString();
	        try {
	            stmt.executeUpdate(sql);
	            System.out.println("삭제 성공");
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	 public void select(String a[][]) {
		 	int count=0;
	        StringBuilder sb = new StringBuilder();
	        String sql = sb.append("select * from " + table + " order by score desc")
	                .toString();
	        try {
	            ResultSet rs = stmt.executeQuery(sql);
	                   
	              while(rs.next()&&count<5){
	                     a[count][0]=rs.getString("name");
	                     a[count][1]=Integer.toString(rs.getInt("score"));
	                     count++;
	                }
	            
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

}

