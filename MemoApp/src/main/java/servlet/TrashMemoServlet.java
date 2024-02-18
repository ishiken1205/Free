package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AddMemoLogic;
import model.DeleteAllMemoLogic;
import model.IndicateMemoLogic;
import model.Memo;
import model.SearchMemoLogic;

@WebServlet("/TrashMemoServlet")
public class TrashMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String link = request.getParameter("link");
		if(link.equals("del")) {
			HttpSession hs = request.getSession();
			Memo m = (Memo) hs.getAttribute("indicatedMemo");
			AddMemoLogic tml = new AddMemoLogic();
			tml.executeTrashMemo(m);
			hs.removeAttribute("indicatedMemo");
			String id = (String) hs.getAttribute("id");
			String title = "";
			SearchMemoLogic sml = new SearchMemoLogic();
			List<Memo> ml = sml.execute(id, title);
			String searchResult = "";
			for(Memo m2 : ml) {
				searchResult +=
						"<a class=\"memo\" href=\"SearchMemoServlet?title=" + m2.getTitle() + "\">" + m2.getTitle() + "</a><br>";
			}
			if(searchResult.length() == 0) {
				searchResult = "まだメモがありません<br>";
			}
			request.setAttribute("searchResult", searchResult);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		}if(link.equals("trash")) {
			List<Memo> ml = new ArrayList<Memo>();
			HttpSession hs = request.getSession();
			String id = (String) hs.getAttribute("id");
			SearchMemoLogic sml = new SearchMemoLogic();
			ml = sml.executeTrashMemo(id);
			String str = "";
			for(Memo m : ml) {
				str +=
						"<a class=\"memo\" href=\"TrashMemoServlet?link=trashMemo&title=" + m.getTitle() + "\">" + m.getTitle() + "</a><br>";
			}
			if(str.length() == 0) {
				str = "該当するメモはありません<br><br><hr>";
			}else {
				str += "<br><hr><br><a href=\"TrashMemoServlet?link=deleteAll\">全て削除する</a><br>";
			}
			request.setAttribute("trashMemo", str);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/trash.jsp");
			rd.forward(request, response);
		}if(link.equals("trashMemo")) {
			HttpSession hs = request.getSession();
			String id = (String) hs.getAttribute("id");
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");
			IndicateMemoLogic iml = new IndicateMemoLogic();
			Memo m = iml.executeTrashMemo(id, title);
			if (m == null) {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
				rd.forward(request, response);
			}else {
				hs.setAttribute("indicatedMemo", m);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/trashMemo.jsp");
				rd.forward(request, response);
			}
		}if(link.equals("deleteAll")) {
			HttpSession hs = request.getSession();
			String id = (String) hs.getAttribute("id");
			DeleteAllMemoLogic daml = new DeleteAllMemoLogic();
			boolean result = daml.execute(id);
			if(result) {
				request.setAttribute("trashMemo", "該当するメモはありません<br><br><hr>");
			}else {
			request.setAttribute("trashMemo", "削除に失敗しました。" + "<br><hr><br><a href=\"TrashMemoServlet?link=deleteAll\">全て削除する</a><br>");
			}
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/trash.jsp");
			rd.forward(request, response);
		}
	}
}
