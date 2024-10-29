package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FormChecker;
import model.User;
import model.UserMasterMaintenanceDao;

@WebServlet("/UserMasterMaintenanceServlet")
public class UserMasterMaintenanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String msg = null;
		
		// 各パラメータを受け取る
		String processDivision = request.getParameter("process_division");
		String userIdHeader = request.getParameter("user_id_header");
		String buttonAction = request.getParameter("button_action");
		String userIdInfo = request.getParameter("user_id_info");
		String userNameK = request.getParameter("user_name_k");
		String userNameE = request.getParameter("user_name_e");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		
		User user = new User(userIdInfo, userNameK, userNameE, startDate, endDate);
		
		// ボタンのactionパラメータで処理を振り分ける
		if(buttonAction.equals("execute")) {
			//実行ボタンの処理
			
			if(processDivision.equals("blank")) {
				// 処理区分を選択していない場合の処理
				msg = "処理区分が入力されていません。";
				
			}else if(userIdHeader.equals("")) {
				// ヘッダ.ユーザーIDが未入力の場合の処理
				msg = "ユーザーIDが入力されていません。";
				
			}else if(processDivision.equals("reference")) {
				// 参照の処理
				UserMasterMaintenanceDao dao = new UserMasterMaintenanceDao();
				user = dao.reference(userIdHeader);
				
				if(user == null) {
					msg = "ユーザーID『" + userIdHeader + "』は存在しません。";
				}
				
			}else if(processDivision.equals("register")) {
				// 登録の処理
				
				// 各入力値のバリデーション
				FormChecker formChecker = new FormChecker();
				msg = formChecker.execute(userIdInfo, userNameK, userNameE, startDate, endDate);
				
				if(msg == null) {
					// 入力値が正常であった場合、登録の処理を行う
					
					UserMasterMaintenanceDao dao = new UserMasterMaintenanceDao();
					msg = dao.register(user);
					
				}
				
			}else if(processDivision.equals("update")) {
				// 更新の処理
				
				// 各入力値のバリデーション
				FormChecker formChecker = new FormChecker();
				msg = formChecker.execute(userIdInfo, userNameK, userNameE, startDate, endDate);
				
				if(msg == null) {
					// 入力値が正常であった場合、更新の処理を行う
					
					UserMasterMaintenanceDao dao = new UserMasterMaintenanceDao();
					msg = dao.update(userIdHeader, user);
					
				}
				
			}else if(processDivision.equals("delete")) {
				// 削除の処理
				UserMasterMaintenanceDao dao = new UserMasterMaintenanceDao();
				msg = dao.delete(userIdHeader);
				
			}
			request.setAttribute("msg",msg);
			request.setAttribute("user_id_header", userIdHeader);
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("userMasterMaintenance/userMasterMaintenance.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("cancel")) {
			// キャンセルボタンの処理
			
			RequestDispatcher rd = request.getRequestDispatcher("userMasterMaintenance/userMasterMaintenance.jsp");
			rd.forward(request, response);
			
		}else if(buttonAction.equals("back")) {
			// 戻るボタンの処理
			
			RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
			rd.forward(request, response);
			
		}
	}
}
