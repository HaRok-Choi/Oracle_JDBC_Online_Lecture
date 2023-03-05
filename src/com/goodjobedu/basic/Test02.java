package com.goodjobedu.basic;

import java.sql.*;

public class Test02 {
	public static void main(String[] args) {
		
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "CREATE TABLE account( "
				+ "name VARCHAR2(20), "
				+ "no NUMBER, "
				+ "email VARCHAR2(20), "
				+ "point NUMBER, "
				+ "regdate DATE)";
		Connection connection = null;
		PreparedStatement ps = null;
//		Class.forName에서 오류가 발생할 수 있으므로 초기화를 해줘야 한다.
		
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 connection = DriverManager.getConnection(url, id, password);
			 
			 ps = connection.prepareStatement(sql);
			 ps.execute();
			 System.out.println("Account 테이블 생성 완료");
//			 위의 ps에 담긴 쿼리문을 보내고 아래에서 한번더 보낸다.
			 
			 ps = connection.prepareStatement("CREATE SEQUENCE acc_seq NOCACHE");
			 ps.execute();
			 System.out.println("acc_seq 시퀀스 생성 완료");
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
//				위의 문장 어디에서라도 오류가 발생하면 제대로 된 예외처리가
//				안되기 때문에 finally에서 한번더 예외처리를 한다.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
