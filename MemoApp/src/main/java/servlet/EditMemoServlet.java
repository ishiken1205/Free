package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EditMemoLogic;
import model.Memo;
import model.SearchMemoLogic;

@WebServlet("/EditMemoServlet")
public class EditMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
	}
	public void destroy() {
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/editMemo.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession hs = request.getSession();
		Memo beforeMemo = (Memo) hs.getAttribute("indicatedMemo");
		Memo afterMemo = new Memo();
		String id = (String) hs.getAttribute("id");
		String afterTitle = request.getParameter("title");
		String memo = request.getParameter("memo");
		if(afterTitle.length() == 0 || memo.length() == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/editMemoError.jsp");
			rd.forward(request, response);
		}else {
			afterMemo.setId(id);
			afterMemo.setTitle(afterTitle);
			afterMemo.setMemo(memo);
			EditMemoLogic eml = new EditMemoLogic();
			eml.execute(beforeMemo, afterMemo);
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
		}
	}
}