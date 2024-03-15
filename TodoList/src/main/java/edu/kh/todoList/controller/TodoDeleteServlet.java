package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.service.TodoService;
import edu.kh.todoList.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/delete")

public class TodoDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		try {
		
			TodoService service = new TodoServiceImpl();
			
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			int result = service.deleteTodo(todoNo);
			
			String path = null;
			String message = null;
			
			if(result > 0 ) {
				
				path = "/"; // 메인페이지
				message = "삭제 되었습니다.";
				
			} else {
				
				path ="/todo/detail?todoNo=" + todoNo; // 상세조회
				message = "삭제 실패...";
			}
			
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
