package accbook.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import accbook.dto.AccountBook;
import accbook.service.AccountBookService;
import accbook.service.AccountBookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		AccountBookService service = new AccountBookServiceImpl();
		
		try {
			
			List<AccountBook> accList = service.selectAll();
			
			req.setAttribute("accList", accList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String path = "/WEB-INF/views/main.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
