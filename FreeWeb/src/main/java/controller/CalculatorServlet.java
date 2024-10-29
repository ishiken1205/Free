package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Calculator;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("num1", null);
		session.setAttribute("num2", null);
		session.setAttribute("operator", null);
		session.setAttribute("display_num", null);
		session.setAttribute("execute_flag", null);
		session.setAttribute("comma_flag", null);
		session.setAttribute("cancel_flag", null);
		RequestDispatcher rd = request.getRequestDispatcher("calculator/calculator.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//セッションスコープから各パラメータを取得
		BigDecimal num1 = (BigDecimal)session.getAttribute("num1");
		if(num1 == null) {
			num1 = BigDecimal.valueOf(0);
		}
		BigDecimal num2 = (BigDecimal)session.getAttribute("num2");
		if(num2 == null) {
			num2 = BigDecimal.valueOf(0);
		}
		BigDecimal display_num = (BigDecimal)session.getAttribute("display_num");
		if(display_num == null) {
			display_num = BigDecimal.valueOf(0);
		}
		String operator = (String)session.getAttribute("operator");
		if(operator == null) {
			operator = "plus";
		}
		String display_flag = (String)session.getAttribute("display_flag");
		if(display_flag == null) {
			display_flag = "2";
		}
		String comma_flag = (String)session.getAttribute("comma_flag");
		if(comma_flag == null) {
			comma_flag = "1";
		}
		String cancel_flag = (String)session.getAttribute("cancel_flag");
		if(cancel_flag == null) {
			cancel_flag = "1";
		}
		String posted_param = request.getParameter("hid_param");
		
		
		
		try {
			BigDecimal posted_num = new BigDecimal(posted_param);
			
			//　入力値が数字の場合の処理
			
			if(display_flag.equals("1")) {
				
				//　=の後の処理
				num1 = BigDecimal.valueOf(0);
				num2 = BigDecimal.valueOf(0);
				operator = null;
				
			}
			
			if(num2.compareTo(BigDecimal.valueOf(0)) == -1) {
				
				// 処理する値がマイナスの場合の処理
				posted_num = posted_num.multiply(BigDecimal.valueOf(-1));
				
			}
			
			if(comma_flag.equals("1")) {
				
				// 処理する値が整数の場合の処理
				num2 = num2.multiply(BigDecimal.valueOf(10)).add(posted_num);
				
			}else {
				
				// 処理する値が小数の場合の処理
				num2 = num2.setScale(num2.scale() + 1);
				BigDecimal digits = new BigDecimal("1");
				
				for (int i = 0; i < num2.scale(); i++) {
					digits = digits.multiply(BigDecimal.valueOf(0.1));
		        }
				
				num2 = num2.add(posted_num.multiply(digits));
				
			}
			
			display_flag = "2";
			cancel_flag = "2";
			display_num = num2;
			
		}catch(Exception e) {
			
			if(posted_param.equals("comma")) {
				
				// 入力値がcommaの場合の処理
				comma_flag = "2";
				cancel_flag = "2";
				display_num = num2;
				
			}else if(posted_param.equals("all_cancel")) {
				
				// 入力値がACの場合の処理
				num1 = null;
				num2 = null;
				display_num = null;
				operator = null;
				display_flag = null;
				comma_flag = null;
				cancel_flag = null;
				
			}else if(posted_param.equals("cancel")) {
				
				// 入力値がCの場合の処理
				num2 = null;
				display_num = null;
				comma_flag = null;
				cancel_flag = null;
				
			}else if(posted_param.equals("equal")) {
				
				// 入力値が=の場合の処理
				Calculator calculator = new Calculator();
				num1 = calculator.execute(num1, num2, operator, "2");
				display_num = num1;
				display_flag = "1";
				comma_flag = null;
				cancel_flag = "2";
				
			}else if(posted_param.equals("plus_minus")) {
				
				// 入力値が±の場合の処理
				if(display_flag.equals("1")) {
					
					// =の後の処理
					num1 = num1.multiply(BigDecimal.valueOf(-1));
					display_num = num1;
					
				}else {
					
					//　=以外の後の処理
					num2 = num2.multiply(BigDecimal.valueOf(-1));
					display_num = num2;
					
				}
				
				cancel_flag = "2";
				
			}else if(posted_param.equals("percent")) {
				
				// 入力値が%の場合の処理
				num1 = display_num.multiply(BigDecimal.valueOf(0.01));
				num2 = num1;
				display_flag = "1";
				cancel_flag = "2";
				display_num = num1;
				
			}else {
				
				// 入力値が演算子の場合の処理
				if(display_flag.equals("1")) {
					
					//　=の後の処理
					display_num = num1;
					
				}else {
					
					//　=以外の後の処理
					Calculator calculator = new Calculator();
					num1 = calculator.execute(num1, num2, operator, display_flag);
					display_num = num1;
					
				}
				
				num2 = null;
				operator = posted_param;
				display_flag = null;
				comma_flag = null;
				cancel_flag = "2";
				
			}
			
			if(display_num != null) {
				
				// 表示する値から余計な0を取り除き、表示形式を整える
				display_num = new BigDecimal(display_num.stripTrailingZeros().toPlainString());
				
			}
		}
		
		// 各パラメータをセッションスコープにセット
		session.setAttribute("cancel_flag", cancel_flag);
		session.setAttribute("num1", num1);
		session.setAttribute("num2", num2);
		session.setAttribute("operator", operator);
		session.setAttribute("display_flag", display_flag);
		session.setAttribute("display_num", display_num);
		session.setAttribute("comma_flag", comma_flag);
		
		RequestDispatcher rd = request.getRequestDispatcher("calculator/calculator.jsp");
		rd.forward(request, response);
	}
}
