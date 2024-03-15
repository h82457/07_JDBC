package edu.kh.todoList.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.service.TodoService;
import edu.kh.todoList.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 매핑할 요청 주소 공란 : 최상위 주소 요청
@WebServlet("")

public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		try {
			TodoService service = new TodoServiceImpl(); // 서비스 객체 생성
			
			/* 할 일 목록, 완료된 할 일 개수 조회 서비스 호출 + 결과 반환 받기 */
			
			Map<String, Object> map = service.selectAll(); // 메서드에서 return값은 1개만 반환 가능, Map으로 하나로 묶어 전달
//							ㄴ> Object: 최상위 부모로 모든 타입 저장 가능
			
			// map에 담긴 데이터 분리 (다운캐스팅)
			List<Todo> todoList = (List<Todo>)map.get("todoList");
			int completeCount = (int)map.get("completeCount");
			
			// 분리된 데이터를 request 객체에 속성으로 추가
			req.setAttribute("todoList", todoList);
			req.setAttribute("completeCount", completeCount);

			String path = "/WEB-INF/views/main.jsp";
			
			// main.jsp로 forward
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
