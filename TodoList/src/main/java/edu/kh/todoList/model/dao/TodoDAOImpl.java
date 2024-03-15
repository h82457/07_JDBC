package edu.kh.todoList.model.dao;

import static edu.kh.todoList.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todoList.common.JDBCTemplate;
import edu.kh.todoList.model.dto.Todo;

// DAO(Data Access Object) : DB에 접근하는 객체(SQL 수행 + 결과 반환 받기) 
public class TodoDAOImpl implements TodoDAO{

	// JDBC 객체 참조 변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// Properties 객체 참조 변수 선언
	private Properties prop;
	
	// 기본 생성자로 객체 생성시 Properties 객체 생성 + xml 파일 내용 읽어오기
	public TodoDAOImpl() {
		
		try {
			prop = new Properties();
			String path = TodoDAOImpl.class.getResource("/edu/kh/todoList/sql/sql.xml").getPath();
//			 ㄴ> class 파일이 위치한곳에서 sql.xml 파일의 path 얻어오기
			prop.loadFromXML(new FileInputStream(path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override // 완료된 할 일 개수 조회
	public List<Todo> selectAll(Connection conn) throws SQLException {
		
		List<Todo> todoList = new ArrayList<Todo>(); // 결과 저장용 변수 선언 + 객체 생성
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql); // sql 수행 후 반환 받기
			
			while(rs.next()) { // 조회 결과 한 행씩 접근
				int todoNo = rs.getInt("TODO_NO");
				String todoTitle = rs.getString("TODO_TITLE");
				String complete = rs.getString("COMPLETE");
				String reg_date = rs.getString("REG_DATE");
				
				Todo todo = new Todo(todoNo, todoTitle, complete, reg_date); // Todo 객체 생성 + 값세팅, todoList에 추가
				todoList.add(todo);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		return todoList;
	}
	
	
	@Override // 완료된 할 일 개수 조회
	public int getCompleteCount(Connection conn) throws SQLException {
		
		int completeCount = 0;
		
		try {
			String sql = prop.getProperty("getComplteCount");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);  // <- GRUOP BY가 없는 COUNT 그룹 함수의 결과는 1행!
			
			if(rs.next())	completeCount = rs.getInt(1);
			
		} finally {
			close(rs);
			close(stmt);
		}
		return completeCount;
	}

	@Override // 할 일 추가
	public int addTodo(Connection conn, String todoTitle, String todoContent) throws SQLException {
		
		int result = 0; // 결과 저장용 변수 선언
		
		try {
			String sql = prop.getProperty("addTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, todoTitle);
			pstmt.setString(2, todoContent);
			
			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
			
			
		}
		
		return result;
	}

	@Override // 할 일 상세 조회
	public Todo selectTodo(Connection conn, int todoNo) throws SQLException {

		Todo todo = null;
		
		try {
			String sql = prop.getProperty("selectTodo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, todoNo);
			
			rs = pstmt.executeQuery(); // SQL 수행, 결과 반환
			
			if(rs.next()) { // 조회 결과가 있어도 1행 밖에 없음 
											// == if 사용이 효율적
				
				todo = new Todo();
				
				todo.setTodoNo			(rs.getInt("TODO_NO"));
				todo.setTodoTitle		(rs.getString("TODO_TITLE"));
				todo.setTodoContent	(rs.getString("TODO_CONTENT"));
				todo.setComplete		(rs.getString("COMPLETE"));
				todo.setRegDate			(rs.getString("REG_DATE"));
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return todo;
	}

	@Override
	public int changeComplete(Connection conn, int todoNo, String complete) throws SQLException {

		int result = 0;
		
		try {
			String sql = prop.getProperty("changeComplete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, complete);
			pstmt.setInt(2, todoNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	@Override
	public int updateTodo(Connection conn, Todo todo) throws SQLException {

		int result = 0 ;
		
		try {
			String sql = prop.getProperty("updateTodo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, todo.getTodoTitle());
			pstmt.setString(2, todo.getTodoContent());
			pstmt.setInt(3, todo.getTodoNo());
			
			result = pstmt.executeUpdate();
		} finally {
			
			close(pstmt);
		}
		return result;
	}

	@Override
	public int deleteTodo(Connection conn, int todoNo) throws SQLException {

		int result = 0 ;
		
		try {
			String sql = prop.getProperty("deleteTodo");
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, todoNo);
			
			result = pstmt.executeUpdate();
		} finally {
			
			close(pstmt);
		}
		return result;
		
	}
	
}
