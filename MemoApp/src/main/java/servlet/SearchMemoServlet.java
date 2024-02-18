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

import model.IndicateMemoLogic;
import model.Memo;
import model.SearchMemoLogic;

@WebServlet("/SearchMemoServlet")
public class SearchMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		IndicateMemoLogic iml = new IndicateMemoLogic();
		Memo m = iml.execute(id, title);
		if (m == null) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		}else {
			hs.setAttribute("indicatedMemo", m);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("searchMemoTitle");
		SearchMemoLogic sml = new SearchMemoLogic();
		List<Memo> ml = sml.execute(id, title);
		String searchResult = "";
		for(Memo m : ml) {
			searchResult +=
					"<a class=\"memo\" href=\"SearchMemoServlet?title=" + m.getTitle() + "\">" + m.getTitle() + "</a><br>";
		}
		if(searchResult.length() == 0) {
			searchResult = "該当するメモはありません";
		}
		request.setAttribute("searchResult", searchResult);	
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/searchMemoResult.jsp");
		rd.forward(request, response);
	}
}