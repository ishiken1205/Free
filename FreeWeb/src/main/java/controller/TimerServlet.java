package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TimerServlet")
public class TimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//ボタンによる処理の振り分け
		String buttonAction = request.getParameter("button_action");
		
		if(buttonAction.equals("set")) {
			// セットボタンの処理
			
			// 各入力値を取得
			String hour = request.getParameter("hour");
			String minute = request.getParameter("minute");
			String second = request.getParameter("second");
			
			// 入力値をチェック
			if(hour == null || hour.length() == 0) {
				hour = "00";
			}else if(hour.length() == 1) {
				hour = "0" + hour;
			}else if(hour.length() >= 3) {
				
				request.setAttribute("msg", "時間の数値は2桁までを入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
				rd.forward(request, response);
				
			}else if(Integer.parseInt(hour) < 0) {
				
				request.setAttribute("msg", "時間の数値は0以上を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
				rd.forward(request, response);
				
			}
			
			if(minute == null || minute.length() == 0) {
				minute = "00";
			}else if(minute.length() == 1) {
				minute = "0" + minute;
			}else if(minute.length() >= 3) {
				
				request.setAttribute("msg", "分の数値は2桁までを入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
				rd.forward(request, response);
				
			}else if(Integer.parseInt(minute) < 0 || Integer.parseInt(minute) > 59) {
				
				request.setAttribute("msg", "分の数値は0～59を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
				rd.forward(request, response);
				
			}
			
			if(second == null || second.length() == 0) {
				second = "00";
			}else if(second.length() == 1) {
				second = "0" + second;
			}else if(second.length() >= 3) {
				
				request.setAttribute("msg", "秒の数値は2桁までを入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
				rd.forward(request, response);
				
			}else if(Integer.parseInt(second) < 0 || Integer.parseInt(second) > 59) {
				
				request.setAttribute("msg", "秒の数値は0～59を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
				rd.forward(request, response);
				
			}
			
			if(hour.equals("00") & minute.equals("00") & second.equals("00")) {
				
				request.setAttribute("msg", "時間を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
				rd.forward(request, response);
				
			}
			
			// ビューに送る
			request.setAttribute("hour", hour);
			request.setAttribute("minute", minute);
			request.setAttribute("second", second);
			
			RequestDispatcher rd = request.getRequestDispatcher("timer/timer.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("reset")) {
			// リセットボタンの処理
			
			RequestDispatcher rd = request.getRequestDispatcher("timer/setTimer.jsp");
			rd.forward(request, response);
			
		}
	}
}