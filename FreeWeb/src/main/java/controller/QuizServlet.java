package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Quiz;
import model.QuizDao;
import model.QuizLogic;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("quiz/quizTop.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String buttonAction = request.getParameter("button_action");
		
		if(buttonAction.equals("quiz_start")) {
			// スタートボタンの処理
			
			// 問題リストを作成
			QuizDao dao = new QuizDao();
			ArrayList<Quiz> quizList = dao.makeQuiz();
			HttpSession session = request.getSession();
			session.setAttribute("quizList", quizList);
			
			// 1問目をビューへセット
			Quiz quiz = quizList.get(0);
			session.setAttribute("quiz",quiz);
			
			RequestDispatcher rd = request.getRequestDispatcher("quiz/quizQuestion.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("to_top")) {
			// トップへ戻るボタンの処理
			
			RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("to_quiz_top")) {
			// 戻るボタンの処理
			
			// スコープの問題を削除
			HttpSession session = request.getSession();
			session.removeAttribute("quizList");
			session.removeAttribute("quiz");
			
			RequestDispatcher rd = request.getRequestDispatcher("quiz/quizTop.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("answer")) {
			// 解答するボタンの処理
			
			// 入力された解答を取得
			String answerYear = request.getParameter("answer_year");
			String answerMonth = request.getParameter("answer_month");
			String answerDay = request.getParameter("answer_day");
			
			HttpSession session = request.getSession();
			
			// 問題リストを取得
			ArrayList<Quiz> quizList = (ArrayList<Quiz>)session.getAttribute("quizList");
			
			// 現在の問題を取得
			Quiz quiz = (Quiz)session.getAttribute("quiz");
			
			// 答えが合っているか判定
			QuizLogic quizLogic = new QuizLogic();
			boolean result = quizLogic.execute(quiz, answerYear, answerMonth, answerDay);
			
			if(result) {
				// 正解の場合の処理
				
				request.setAttribute("true_msg", "〇 正解！");
				
			}else {
				// 不正解の場合の処理
				
				request.setAttribute("false_msg", "× 不正解...");
				
			}
			
			// 問題の正誤をセット
			quiz.setResult(result);
			quizList.set(quiz.getQuizCount() -1 , quiz);
			session.setAttribute("quizList", quizList);
			
			RequestDispatcher rd = request.getRequestDispatcher("quiz/quizResult.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("next_quiz")) {
			// 次の問題へボタンの処理
			
			HttpSession session = request.getSession();
			
			// 問題リストを取得
			ArrayList<Quiz> quizList = (ArrayList<Quiz>)session.getAttribute("quizList");
			
			// 現在の問題を取得
			Quiz quiz = (Quiz)session.getAttribute("quiz");
			
	
			// 次の問題をセット
			
			quiz = quizList.get(quiz.getQuizCount());
			session.setAttribute("quiz", quiz);
			
			RequestDispatcher rd = request.getRequestDispatcher("quiz/quizQuestion.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("final_result")) {
			// 最終結果を見るボタンの処理
			
			HttpSession session = request.getSession();
			
			session.removeAttribute("quiz");
			
			// 問題リストを取得
			ArrayList<Quiz> quizList = (ArrayList<Quiz>)session.getAttribute("quizList");
			
			// 正解数をセット
			int trueCount = 0;
			
			for(Quiz quiz : quizList) {
				if(quiz.getResult()) {
					trueCount ++;
				}
			}
			
			request.setAttribute("true_count", trueCount);
			
			RequestDispatcher rd = request.getRequestDispatcher("quiz/quizFinalResult.jsp");
			rd.forward(request, response);
			
		}
	}
}