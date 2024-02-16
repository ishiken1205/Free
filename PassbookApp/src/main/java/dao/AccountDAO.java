package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public boolean findByAccount(Account account) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, PASS FROM ACCOUNT WHERE ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getId());
			pStmt.setString(2, account.getPass());
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean registerAccount(Account account) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO ACCOUNT VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getId());
			pStmt.setString(2, account.getPass());
			int result = pStmt.executeUpdate();
		      if (result != 1) {
		          return false;
		      }
		} catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		}
		return true;
	}
	
	public boolean deleteAccount(Account account) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM ACCOUNT WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getId());
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}else {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
