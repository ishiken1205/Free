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
import model.Memo;
import model.SearchMemoLogic;

@WebServlet("/RestoreMemoServlet")
public class RestoreMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		Memo m = (Memo) hs.getAttribute("indicatedMemo");
		AddMemoLogic aml = new AddMemoLogic();
		boolean result = aml.executeRestoreMemo(m);
		if(result) {
			List<Memo> ml = new ArrayList<Memo>();
			String id = (String) hs.getAttribute("id");
			SearchMemoLogic sml = new SearchMemoLogic();
			ml = sml.executeTrashMemo(id);
			String str = "";
			for(Memo m2 : ml) {
				str +=
						"<a class=\"memo\" href=\"TrashMemoServlet?link=trashMemo&title=" + m2.getTitle() + "\">" + m2.getTitle() + "</a><br>";
			}
		if(str.length() == 0) {
			str = "該当するメモはありません" + "<br><br><hr>";
		}else {
			str += "<br><hr><br><a href=\"TrashMemoServlet?link=deleteAll\">全て削除する</a><br>";
		}
			request.setAttribute("trashMemo", str);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/trash.jsp");
			rd.forward(request, response);
			hs.removeAttribute("indicatedMemo");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
			rd.forward(request, response);
			hs.removeAttribute("indicatedMemo");
		}
	}
}