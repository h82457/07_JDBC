package edu.kh.dept.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import edu.kh.dept.common.JDBCTemplate;
import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 현재 클래스를 서블릿으로 등록 (서버 시행시 객체 생성) + URL 매핑
@WebServlet("/department/selectAll")
public class SelectAllServlet extends HttpServlet{

	
	// Get방식 요청 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {

			// Service 객체 생성
			DepartmentService service = new DepartmentServiceImpl();
			
			// 모든 부서 조회 Service 호출후 결과 반환
			List<Department> deptList = service.selectAll();
			
			// DB 조회 결과를 request scope에 세팅 + JSP로 요청 위임(forward)
			req.setAttribute("deptList", deptList);
			String path = "/WEB-INF/views/selectAll.jsp";
			req.getRequestDispatcher(path).forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}