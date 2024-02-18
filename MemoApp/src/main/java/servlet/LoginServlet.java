package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginLogic;
import model.Memo;
import model.SearchMemoLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String link = request.getParameter("link");
		if(link.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
		}if(link.equals("main")) {
			HttpSession hs = request.getSession();
			String id = (String) hs.getAttribute("id");
			request.setCharacterEncoding("UTF-8");
			String title = "";
			SearchMemoLogic sml = new SearchMemoLogic();
			List<Memo> ml = sml.execute(id, title);
			String searchResult = "";
			for(Memo m : ml) {
				searchResult +=
						"<a class=\"memo\" href=\"SearchMemoServlet?title=" + m.getTitle() + "\">" + m.getTitle() + "</a><br>";
			}
			if(searchResult.length() == 0) {
				searchResult = "まだメモがありません<br>";
			}
			request.setAttribute("searchResult", searchResult);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		}if(link.equals("main2")) {
			HttpSession hs = request.getSession();
			String id = (String) hs.getAttribute("id");
			request.setCharacterEncoding("UTF-8");
			String title = "";
			SearchMemoLogic sml = new SearchMemoLogic();
			List<Memo> ml = sml.execute(id, title);
			String searchResult = "";
			for(Memo m : ml) {
				searchResult +=
						"<a class=\"memo\" href=\"SearchMemoServlet?title=" + m.getTitle() + "\">" + m.getTitle() + "</a><br>";
			}
			if(searchResult.length() == 0) {
				searchResult = "まだメモがありません<br>";
			}
			request.setAttribute("searchResult", searchResult);
			hs.removeAttribute("indicatedMemo");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		}if(link.equals("main3")) {
			HttpSession hs = request.getSession();
			String id = (String) hs.getAttribute("id");
			request.setCharacterEncoding("UTF-8");
			String title = "";
			SearchMemoLogic sml = new SearchMemoLogic();
			List<Memo> ml = sml.execute(id, title);
			String searchResult = "";
			for(Memo m : ml) {
				searchResult +=
						"<a class=\"memo\" href=\"SearchMemoServlet?title=" + m.getTitle() + "\">" + m.getTitle() + "</a><br>";
			}
			if(searchResult.length() == 0) {
				searchResult = "まだメモがありません<br>";
			}
			request.setAttribute("searchResult", searchResult);
			hs.removeAttribute("trashMemo");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginLogic ll = new LoginLogic();
		Account ac = new Account();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		ac.setId(id);
		ac.setPass(pass);
		boolean result = ll.execute(ac);
		if(result) {
			HttpSession hs = request.getSession();
			hs.setAttribute("id",id);
			hs.setAttribute("pass", pass);
			String title = "";
			SearchMemoLogic sml = new SearchMemoLogic();
			List<Memo> ml = sml.execute(id, title);
			String searchResult = "";
			for(Memo m : ml) {
				searchResult +=
						"<a class=\"memo\" href=\"SearchMemoServlet?title=" + m.getTitle() + "\">" + m.getTitle() + "</a><br>";
			}
			if(searchResult.length() == 0) {
				searchResult = "まだメモがありません<br>";
			}
			request.setAttribute("searchResult", searchResult);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/loginError.jsp");
			rd.forward(request, response);
		}
	}
}