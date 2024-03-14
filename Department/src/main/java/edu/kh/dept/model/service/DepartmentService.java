package edu.kh.dept.model.service;

import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;

public interface DepartmentService {

	/** 모든 부서 조회
	 * @return deptList(부서 목록)
	 * @throws SQLException
	 */
	List<Department> selectAll() throws SQLException;

	/** 부서 추가 서비스
	 * @param dept
	 * @return result : 삽입된 행의 개수
	 * @throws SQLException
	 */
	int insertDepartment(Department dept) throws SQLException;

	int multiInsert(List<Department> deptList) throws SQLException;

	/** 부서 삭제
	 * @param deptId
	 * @return return
	 * @throws SQLException
	 */
	int deleteDepartment(String deptId) throws SQLException;

	/** 부서 1행 조회
	 * @param deptId
	 * @return dept (부서 1행 데이터가 담긴 객체)
	 * @throws SQLException
	 */
	Department selectOne(String deptId) throws SQLException;

	/** 부서 수정
	 * @param dept
	 * @return result
	 */
	int updateDepartment(Department dept) throws SQLException;

	/** 부서 검색
	 * @param keyword
	 * @return deptList
	 */
	List<Department> searchDepartment(String keyword) throws SQLException;
	
	
	
	
}
