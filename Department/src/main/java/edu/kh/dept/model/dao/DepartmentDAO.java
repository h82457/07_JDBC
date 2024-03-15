package edu.kh.dept.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;

public interface DepartmentDAO {

	/** 부서 전체 조회
	 * @param conn
	 * @return deptList
	 * @throws SQLException
	 */
	List<Department> selectAll(Connection conn) throws SQLException;

	/** 부서 추가 
	 * @param conn
	 * @param dept
	 * @returnresult
	 * @throws SQLException
	 */
	int insertDepartment(Connection conn, Department dept) throws SQLException;

	int deleteDepartment(String deptId, Connection conn) throws SQLException;

	/** 부서 1행 조회
	 * @param conn
	 * @param deptId
	 * @return dept
	 * @throws SQLException
	 */
	Department selectOne(Connection conn, String deptId) throws SQLException;

	/** 부서 수정
	 * @param dept
	 * @return result
	 */
	int updateDepartment(Department dept, Connection conn) throws SQLException;

	/** 부서 검색
	 * @param conn
	 * @param keyword
	 * @return deptList (조회 결과 없으면 비어있음)
	 * @throws SQLException
	 */
	List<Department> searchDepartment(Connection conn, String keyword) throws SQLException;

}
