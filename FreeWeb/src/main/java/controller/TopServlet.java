package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 遷移先を取得
		String buttonAction = request.getParameter("button_action");
		
		// 取得したパラメータに応じて画面遷移を行う
		if(buttonAction.equals("user_master_maintenance")) {
			// ユーザーマスターメンテナンス
			
			RequestDispatcher rd = request.getRequestDispatcher("userMasterMaintenance/userMasterMaintenance.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("calculator")) {
			// 電卓
			
			RequestDispatcher rd = request.getRequestDispatcher("calculator/calculator.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("quiz")) {
			// クイズ
			
			RequestDispatcher rd = request.getRequestDispatcher("quiz/quizTop.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("timer")) {
			// タイマー
			
			RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("chat")) {
			// チャット
			
			RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
			rd.forward(request, response);
			
		}
	}
}
