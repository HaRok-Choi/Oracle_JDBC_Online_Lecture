package com.goodjobedu.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test06 {
	public static void main(String[] args) {
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
//			커넥션 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
			String sql = "UPDATE student SET st_kr = 80";
			ps = con.prepareStatement(sql);
			
			int result = ps.executeUpdate();	
//			return이 정수형태다
//			몇 행이 적용(수정, 삭제, 추가)됐는지 궁금할 때
			System.out.println(result + "행에 적용 되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
