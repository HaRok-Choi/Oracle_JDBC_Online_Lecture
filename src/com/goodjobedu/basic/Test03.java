package com.goodjobedu.basic;

import java.sql.*;

public class Test03 {
	public static void main(String[] args) {
		
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql1 = "INSERT INTO account "
				+ "VALUES('최하록',"
				+ "acc_seq.NEXTVAL,"
				+ "'gkfhrwkd23@naver.com', "
				+ "20000, SYSDATE)";
		String sql2 = "INSERT INTO account "
				+ "VALUES('고길동',"
				+ "acc_seq.NEXTVAL,"
				+ "'zxcv@naver.com', "
				+ "5000, SYSDATE)";
		String sql3 = "INSERT INTO account "
				+ "VALUES('피카츄',"
				+ "acc_seq.NEXTVAL,"
				+ "'asdf@naver.com', "
				+ "8000, SYSDATE)";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, id, password);
			
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.execute();	// COMMIT;
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.execute();	// COMMIT;
			preparedStatement = connection.prepareStatement(sql3);
			preparedStatement.execute();	// COMMIT;
			
			System.out.println("레코드 추가완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}
