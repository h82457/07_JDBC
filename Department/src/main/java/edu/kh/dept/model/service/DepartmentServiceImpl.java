package edu.kh.dept.model.service;

// JDBCTemplate 클래스의 static 메서드 모두 가져오기
import static edu.kh.dept.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dao.DepartmentDAO;
import edu.kh.dept.model.dao.DepartmentDAOImpl;
import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;

// Service 
// - 비즈니스 로직 처리
//  (데이터 가공, 트랜잭션 제어 처리)

// - 트랜잭션 제어 처리
//  -> 하나의 Service 메서드가 여러 DAO 메서드를 호출할 수 있다!
//  - 호출한 모든 DAO 메서드가 성공 -> Commit / 하나라도 실패 -> Rollback
//	- 
public class DepartmentServiceImpl implements DepartmentService{
	
	// DAO 객체 생성
	private DepartmentDAO dao = new DepartmentDAOImpl();
	
	// 모든 부서 조회
	@Override
	public List<Department> selectAll() throws SQLException {
		
		/* 1. 커넥션 얻어오기 */
		Connection conn = getConnection();
			
		/* 2. DAO 메서드 호출 (매개 변수로 Connection 전달) + 조회 결과 반환*/
		List<Department> deptList = dao.selectAll(conn);
		
		/* 3. SELECT는 트랜잭션 제어 필요 X */
		
		/* 4. 사용 완료된 Connection 닫기 */
		close(conn);
		
		/* 5. 결과 반환 */
		return deptList;
	}

	// 부서 추가 서비스
	@Override
	public int insertDepartment(Department dept) throws DepartmentInsertException {
		
		int result = 0; // 결과 저장 변수
		
		/* 1. 커넥션 얻어오기 */
		Connection conn = getConnection();
		
		try {
			
			/* 2. DAO 메서드 호출 + 결과 반환 (DAO 메서드 수행시 커넥션 필요, 매개변수로 전달) */
			result = dao.insertDepartment(conn, dept);
			
			/* 3. DAO 수행 결과에 따라 트랜잭션 제어 */
			if(result > 0) commit(conn);
			else rollback(conn);
			
		} catch (SQLException e) { // PK, NN 제약조건 위배
		
			e.printStackTrace();
			
			// 예외 발생시 부조건 rollback
			rollback(conn);
			
			/* 제약조건 위배, 정상 수행X 표현 -> 강제로 예외 발생, 사용자 정의 예외 이용 */
			
			throw new DepartmentInsertException();
		} finally { // 예외 발생여부 관계 없이 커넥션 close
			
			/* 4. Connection 반환 */
			close(conn);
		}

		/* 5. 결과 반환*/
		return result;
	}

	/* 여러 부서 추가 */
	@Override
	public int multiInsert(List<Department> deptList) throws SQLException{ //Ucheked Exception<- throw Exception 생략시에도 자동으로 인식
		
		// 1. 커넥션 얻어오기
		Connection conn = getConnection();
		
		int result = 0;
		String currentDeptId = null; // 현재 삽입하려는 부서 코드를 저장하는 변수
		
		try {
			
			// 2. 전달 받은 deptList의 크기(길이) 만큼 반복 DB에 INSERT 하는 DAO 메서드 호출
			for(Department dept : deptList)	{
				currentDeptId = dept.getDeptId(); // 삽입할 deptId를 변수에 저장
				result += dao.insertDepartment(conn, dept);
			}
			
			// 3. 트랜잭션 제어 처리
//		result 누적값(삽입 성공한 행 개수) == deptList의 길이  => 성공, commit, result != deptList의 길이 => 실패 경우 존재, rollback;
			
			if(result == deptList.size())	commit(conn);
			else	rollback(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			// 4. SQL 수행중 오류 발생시 (PK, NN 제약조건 위배, 데이터 크기 초과, DB 연결 종료)
			e.printStackTrace();
			rollback(conn); // SQL이 하나라도 실패시 전체 rollback
			
			// 예외 발생을 Controller(Servlet)에 전달 -> 사용자 정의 예외 강제 발생
			
			// (PK 제약조건 위배만 생각하고 코드 작성)
			throw new DepartmentInsertException(currentDeptId + "부서 코드가 이미 존재합니다.");
		}finally {
			// 5. 사용 완료된 Connection 반환 처리
			close(conn);
		}

		return result;
	}

	/* 부서 삭제 */
	@Override
	public int deleteDepartment(String deptId) throws SQLException { // delete시 제약조건 위배사항 X try~ 사용 필요X, throws 사용
		
		// 결과 저장용 변수
		int result = 0;
		
		// 커넥션 얻어오기
		Connection conn = getConnection();
		
		// DAO method 호출 + 결과 반환
		result = dao.deleteDepartment(deptId, conn);
		
		//DAO 수행 결과에 따라 트랜잭션 제어
		if(result > 0) commit(conn);
		else rollback(conn);
	
		close(conn);
		
		return result;
	}
	
	/* 부서 1행 조회 */
	@Override
	public Department selectOne(String deptId) throws SQLException {

		// 커넥션 얻어오기
		Connection conn = getConnection();
		
		// DAO 메서드 호출후 결과 반환 받기
		Department dept = dao.selectOne(conn,deptId);
		
		// 커넥션 반환
		close(conn);
		return dept;
	}

	// 부서 수정
	@Override
	public int updateDepartment(Department dept) throws SQLException {

		
		Connection conn = getConnection();

		int result = dao.updateDepartment(dept, conn);
		
		if(result > 0)	commit(conn);
		
		else rollback(conn);
					
		close(conn);


		return result;
	}
	// 부서 검색
	@Override
	public List<Department> searchDepartment(String keyword) throws SQLException{
		
		Connection conn = getConnection();
		
		List <Department> deptList = dao.searchDepartment(conn, keyword);
		
		close(conn);
		
		return deptList;
	}

	
	
	
	
}
