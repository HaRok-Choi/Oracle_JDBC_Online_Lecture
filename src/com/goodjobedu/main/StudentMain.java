package com.goodjobedu.main;

import java.sql.*;

import javax.swing.JOptionPane;

// 학생관리프로그램
public class StudentMain {
	
	public StudentMain() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "myJsp";
		String password = "jsppassword";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String menu = "1. 학생등록\n"
				+ "2. 학생수정\n"
				+ "3. 학생삭제\n"
				+ "4. 학생검색\n"
				+ "5. 이름순 조회\n"
				+ "6. 점수순 조회\n"
				+ "7. 종료하기\n";
		String select;	// 메뉴 선택용
		String name;	// 이름 입력용
		String tel;		// 연락처 입력용
		String sKr, sEn, sMa;	// 국영수 입력용(String)
		int kor, eng, math;			// 국영수 입력용(int)
		double avg;				// 평균 입력용
		String sql;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
			while (true) {
				select = JOptionPane.showInputDialog(menu);
				if (select.equals("1")) {
//					이름, 연락처, 국영수, 평균계산, sql실행, 결과보여줌
					sql = "INSERT INTO student VALUES (st_seq.NEXTVAL, ?, ? ,? ,?, ?, ?, SYSDATE)";
					name = JOptionPane.showInputDialog("새 학생 이름");
					tel = JOptionPane.showInputDialog("새 학생 연락처");
					sKr = JOptionPane.showInputDialog(name + "의 국어점수");
					sEn = JOptionPane.showInputDialog(name + "의 영어점수");
					sMa = JOptionPane.showInputDialog(name + "의 수학점수");
					kor = Integer.parseInt(sKr);
					eng = Integer.parseInt(sEn);
					math = Integer.parseInt(sMa);
					avg = (kor + eng + math) / 3.0;
					
					ps = con.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, tel);
					ps.setInt(3, kor);
					ps.setInt(4, eng);
					ps.setInt(5, math);
					ps.setDouble(6, avg);
					
					if (ps.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "등록 완료!");
					} else {
						JOptionPane.showMessageDialog(null, "등록 실패!");
					}
					ps.close();
					
				} else if (select.equals("2")) {
					String oldName;		// 수정전 학생 이름
					sql = "UPDATE student SET "
							+ "st_name = ?, st_tel = ?, st_kr = ?, st_eng = ?, st_ma = ?, "
							+ "st_avg = ?"
							+ "WHERE st_name = ?";
					oldName = JOptionPane.showInputDialog("수정할 학생의 이름");
					name = JOptionPane.showInputDialog(oldName + "의 새 이름");
					tel = JOptionPane.showInputDialog(oldName + "의 새 연락처");
					sKr = JOptionPane.showInputDialog(oldName + "의 새 국어점수");
					sEn = JOptionPane.showInputDialog(oldName + "의 새 영어점수");
					sMa = JOptionPane.showInputDialog(oldName + "의 새 수학점수");
					kor = Integer.parseInt(sKr);
					eng = Integer.parseInt(sEn);
					math = Integer.parseInt(sMa);
					
					ps = con.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, tel);
					ps.setInt(3, kor);
					ps.setInt(4, eng);
					ps.setInt(5, math);
					ps.setDouble(6, (kor+eng+math)/3.0);
					ps.setString(7, oldName);
					
					if (ps.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "학생수정 완료!");
					} else {
						JOptionPane.showMessageDialog(null, "수정할 학생이 없습니다.");
					}
					ps.close();
					
				} else if (select.equals("3")) {
					sql = "DELETE FROM student WHERE st_name = ?";
					name = JOptionPane.showInputDialog("삭제할 학생 이름");
					ps = con.prepareStatement(sql);
					ps.setString(1, name);
					
					if (ps.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "삭제 완료!");
					} else {
						JOptionPane.showMessageDialog(null, "삭제 실패!");
					}
					ps.close();
					
				} else if (select.equals("4")) {
					sql = "SELECT * FROM student WHERE st_name = ?";
					name = JOptionPane.showInputDialog("검색할 학생 이름");
					ps = con.prepareStatement(sql);
					ps.setString(1, name);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						name = rs.getString("st_name"); 
						tel = rs.getString("st_name");
						kor = rs.getInt("st_kr");
						eng = rs.getInt("st_eng");
						math = rs.getInt("st_ma");
						int no = rs.getInt("st_no");
						String regdate = rs.getString("st_regdate");
						String message = "이름 : " + name + "\n"
										+ "국/영/수" + kor + "," + eng + "," + math + "\n"
										+ "학번 : " + no + "\n"
										+ "등록일자 : " + regdate + "\n"
										+ "연락처 : " + tel;
						JOptionPane.showMessageDialog(null, message);
					} else {
						JOptionPane.showMessageDialog(null, "미등록 학생");
					}
					ps.close();

				} else if (select.equals("5")) {
					sql = "SELECT * FROM student ORDER BY st_name = ASC";
					String message = "----이름 순 조회----\n";
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					while (rs.next()) {
						name = rs.getString("st_name"); 
						tel = rs.getString("st_name");
						kor = rs.getInt("st_kr");
						eng = rs.getInt("st_eng");
						math = rs.getInt("st_ma");
						int no = rs.getInt("st_no");
						String regdate = rs.getString("st_regdate");
						message += "이름 : " + name + "\n"
								+ "국/영/수" + kor + "," + eng + "," + math + "\n"
								+ "학번 : " + no + "\n"
								+ "등록일자 : " + regdate + "\n"
								+ "연락처 : " + tel + "\n"
								+ "---------------------------------\n";
					}
					JOptionPane.showMessageDialog(null, message);
				} else if (select.equals("6")) {
					sql = "SELECT * FROM student ORDER BY st_avg = DESC";
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					int n = 1;
					String message = "";
					
					while (rs.next()) {
						name = rs.getString("st_name"); 
						tel = rs.getString("st_name");
						kor = rs.getInt("st_kr");
						eng = rs.getInt("st_eng");
						math = rs.getInt("st_ma");
						int no = rs.getInt("st_no");
						String regdate = rs.getString("st_regdate");
						message += "---------" + n + "---------" 
								+ "이름 : " + name + "\n"
								+ "국/영/수" + kor + "," + eng + "," + math + "\n"
								+ "학번 : " + no + "\n"
								+ "등록일자 : " + regdate + "\n"
								+ "연락처 : " + tel + "\n";
						n++;
					}
					JOptionPane.showMessageDialog(null, message);

				} else if (select.equals("7")) {
					JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
					break;
				} else {
					JOptionPane.showMessageDialog(null, "잘못 입력했습니다.\n 다시 입력하세요.");
				}
			}
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
	public static void main(String[] args) {
		
		new StudentMain();

	}
}
