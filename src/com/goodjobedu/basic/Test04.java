package com.goodjobedu.basic;

import java.sql.*;
import java.util.Scanner;

public class Test04 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("이름: ");
		String name = sc.nextLine();
		System.out.print("이메일: ");
		String email = sc.nextLine();
		
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "INSERT INTO account VALUES ('"+name+"', "
				+ "acc_seq.NEXTVAL, "
				+ "'"+email+"', "
				+ "1000, SYSDATE)";
//		입력받은 변수명 넣을 때
//		=> '"+변수명+"'
		
		
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
//			커넥션 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
//			쿼리문 준비
			ps = con.prepareStatement(sql);
			
//			실행
			ps.execute();
			System.out.println("레코드 추가 완료");
			
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
