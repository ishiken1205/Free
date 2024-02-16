package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PassbookDAO;
import model.Account;
import model.DeleteAccountLogic;
import model.LoginLogic;
import model.RegisterAccountLogic;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String link = request.getParameter("link");
		
		if(link.equals("registerAccount")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
			rd.forward(request, response);
		}
		if(link.equals("deleteAccount")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/deleteAccount.jsp");
			rd.forward(request, response);
		}
		if(link.equals("deleteAccount2")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/deleteAccount2.jsp");
			rd.forward(request, response);
		}
		if(link.equals("logout")) {
			HttpSession hs = request.getSession();
			hs.removeAttribute("loginAccount");
			request.setAttribute("msg", "<hr><h2>ログアウトしました</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String link = request.getParameter("link");
		
		if(link.equals("registerAccount")) {
			Account ac = new Account();
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String pass2 = request.getParameter("pass2");
			ac.setId(id);
			String msg;
			if(!(pass.equals(pass2))  || id.length() == 0 || pass.length() == 0) {
				msg = "<hr><h2>アカウントの登録に失敗しました</h2>";
			}else {
				ac.setPass(pass);
				RegisterAccountLogic ral = new RegisterAccountLogic();
				boolean result = ral.execute(ac);
				if(result) {
					msg = "<hr><h2>" + id + "さんのアカウントを登録しました</h2>";
				} else {
					msg = "<hr><h2>アカウントの登録に失敗しました</h2>";
				}
			}
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
			rd.forward(request, response);
		}
		if(link.equals("deleteAccount")) {
			request.setCharacterEncoding("UTF-8");
			String passCheck = request.getParameter("passCheck");
			HttpSession hs = request.getSession();
			Account ac = (Account) hs.getAttribute("loginAccount");
			String msg;
			if(ac.getPass().equals(passCheck)) {
				DeleteAccountLogic dal = new DeleteAccountLogic();
				dal.execute(ac);
					msg = "<hr><h2>アカウントを削除しました</h2>";
					request.setAttribute("msg", msg);
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
					rd.forward(request, response);
			}else {
				msg = "<hr><h2>パスワードが違います</h2>";
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/deleteAccount2.jsp");
				rd.forward(request, response);
			}
		}
		if(link.equals("login")) {
			LoginLogic ll = new LoginLogic();
			Account ac = new Account();
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			ac.setId(id);
			ac.setPass(pass);
			boolean result = ll.execute(ac);
			if(result) {
				PassbookDAO dao = new PassbookDAO();
				String passbookList = dao.findByPassbookList(ac);
				request.setAttribute("passbookList", passbookList);
				HttpSession hs = request.getSession();
				hs.setAttribute("loginAccount",ac);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
				rd.forward(request, response);
			}else {
				String msg = "<hr><h2>ログインに失敗しました</h2>";
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
				rd.forward(request, response);
			}
		}
	}
}