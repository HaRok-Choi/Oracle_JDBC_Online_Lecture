package com.goodjobedu.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Test05 {
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
			
//			Student 테이블에 입력 받은 학생 이름, 연락처를 레코드로 추가해보자.
			Scanner sc = new Scanner(System.in);
			System.out.print("학생 이름: ");
			String name = sc.nextLine();
			System.out.print("학생 연락처: ");
			String tel = sc.nextLine();
			
			System.out.print("국어 점수: ");
			int kor = sc.nextInt();
			System.out.print("영어 점수: ");
			int eng = sc.nextInt();
			System.out.print("수학 점수: ");
			int math = sc.nextInt();
			double avg = (kor + eng + math) / 3.0;
			
			String sql = "INSERT INTO student VALUES(st_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, tel);
			ps.setInt(3, kor);
			ps.setInt(4, eng);
			ps.setInt(5, math);
			ps.setDouble(6, avg);
			
			ps.execute();
			System.out.println("학생추가완료");

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
