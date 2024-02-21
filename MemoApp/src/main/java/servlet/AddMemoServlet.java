package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AddMemoLogic;
import model.Memo;

@WebServlet("/AddMemoServlet")
public class AddMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/addMemo.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		String id = (String)hs.getAttribute("id");
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		if(title.length() == 0 || memo.length() == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/addMemoError.jsp");
			rd.forward(request, response);
		}else {
			Memo m = new Memo();
			m.setId(id);
			m.setTitle(title);
			m.setMemo(memo);
			AddMemoLogic aml = new AddMemoLogic();
			boolean result = aml.execute(m);
			if (result) {
				request.setAttribute("title", title);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/addMemoResult.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/addMemoError.jsp");
				rd.forward(request, response);
			}
		}
	}
}