package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 프로젝트 우클릭 -> build path -> jar 추가

public class JDBXCExample1 {

	public static void main(String[] args) {
		
		/* JDBC (Java DataBase Connectivity) */
//		- Java 에서 DB에 접근할 수 있게 해주는 Java 제공 코드 (class, interface)
//			-> java.sql 패키지에 존재
			
		
		
		/* 1. JDBC 객체 참조 변수 선언 */
		
//		Connection : 특정 DB와의 연결 정보를 저장한 객체 (== dbeaver에서 DB 연결을 위해 주소, 계정, 비밀번호 저장한것)
		Connection conn = null;

//		Statement : SQL을 String 형태로 DB에 전달하고 결과를 받아오는 객체 선언
		Statement stmt = null;
		
//		ResultSet : SELECT 결과를 저장하는 객체 (조회 결과는 0행 이상)
		ResultSet  rs = null;

		
		try {
				
			/* 2. DriverManager 객체를 이용해서 Connection 생성 */
			/**/
			
			/* 2-1) Oracle JDBC Driver 객체를 메모리에 로드(적재) */
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName("클래스명") : 해당 클래스를 읽어 메모리에 적재
//												ㄴ> Java - Oracle 연결에 필요한 코드가 담긴 클래스
			
			/* 2-2) DB 연결 정보를 이용해서 Connection 객체 생성 */
			String type = "jdbc:oracle:thin:@"; // 드라이버 종류
			String host = "localhost"; // DB 서버 컴퓨터의 IP 주소
			String port = ":1521"; // DB 서버 컴퓨터의 DB 프로그램 연결 번호
			String dbName = ":XE"; // DB 이름
			String userName = "KH_LSM"; // 사용자 계정
			String pw = "KH1234"; // 계정 비밀번호
			
			// Drivermanager : Connection 만드는 객체
			conn = DriverManager.getConnection(type + host + port + dbName, userName, pw);
			
//			System.out.println(conn); // 중간 확인
			
			/* 3. SQL 작성 */
			
			// EMPLOYEE 테이블에서 사번, 이름, 부서코드, 직급코드, 급여, 입사일 조회해서 이클립스 콘솔에 출력
			
			String sql = "SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY, HIRE_DATE "
						+ "FROM EMPLOYEE"; // *** JDBC에서 SQL 작성시 ";" 작성XX <- 작성시 오류 "SQL 명령어가 올바르게 종료되지 않았습니다"
			
			/* 4. Statement 객체 생성 */
			stmt = conn.createStatement(); // Connection에 연결된 DB로 SQL 전달+결과 받아오는 Statment 객체 생성
			
			/* 5. Statement 객체를 이용해서 SQL 수행후 결과 반환 받기 */
			rs = stmt.executeQuery(sql); // 작성한 sql 객체를 query로 실행, result set 에 저장
			
			/* 6. 조회 결과가 담겨있는 ResultSet을 커서를 이용해 1행씩 접근하며 각행의 컬럼값 얻어오기 */
			while(rs.next()) {
//				ㄴ rs.next() : 커서를 한 행씩 이동시켜 해당 행이 있으면 true, 없으면 false
				
				
				// rs.get자료형(컬럼명 | 순서) : 현재 접근한 행의 컬럼값 얻어오기
				
				// [java]         [db]
				// String         CHAR, VARCHAR2
				// int, long      NUMBER (정수만 저장된 컬럼)
				// float, double  NUMBER (정수 + 실수)
				// java.sql.Date  DATE
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				int salary = rs.getInt("SALARY");
				Date hireDate = rs.getDate("HIRE_DATE");
				
				System.out.printf("사번 : %s / 이름 : %s / 부서코드 : %s / 직급코드 : %s / 급여 : %d / 입사일 : %s \n",
					empId, empName, deptCode, jobCode, salary, hireDate.toString()); // DATE 타입은 toStirng()으로 변환 필요
				
			}
			
			
		} catch (SQLException e) {
			// SQLException : JDBC 관련 예외의 최상위 부모
			e.printStackTrace();
			
		}catch(ClassNotFoundException e) {
			// ClassNotFoundException : 클래스가 존재하지 않으면 발생하는 예외
//				추가한 라이브러리가 없거나, 클래스 경로를 잘못 작성
			e.printStackTrace();
			
		} finally {
			
			/* 7. 사용 완료된 JDBC 객체 자원 반환 (만들어진 역순 반환 권장) */
			
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
