package com.goodjobedu.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test01 {
	public static void main(String[] args) {
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@harokbook:1521:xe";
		
		
		try {
//			oracle.jdbc.driver. => package, OracleDriver => class
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Class.forName(클래스명) => 클래스를 찾겠다.
//			찾으면서 + 클래스로드(프로그램 시작전에 해당 클래스를 메모리에 로딩)
			
			Connection connection = DriverManager.getConnection(url, id, password);
//			Connection : 다리 역할의 객체(test01과 Oracle DB를 연결), 통로
			
			PreparedStatement ps = connection.prepareStatement("CREATE SEQUENCE student_seq NOCACHE"); // ; 금지
//			prepareStatement : 명령문을 준비하겠다. (쿼리문 준비 작업)
			
			ps.execute();	// 실행
			
			ps.close();
			connection.close();
			
			System.out.println("시퀀스 생성 완료!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	// OracleDriver 클래스가 없을때
		} catch (SQLException e) {
			e.printStackTrace();	// 커넥션(DB와 연동)하는 과정에서 예외 발생
		}
	}
}
