package com.goodjobedu.basic;

import java.sql.*;

public class Test08 {
	public static void main(String[] args) {

		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		SELECT를 써서 조회하기 때문에 ResultSet을 써서 테이블을 한줄씩 읽어(받아)온다.

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
			sql = "SELECT st_name, st_kr, st_no, st_avg FROM student WHERE st_avg >= 80";
//			SELECT는 조회하는 것이므로 결과값이 ResultSet 형태로 돌아와야 한다.
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			String name;
			int kr;
			int no;
			double avg;
			
			System.out.println("====1번문제=====");
			while (rs.next()) {
				name = rs.getString("st_name");
				kr = rs.getInt("st_kr");
				no = rs.getInt("st_no");
				avg = rs.getDouble("st_avg");
				
				System.out.println("이름: " + name);
				System.out.println("국어 점수: " + kr);
				System.out.println("학번: " + no);
				System.out.println("평균점수: " + avg + "점");
				System.out.println();
			}
			rs.close();
			ps.close();
			
			System.out.println("====2번문제====");
			sql = "SELECT st_name, st_no, st_tel FROM student WHERE st_name LIKE '최%' ORDER BY st_name ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("이름: " + rs.getString("st_name"));
				System.out.println("학번: " + rs.getInt("st_no"));
				System.out.println("번호: " + rs.getString("st_tel"));
				System.out.println();
			}
			rs.close();
			ps.close();
			
			System.out.println("====3번문제====");
			sql = "DELETE FROM student WHERE st_regdate = (SELECT MAX(st_regdate) FROM student) ";
			ps = con.prepareStatement(sql);
//			System.out.println(ps.executeUpdate() + "개 레코드가 삭제됨.");
			ps.close();
			
			System.out.println("====4번문제====");
			sql = "SELECT st_name, st_avg FROM student WHERE st_avg = (SELECT MAX(st_avg) FROM student)";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			System.out.println("1등의 이름 : " + rs.getString("st_name"));
			System.out.println("1등의 평균점수 : " + rs.getDouble("st_avg"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && ps != null && con != null) {
					rs.close();
					ps.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
