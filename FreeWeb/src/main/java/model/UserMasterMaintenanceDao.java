package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMasterMaintenanceDao {
	
	String db_url = "jdbc:postgresql://localhost/ecwdb";
	String db_user = "postgres";
	String db_pass = "69201610";
	
	// 参照のメソッド
	public User reference(String userIdHeader) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			String sql = "select user_id, user_name_k, user_name_e, start_date, end_date from m_user where user_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userIdHeader);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) {
				// ユーザーが存在しない場合の処理
				return null;
			}
			
			User user = new User(rs.getString("user_id"), rs.getString("user_name_k"), rs.getString("user_name_e"),
					rs.getDate("start_date").toLocalDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
					rs.getDate("end_date").toLocalDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
			return user;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 登録のメソッド
	public String register(User user) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return "JDBC接続に失敗しました。";
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			// ユーザーIDが既に存在しているかを確認
			String selectSql = "select * from m_user where user_id = ?";
			PreparedStatement selectPs = con.prepareStatement(selectSql);
			selectPs.setString(1, user.getUserId());
			ResultSet rs = selectPs.executeQuery();
			
			if(rs.next()) {
				// ユーザーIDが存在していた場合の処理
				return "既に存在するユーザーIDです。";
				
			} else {
				// ユーザーIDが存在していなかった場合の処理
				
				// 適用開始日と適用終了日をString型からsql.Date型へ変換
				Date startDate = Date.valueOf(LocalDate.parse(user.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd")));
				Date endDate = Date.valueOf(LocalDate.parse(user.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd")));
				
				String insertSql = "insert into m_user values(?,?,?,?,?)";
				PreparedStatement insertPs = con.prepareStatement(insertSql);
				insertPs.setString(1, user.getUserId());
				insertPs.setString(2, user.getUserNameK());
				insertPs.setString(3, user.getUserNameE());
				insertPs.setDate(4, startDate);
				insertPs.setDate(5, endDate);
				int count = insertPs.executeUpdate();
				
				if(count == 1) {
					// insertに成功した場合の処理
					return "登録しました。";
				}else {
					// insertに失敗した場合の処理
					return "登録に失敗しました。";
				}
			}
		}
		catch(SQLException e) {
			return "データベース接続に失敗しました。";
		}
	}
	
	// 更新のメソッド
	public String update(String userIdHeader, User user) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return "JDBC接続に失敗しました。";
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			// ユーザーIDに存在しているかを確認
			String selectSql = "select * from m_user where user_id = ?";
			PreparedStatement selectPs = con.prepareStatement(selectSql);
			selectPs.setString(1, userIdHeader);
			ResultSet rs = selectPs.executeQuery();
			
			if(rs.next()) {
				// ユーザーIDが存在していた場合の処理
				
				// 適用開始日と適用終了日をString型からsql.Date型へ変換
				Date startDate = Date.valueOf(LocalDate.parse(user.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd")));
				Date endDate = Date.valueOf(LocalDate.parse(user.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd")));
				
				String updateSql = "update m_user set user_id = ?, user_name_k = ?, user_name_e = ?, start_date = ?, end_date = ? where user_id = ?";
				PreparedStatement updatePs = con.prepareStatement(updateSql);
				updatePs.setString(1, user.getUserId());
				updatePs.setString(2, user.getUserNameK());
				updatePs.setString(3, user.getUserNameE());
				updatePs.setDate(4, startDate);
				updatePs.setDate(5, endDate);
				updatePs.setString(6, userIdHeader);
				int count = updatePs.executeUpdate();
				
				if(count == 1) {
					// updateに成功した場合の処理
					return "更新しました。";
				}else {
					// updateに失敗した場合の処理
					return "更新に失敗しました。";
				}
				
			} else {
				// ユーザーIDが存在していなかった場合の処理
				
				return "入力されたユーザーIDは存在しません。";
				
			}
		}
		catch(SQLException e) {
			return "データベース接続に失敗しました。";
		}
	}
	
	// 削除のメソッド
	public String delete(String userIdHeader) {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			return "JDBC接続に失敗しました。";
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			// ユーザーIDに存在しているかを確認
			String selectSql = "select * from m_user where user_id = ?";
			PreparedStatement selectPs = con.prepareStatement(selectSql);
			selectPs.setString(1, userIdHeader);
			ResultSet rs = selectPs.executeQuery();
			
			if(rs.next()) {
				// ユーザーIDが存在していた場合の処理
				
				String deleteSql = "delete from m_user where user_id = ?";
				PreparedStatement deletePs = con.prepareStatement(deleteSql);
				deletePs.setString(1, userIdHeader);
				int count = deletePs.executeUpdate();
				
				if(count == 1) {
					// deleteに成功した場合の処理
					return "削除しました。";
				} else {
					// deleteに失敗した場合の処理
					return "削除に失敗しました。";
				}
				
			} else {
				// ユーザーIDが存在していなかった場合の処理
				
				return "入力されたユーザーIDは存在しません。";
				
			}
		}
		catch(SQLException e) {
			return "データベース接続に失敗しました。";
		}
	}
}