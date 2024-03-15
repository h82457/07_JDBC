package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.service.TodoService;
import edu.kh.todoList.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

///todo/detail?todoNo=${todo.todoNo}
//		주소  ?-> parameter

@WebServlet("/todo/detail") 

public class TodoDetailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			TodoService service = new TodoServiceImpl(); // 서비스객체 생성
			
			// parameter ( todoNo) 얻어오기 + 데이터 파싱
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));

			Todo todo = service.selectTodo(todoNo); // 서비스 호출 + 결과 반환 받기
			
			
			if(todo != null) { // 조회 결과가 있을 경우
				req.setAttribute("todo", todo);
				String path = "/WEB-INF/views/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			} else { // 조회 결과가 없을 경우
				// -> 파라미터로 전달 받은 todoNo가 DB에 존재X
				req.getSession().setAttribute("message", "해당 할 일이 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
