package edu.kh.dept.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	/* JDBCTemplate : JDBC 관련 작업을 위한 코드를 제공하는 클래스
	 * 
	 * (JDBC 관련 공통 코드를 미리 작성해둘 예정)
	 * 
	 * - Connection 생성 (+ 자동 커밋 false)
	 * - commit / rollback 제어
	 * - JDBC 객체 자원 반환 구문 close()
	 * 
	 *  * 어디서든지 별도의 객체 생성없이 사용할 수 있게 모든 메서드는 public static으로 생성
	 * */
	
	// 필드
	private static Connection conn = null; 
//		ㄴ> 필드에 static? -> static 메서드가 참조 가능한 필드는 static 필드밖에 없기 때문 (해석순서)
	
	// 메서드
	
	/** 호출시 연결 정보가 담긴 새로운 Connection 객체를 생성해서 반환
	 * @return conn
	 */
	public static Connection getConnection() { // static <- 프로그램 시작시 생성
		
		try {
			// Connection이 없거나 닫혀있는 상태인 경우 -> 새 Connection 생성
			if(conn == null || conn.isClosed()) {

				Properties prop = new Properties();
//				 ㄴ> Properties : k:v 모두 String 인 Map, 파일로 입출력하기 편리한 기능을 제공
				
				
				/**/	/**/	/**/	/**/	/**/	/**/	/**/
				
				// 컴파일 된 driver.xml 파일의 위치를 얻어오는 코드
				String path = JDBCTemplate.class.getResource("/edu/kh/dept/sql/driver.xml").getPath(); 
				
				// 프로젝트 최상단 폴더에 존재하는 driver.xml 파일을 읽어와 Properties 객체에 저장(적재)
				prop.loadFromXML(new FileInputStream(path)); // <- 주소 대신 path값으로 변경
				
				/**/	/**/	/**/	/**/	/**/	/**/	/**/
				
				// prop에 저장된 값 얻어오기
				String driver = prop.getProperty("driver"); // oracle.jdbc.driver.OracleDriver
				String url = prop.getProperty("url"); // jdbc:oracle:thin:@localhost:1521:xe
				String user = prop.getProperty("user");
				String pw = prop.getProperty("pw");
				
				// 오라클 드라이버 클래스를 읽어와 메모리 적재
				Class.forName(driver);
				
				// 커넥션 객체 생성
				conn = DriverManager.getConnection(url, user, pw);
				
				// 자동 커밋 false 설정
				conn.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// -----------------
	
	/* 트랜잭션 제어 코드 */
	
	// commit
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// rollback
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------
	/* JDBC 객체 자원 반환 (close) + 오버로딩 */
	
	// Connection
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Statement
	public static void close(Statement stmt) { // Statement<- java.sql import
		// Statement, PreparedStatement 두 객체의 close 처리하는 메서드 (preparedStatement <- Statement의 상속, 한번에 처리 가능)
		
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ResultSet
	public static void close(ResultSet rs) {

		try {
			if(rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
