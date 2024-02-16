package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PassbookDAO;
import model.Account;
import model.Passbook;

@WebServlet("/PassbookServlet")
public class PassbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
	}
	public void destroy() {
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String link = request.getParameter("link");
		if(link.equals("createPassbook")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/createPassbook.jsp");
			rd.forward(request, response);
		}
		String title = request.getParameter("title");
		PassbookDAO dao = new PassbookDAO();
		HttpSession hs = request.getSession();
		Account account = (Account) hs.getAttribute("loginAccount");
		dao.indicatePassbook(account.getId(), title);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/passbook.jsp");
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession hs = request.getSession();
		Account account = (Account) hs.getAttribute("loginAccount");
		String link = request.getParameter("link");
		if(link.equals("createPassbook")) {
			Passbook passbook = new Passbook();
			String title = request.getParameter("title");
			int money = Integer.parseInt(request.getParameter("money"));
			passbook.setId(account.getId());
			passbook.setTitle(title);
			passbook.setMoney(money);
			PassbookDAO dao = new PassbookDAO();
			boolean result = dao.createPassbook(passbook);
			if(result) {
				request.setAttribute("passbook", passbook);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/passbook.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
				rd.forward(request, response);
			}
		}
	}
}