package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) {
		
		/* 1. JDBC 객체 참조 변수 선언 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		
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
			
			String sql = String.format("INSERT INTO DEPARTMENT4 VALUES('%S', '%S', '%S')", deptCode, deptTitle, locationId);
			
			/* 4. Statement 객체 생성 */
			stmt = conn.createStatement();
			
			/* 5. SQL 수행후 결과 반환 받기 (DML 수행 결과는 결과 행의 수!) */
			int result = stmt.executeUpdate(sql); // INSERT, UPDATE, DELETE 묶어서 UPDATE로 취급
			
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
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
