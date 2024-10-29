package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatDao {
	
	String db_url = "jdbc:postgresql://localhost/ecwdb";
	String db_user = "postgres";
	String db_pass = "69201610";
	
	// 新規登録
	public String register(String id, String password, String passwordCheck, String name) {
		
		// 入力値チェック
		FormChecker formChecker = new FormChecker();
		String msg = formChecker.executeChatUser(id, password, passwordCheck, name);
		
		if(msg != null) {
			// 入力値が異常である場合の処理
			
			return msg;
			
		}else {
			// 入力値が正常である場合の処理
			
			id = id.trim();
			password = password.trim();
			passwordCheck = passwordCheck.trim();
			name = name.trim();
			
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				return "JDBCエラーが発生しました";
			}
			
			try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
				
				// IDが既に存在しているかをチェック
				String selectSql = "SELECT * FROM chat_user WHERE id = ?";
				PreparedStatement selectPs = con.prepareStatement(selectSql);
				
				selectPs.setString(1, id);
				
				ResultSet rs = selectPs.executeQuery();
				
				if(rs.next()) {
					// 存在している場合の処理
					
					//エラーメッセージを返す
					return "ユーザーID『" + id + "』は既に存在しています";
					
				} else {
					// 存在している場合の処理
					
					// ユーザーをデーターベースに追加
					String insertSql = "insert into chat_user values(?,?,?)";
					PreparedStatement insertPs = con.prepareStatement(insertSql);
					
					insertPs.setString(1, id);
					insertPs.setString(2, password);
					insertPs.setString(3, name);
					
					int count = insertPs.executeUpdate();
					
					if(count == 1) {
						// 新規登録に成功した場合の処理
						return null;
						
					} else {
						// 新規登録に失敗した場合の処理
						return "新規登録に失敗しました";
						
					}
				}
			}
			catch(SQLException e) {
				return "データベース接続に失敗しました";
			}
		}
	}
	
	// ログイン
	public ChatUser login(String id, String password) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return null;
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			String selectSql = "SELECT * FROM chat_user WHERE id = ? and password = ?";
			PreparedStatement selectPs = con.prepareStatement(selectSql);
			
			selectPs.setString(1, id);
			selectPs.setString(2, password);
			
			ResultSet rs = selectPs.executeQuery();
			
			if(rs.next()) {
				
				return new ChatUser(rs.getString("id"), rs.getString("password"), rs.getString("name"));
				
			}else {
				return null;
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	// ユーザーの存在確認
	public ChatUser searchUser(String id) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return null;
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			String selectSql = "SELECT * FROM chat_user WHERE id = ?";
			PreparedStatement selectPs = con.prepareStatement(selectSql);
			
			selectPs.setString(1, id);
			
			ResultSet rs = selectPs.executeQuery();
			
			if(rs.next()) {
				
				return new ChatUser(rs.getString("id"), rs.getString("password"), rs.getString("name"));
				
			}else {
				return null;
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	// チャットリストを取得
	public ArrayList<Chat> getChat(String fromId, String toId) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return null;
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			// ユーザーを検索
			String selectUserSql = "SELECT * FROM chat_user WHERE id = ?";
			PreparedStatement selectUserPs = con.prepareStatement(selectUserSql);
			
			selectUserPs.setString(1, toId);
			
			ResultSet rsUser = selectUserPs.executeQuery();
			
			if(rsUser.next()) {
				// 存在している場合の処理
				
				// チャットをデータベースから取得
				String selectChatSql = "SELECT * FROM chat WHERE (from_id = ? and to_id =?) or (from_id = ? and to_id = ?) order by chat_date";
				PreparedStatement selectChatPs = con.prepareStatement(selectChatSql);
				
				selectChatPs.setString(1, fromId);
				selectChatPs.setString(2, toId);
				selectChatPs.setString(3, toId);
				selectChatPs.setString(4, fromId);
				
				ResultSet rsChat = selectChatPs.executeQuery();
				
				ArrayList<Chat> chatList = new ArrayList<>();
				
				while(rsChat.next()) {
					
					Chat chat = new Chat(rsChat.getString("chat_id"), rsChat.getString("from_id"), rsChat.getString("to_id"), rsChat.getString("text"), rsChat.getTimestamp("chat_date"));
					
					chatList.add(chat);
					
				}
				
				return chatList;
				
			} else {
				// 存在していない場合の処理
				
				return null;
				
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	// チャット履歴を取得
	public ArrayList<Chat> getChatLog(String fromId) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return null;
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			// チャットをデータベースから取得
			String selectChatSql = "SELECT * FROM chat WHERE from_id = ? or to_id = ? order by chat_date desc";
			PreparedStatement selectChatPs = con.prepareStatement(selectChatSql);
			
			selectChatPs.setString(1, fromId);
			selectChatPs.setString(2, fromId);
			
			ResultSet rsChat = selectChatPs.executeQuery();
			
			ArrayList<Chat> chatLog = new ArrayList<>();
			ArrayList<String> toIdList = new ArrayList<>();
			String id = new String();
			
			while(rsChat.next()) {
				
				// チャット相手を取得
				if(fromId.equals(rsChat.getString("from_id"))) {
					// fromIdとfrom_idが一致した場合の処理
					
					id = rsChat.getString("to_id");
					
				} else {
					// それ以外の場合の処理
					
					id = rsChat.getString("from_id");
					
				}
				
				if(!toIdList.contains(id)) {
					// チャット相手がチャット履歴にない場合の処理
					
					// チャット履歴に追加
					Chat chat = new Chat(rsChat.getString("chat_id"), rsChat.getString("from_id"), rsChat.getString("to_id"), rsChat.getString("text"), rsChat.getTimestamp("chat_date"));
					chatLog.add(chat);
					
					// チャット相手のリストに追加
					toIdList.add(id);
					
				}
			}
			
			return chatLog;
			
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	// チャットを送信
	public ArrayList<Chat> sendChat(Chat chat) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return null;
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			// チャットをデータベースにinsert
			String insertChatSql = "insert into chat(from_id, to_id, text, chat_date) values(?,?,?,?)";
			PreparedStatement insertChatPs = con.prepareStatement(insertChatSql);
			
			insertChatPs.setString(1, chat.getFromId());
			insertChatPs.setString(2, chat.getToId());
			insertChatPs.setString(3, chat.getText());
			insertChatPs.setTimestamp(4, chat.getDate());
			
			int count = insertChatPs.executeUpdate();
			
			if(count == 0) {
				// insertできなかった場合の処理
				
				return null;
				
			} else {
				// insertできた場合の処理
				
				// チャットをデータベースから取得
				String selectChatSql = "SELECT * FROM chat WHERE (from_id = ? and to_id =?) or (from_id = ? and to_id =?) order by chat_date";
				PreparedStatement selectChatPs = con.prepareStatement(selectChatSql);
				
				selectChatPs.setString(1, chat.getFromId());
				selectChatPs.setString(2, chat.getToId());
				selectChatPs.setString(3, chat.getToId());
				selectChatPs.setString(4, chat.getFromId());
				
				ResultSet rsChat = selectChatPs.executeQuery();
				
				ArrayList<Chat> chatList = new ArrayList<>();
				
				while(rsChat.next()) {
					
					Chat chat2 = new Chat(rsChat.getString("chat_id"), rsChat.getString("from_id"), rsChat.getString("to_id"), rsChat.getString("text"), rsChat.getTimestamp("chat_date"));
					
					chatList.add(chat2);
					
				}
				
				return chatList;
				
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	// ユーザーのIDからニックネームを主六
	public String getName(String id) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return null;
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			String selectSql = "SELECT name FROM chat_user WHERE id = ?";
			PreparedStatement selectPs = con.prepareStatement(selectSql);
			
			selectPs.setString(1, id);
			
			ResultSet rs = selectPs.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("name");
				
			}else {
				return null;
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
}