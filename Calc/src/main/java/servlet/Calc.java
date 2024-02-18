package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CalcModel;

@WebServlet("/Calc")
public class Calc extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/CalcForm.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String as = (String)request.getParameter("数値A");
		String bs = (String)request.getParameter("数値B");
		
		int a, b;
		String result;
		
		if(as.length() != 0 && bs.length() !=0) {
			a = Integer.parseInt(as); 
			b = Integer.parseInt(bs);
		
		String enzanshi = (String)request.getParameter("enzanshi");
		
		CalcModel cm = new CalcModel();
		result = cm.execute(a,b,enzanshi);
		}else {
			result = "数値が入力されていません";
		}
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/CalcResult.jsp");
		rd.forward(request, response);
	}
}