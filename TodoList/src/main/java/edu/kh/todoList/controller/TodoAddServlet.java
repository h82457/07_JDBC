package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.service.TodoService;
import edu.kh.todoList.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/add")

public class TodoAddServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			TodoService service = new TodoServiceImpl(); // 서비스 객체 생성
			
			String todoTitle = req.getParameter("todoTitle"); // 파라미터 얻어오기
			String todoContent = req.getParameter("todoContent");
			
			// 할 일 추가 서비스 호출후 + 반환 받기
			int result = service.addTodo(todoTitle, todoContent); // 매개변수가 4개 이상일때 묶어 전달 (4개 기준)
			
			String message = null;
			
			if(result > 0) message = "할 일 추가 성공!!!";
			else	message = "할 일 추가 실패...";
			
			HttpSession session = req.getSession(); // redirect시 세션 재생성
			session.setAttribute("message", message);
			
			resp.sendRedirect("/");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
