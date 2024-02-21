package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.RegisterAccountLogic;

@WebServlet("/RegisterAccountServlet")
public class RegisterAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account ac = new Account();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		ac.setId(id);
		if(!(pass.equals(pass2))  || id.length() == 0 || pass.length() == 0) {
			request.setAttribute("registerResult","アカウントの登録に失敗しました。");
		}else {
			ac.setPass(pass);
			RegisterAccountLogic ral = new RegisterAccountLogic();
			boolean result = ral.execute(ac);
			if(result) {
				request.setAttribute("registerResult",id + "さんのアカウントを登録しました");
			} else {
				request.setAttribute("registerResult","アカウントの登録に失敗しました");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/registerAccountResult.jsp");
		rd.forward(request, response);
	}
}