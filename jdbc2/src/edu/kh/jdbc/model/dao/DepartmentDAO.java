package edu.kh.jdbc.model.dao;

// JDBCTemplate 클래스 내부의 static이 붙은 메서드를 모두 가져오기
// ㄴ> 가져온 메서드는 메서드 호출시 클래스명 생략 가능
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.model.dto.Department;

// DAO(Data Acess Object) : 데이터(DB,파일) 접근하는 객체
// -> SQL 수행, 결과 반환 
public class DepartmentDAO {

	/* JDBC 객체 참조 변수 선언 */
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;

	
	/** 부서 추가(삽입) 메서드
	 * @param dept (부서코드, 부서명, 지역코드)
	 * @return result (삽입된 결과 행의 개수)
	 * @throws SQLException 
	 */
	public int insertDepartment(Department dept) throws SQLException {

		int result = 0; // 결과 저장용 변수
		
		try {
			
			// 1. Connection 얻어오기
			
//			conn = JDBCTemaplate.getConnection(); // import static 사용 -> 클래스명 생략 가능
			conn = getConnection();
			
			// 2. SQL 작성
			String sql = "INSERT INTO DEPARTMENT4 VALUES(?, ?, ?)";
			
			// 3. PreparedStatment 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 에 알맞은 값 세팅
			pstmt.setString(1, dept.getDeptId());
			pstmt.setString(2, dept.getDeptTitle());
			pstmt.setString(3, dept.getLocationId());
			
			// 5. SQL 실행 + 결과 반환
			result = pstmt.executeUpdate(); // DML 수행
			
			// 6. 트랜잭션 제어 처리
			if(result > 0) commit(conn);
			else rollback(conn);
			
		} finally {
			
			// 7. 사용한 JDBC 객체 자원 반환
			close(pstmt);
			close(conn);
		}

		return result;
	}
	
	/** 부서 전체 조회
	 * @return departmentList (전체 부서)
	 * @throws SQLException 
	 */
	public List<Department> selectAll() throws SQLException{
		
		// 결과 저장용 변수 선언
		List<Department> deptList = null;
		
		try {
			// 1. Connection 얻어오기
			conn = getConnection();
			
			// 2. SQL 작성
			String sql = "SELECT * FROM DEPARTMENT4";
			
			// 3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? <-x 값 세팅 X
			
			// 4. SQL 실행후 결과 반환 받기
			rs = pstmt.executeQuery(); // excuteQuery() : SELECT 수행 - ResultSet 반환 / excuteUpdate() : DML 수행 - 결과 행의 수 반환
			
			// 5. 결과를 저장할 List 를 생성한 후 한 행씩 접근에 컬럼 값을 얻어와 List에 추가
			deptList = new ArrayList<Department>();
			
			while(rs.next()) {
				String deptId = rs.getString("DEPT_ID");
				String deptTitle = rs.getString("DEPT_TITLE");
				String locationId = rs.getString("LOCATION_ID");
				
				// Department 객체 생성
				Department dept = new Department(deptId, deptTitle, locationId);
				
				// deptList에 추가
				deptList.add(dept);
			}
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return deptList;
	}
}
