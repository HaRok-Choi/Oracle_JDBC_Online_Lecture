package com.goodjobedu.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test07 {
	public static void main(String[] args) {

		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);

			String sql = "SELECT st_name, st_avg FROM student";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			boolean result = rs.next(); // 레코드가 1개라도 있으면 true, 없으면 false 
//			rs.next() : 테이블에서 첫번째 줄을 읽는다.
//			레코드를 확인하기 위해서는 최소 1회 next() 실행해야 함
//			실행하지 않은 초기 커서는 아무것도 가리키지 않기 때문이다.

			while (rs.next()) {
				String name = rs.getString("st_name"); 
				// 현재 위치 레코드의 st_name 항목의 값을 String형태로 갖다줘
				double avg = rs.getDouble("st_avg");
				// 현재 위치 레코드의 st_avg 항목의 값을 double형태로 갖다줘
				// String : 항목이름, int : 몇 번째 항목(<- 항목 이름을 모를 때)
				System.out.println(name + " / " + avg + "점");
			}
			
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
