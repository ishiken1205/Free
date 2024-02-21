package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteAccountLogic;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String link = request.getParameter("link");
		if(link.equals("deleteCheck")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/deleteAccount.jsp");
			rd.forward(request, response);
		}if(link.equals("deleteAccount")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/deleteAccount2.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String passCheck = request.getParameter("passCheck");
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		DeleteAccountLogic dal = new DeleteAccountLogic();
		boolean result = dal.execute(id, passCheck);
		String str;
		if(result) {
			str = "アカウントを削除しました。<br><br><hr><br><a href=\"TopServlet\">トップ画面へ戻る</a>";
		}else {
			str = "アカウントを削除できませんでした。<br><br><hr><br><a href=\"LoginServlet?link=main3\">メイン画面へ戻る</a>";
		}
		request.setAttribute("str", str);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/deleteAccountResult.jsp");
		rd.forward(request, response);
	}
}