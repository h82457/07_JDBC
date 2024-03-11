package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample6 {
	public static void main(String[] args) {
		
		/* 1. JDBC 객체 참조 변수 선언 */
		Connection conn = null;
		
		PreparedStatement pstmt = null; // PreparedStatement : 외부 변수 값을 SQL에 받아올 준비가 되어있는 Statement
//			ㄴ 성능, 속도면에서 우위를 가짐, ? (placeholder) : 변수/값을 위치 시킬 자리 지정
		
		try {
			
			/* 2. DriverManager 객체를 이용, Connection 생성 */ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; 
			String host = "localhost";
			String port = ":1521";
			String dbName = ":XE";
			String userName = "KH_LSM";
			String pw = "KH1234";
			
			conn = DriverManager.getConnection(type + host + port + dbName, userName, pw);
			
			/* 만들어진 커넥션으로 SQL 수행시 자동 커밋 비할성화! (기본 활성화상태) */
			conn.setAutoCommit(false);
			
			/* 3. SQL 작성 */
			Scanner sc = new Scanner(System.in);
			
			System.out.print("부서코드 입력 : ");
			String deptCode = sc.next();
			
			System.out.print("부서명 입력 : ");
			String deptTitle = sc.next();
			
			System.out.print("지역코드 입력 : ");
			String locationId = sc.next();
			
			// ? (placeholder) 추가된 SQL 작성
			String sql = "INSERT INTO DEPARTMENT4 VALUES(?, ?, ?)";
			
			/* 4. Statement 객체 생성 */
			pstmt = conn.prepareStatement(sql);
			
			/* sql을 객체 생성시 전달 + ?에 값을 대입할 준비 */ 
			
			/* 5. PreparedStatement 객체의 ? 부분에 알맞은 값 대입 */ // 해당 구문으로 세팅되는 값은 자동으로 양쪽에 홑따옴표가 추가
			pstmt.setString(1, deptCode);
			pstmt.setString(2, deptTitle);
			pstmt.setString(3, locationId);
			
			int result = pstmt.executeUpdate();
			// ㄴ> PreparedStatement  SQL 수행 시
//			   메서드 매개변수로 SQL을 전달하면 "안"된다!!!!!!
			
			/* 6. 수행 결과에 따라 트랜잭션 제어 처리 */
			if(result > 0) {
				System.out.println("삽입 성공");
				conn.commit();
			} else {
				System.out.println("삽입 실패");
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/* 7. JDBC 자원 반환 */
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
