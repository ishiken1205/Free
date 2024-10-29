package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Chat;
import model.ChatDao;
import model.ChatUser;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 各パラメータを取得
		String buttonAction = request.getParameter("button_action");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("password_check");
		String searchUserId = request.getParameter("search_user_id");
		String sendText = request.getParameter("send_text");
		String chatLogToId = request.getParameter("chat_log_to_id");
		
		// ボタンによって処理を振り分ける
		if(buttonAction.equals("login")) {
			// ログイン
			
			ChatDao dao = new ChatDao();
			ChatUser user = dao.login(id, password);
			
			if(user == null) {
				// ログインに失敗した場合の処理
				
				// メッセージをビューへ送信
				request.setAttribute("msg", "ログインに失敗しました");
				
				// ログイン画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
				rd.forward(request, response);
				
			} else {
				// ログインに成功した場合の処理
				
				// ユーザーをセッションスコープにセット
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				// チャット履歴を取得
				ArrayList<Chat> chatLog = dao.getChatLog(user.getId());
				
				// チャット履歴をビューに送信
				request.setAttribute("chatLog", chatLog);
				
				// マイページ画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatMyPage.jsp");
				rd.forward(request, response);
				
			}
			
		}else if(buttonAction.equals("logout")) {
			// ログアウト
			
			// ユーザーをセッションスコープから削除
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			
			// チャット相手をセッションスコープから削除
			session.removeAttribute("toUser");
			
			// メッセージをビューへ送信
			request.setAttribute("msg", "ログアウトしました");
			
			// ログイン画面へ画面遷移
			RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("register")) {
			// 新規登録
			
			// ユーザーをデータベースへ新規登録
			ChatDao dao = new ChatDao();
			String msg = dao.register(id, password, passwordCheck, name);
			
			if(msg == null) {
				// 新規登録に成功した場合の処理
				
				// ユーザーをセッションスコープにセット
				HttpSession session = request.getSession();
				ChatUser user = new ChatUser(id.trim(), password.trim(), name.trim());
				session.setAttribute("user", user);
				
				// チャット履歴を取得
				ArrayList<Chat> chatLog = dao.getChatLog(user.getId());
				
				// チャット履歴をビューに送信
				request.setAttribute("chatLog", chatLog);
				
				// マイページ画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatMyPage.jsp");
				rd.forward(request, response);
				
			}else {
				// 新規登録に失敗した場合の処理
				
				// メッセージをビューへ送信
				request.setAttribute("msg", msg);
				
				// 新規登録画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatRegister.jsp");
				rd.forward(request, response);
				
			}
			
		}else if(buttonAction.equals("search_user")) {
			// ユーザー検索
			
			// ログインユーザーを取得
			HttpSession session = request.getSession();
			ChatUser user = (ChatUser)session.getAttribute("user");
			
			// セッションが切れている場合の処理
			if(user == null) {
				
				// ユーザーをセッションスコープから削除
				session.removeAttribute("user");
				
				// メッセージをビューへ送信
				request.setAttribute("msg", "セッションが切れています<br>もう一度ログインしてください");
				
				// ログイン画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
				rd.forward(request, response);
				
			}
			
			ChatDao dao = new ChatDao();
			ChatUser toUser = dao.searchUser(searchUserId);
			
			if(toUser == null) {
				// ユーザーが存在していない場合の処理
				
				// メッセージをビューに送信
				if(searchUserId == null || searchUserId.equals("")) {
					
					request.setAttribute("msg", "ユーザーIDを入力してください");
					
				}else {
					
					request.setAttribute("msg", "ユーザーID『" + searchUserId + "』は存在しません");
					
				}
				
				// チャット履歴を取得
				ArrayList<Chat> chatLog = dao.getChatLog(user.getId());
				
				// チャット履歴をビューに送信
				request.setAttribute("chatLog", chatLog);
				
				// マイページ画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatMyPage.jsp");
				rd.forward(request, response);
				
			} else {
				// ユーザーが存在している場合の処理
				
				// チャット相手をセッションスコープにセット
				session.setAttribute("toUser", toUser);
				
				// チャットを取得
				ArrayList<Chat> chatList = dao.getChat(user.getId(), searchUserId);
				
				// チャットをビューに送信
				request.setAttribute("chatList", chatList);
				
				// チャット画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chat.jsp");
				rd.forward(request, response);
				
			}
			
		}else if(buttonAction.equals("send_chat")) {
			// チャットを送信
			
			// ログインユーザーとチャット相手を取得
			HttpSession session = request.getSession();
			ChatUser user = (ChatUser)session.getAttribute("user");
			ChatUser toUser = (ChatUser)session.getAttribute("toUser");
			
			// セッションが切れている場合の処理
			if(user == null || toUser == null) {
				
				// ユーザーをセッションスコープから削除
				session.removeAttribute("user");
				
				// チャット相手をセッションスコープから削除
				session.removeAttribute("toUser");
				
				// メッセージをビューへ送信
				request.setAttribute("msg", "セッションが切れています<br>もう一度ログインしてください");
				
				// ログイン画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
				rd.forward(request, response);
				
			}
			
			ChatDao dao = new ChatDao();
			ArrayList<Chat> chatList = new ArrayList<>();
			
			// 入力値チェック
			if(sendText == null || sendText.length() == 0) {
				// テキストが無い場合の処理
				
				// チャットを取得
				chatList = dao.getChat(user.getId(), toUser.getId());
				request.setAttribute("msg", "テキストが入力されていません");
				
			}else if(sendText.length() > 100) {
				// テキストが100文字以上の場合の処理
				
				// チャットを取得
				chatList = dao.getChat(user.getId(), toUser.getId());
				request.setAttribute("msg", "テキストは100文字以内で入力してください");
				
			}else {
				// テキストが正常の場合の処理
				
				// チャットを送信
				chatList = dao.sendChat(new Chat(null, user.getId(), toUser.getId(), sendText, Timestamp.valueOf(LocalDateTime.now())));
				
			}
			
			request.setAttribute("chatList", chatList);
			
			// チャット画面へ画面遷移
			RequestDispatcher rd = request.getRequestDispatcher("chat/chat.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("toChatPage")) {
			// チャット画面へ画面遷移
			
			// ログインユーザーを取得
			HttpSession session = request.getSession();
			ChatUser user = (ChatUser)session.getAttribute("user");
			
			// チャット相手をセッションスコープにセット
			ChatDao dao = new ChatDao();
			ChatUser toUser = dao.searchUser(chatLogToId);
			session.setAttribute("toUser", toUser);
			
			if(user == null || toUser == null) {
				
				// ユーザーをセッションスコープから削除
				session.removeAttribute("user");
				
				// チャット相手をセッションスコープから削除
				session.removeAttribute("toUser");
				
				// メッセージをビューへ送信
				request.setAttribute("msg", "セッションが切れています<br>もう一度ログインしてください");
				
				// ログイン画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
				rd.forward(request, response);
				
			}
			
			// チャットを取得
			ArrayList<Chat> chatList = dao.getChat(user.getId(), chatLogToId);
			
			// チャットをビューに送信
			request.setAttribute("chatList", chatList);
			
			// チャット画面へ画面遷移
			RequestDispatcher rd = request.getRequestDispatcher("chat/chat.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("to_login")) {
			// ログイン画面へ画面遷移
			
			RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("to_register")) {
			// 新規登録画面へ画面遷移
			
			RequestDispatcher rd = request.getRequestDispatcher("chat/chatRegister.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("toMyPage")) {
			// マイページへ画面遷移
			
			// チャット相手をセッションスコープから削除
			HttpSession session = request.getSession();
			session.removeAttribute("toUser");
			
			// ログインユーザーを取得
			ChatUser user = (ChatUser)session.getAttribute("user");
			
			if(user == null) {
				
				// ユーザーをセッションスコープから削除
				session.removeAttribute("user");
				
				// メッセージをビューへ送信
				request.setAttribute("msg", "セッションが切れています<br>もう一度ログインしてください");
				
				// ログイン画面へ画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("chat/chatLogin.jsp");
				rd.forward(request, response);
				
			}
			
			// チャット履歴を取得
			ChatDao dao = new ChatDao();
			ArrayList<Chat> chatLog = dao.getChatLog(user.getId());
			
			// チャット履歴をビューに送信
			request.setAttribute("chatLog", chatLog);
			
			RequestDispatcher rd = request.getRequestDispatcher("chat/chatMyPage.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("back")) {
			// トップ画面へ画面遷移
			
			RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
			rd.forward(request, response);
			
		}
	}
}
