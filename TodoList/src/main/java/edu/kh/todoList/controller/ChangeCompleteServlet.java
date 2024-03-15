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

// /todo/changeComplete?todoNo=${todoNo}&complete=${complete}

@WebServlet("/todo/changeComplete")

public class ChangeCompleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 서비스객체 생성
			TodoService service = new TodoServiceImpl();
			
			// 파라미터 얻어오기(todoNo는 int형으로)
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			String complete = req.getParameter("complete");
			
			// 서비스 메서드 호출후 결과 반환 받기
			int result = service.changeComplete(todoNo, complete);
			
			String message = "";
			
			HttpSession session =  req.getSession();
			
			if(result > 0) message = "변경 성공!";
			else	message = "변경 실패...";
			
			session.setAttribute("message", message);
			resp.sendRedirect("/todo/detail?todoNo=" + todoNo);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
