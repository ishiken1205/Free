package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Memo;

public class MemoDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Memo> findByMemo(String id, String title) {
		List<Memo> ml = new ArrayList<Memo>();
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, TITLE, MEMO FROM MEMO WHERE ID = ? AND TITLE LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			pStmt.setString(2, "%" + title + "%");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String id2 = rs.getString("ID");
				String title2 = rs.getString("TITLE");
				String memo = rs.getString("MEMO");
				Memo  m = new Memo();
				m.setId(id2);
				m.setTitle(title2);
				m.setMemo(memo);
				ml.add(m);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ml;
	}
	
	public Memo indicateMemo(String id, String title) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, TITLE, MEMO FROM MEMO WHERE ID = ? AND TITLE = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			pStmt.setString(2, title);
			
			ResultSet rs = pStmt.executeQuery();
			rs.next();
				String id2 = rs.getString("ID");
				String title2 = rs.getString("TITLE");
				String memo = rs.getString("MEMO");
				Memo  m = new Memo();
				m.setId(id2);
				m.setTitle(title2);
				m.setMemo(memo);
				return m;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addMemo(Memo m) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO MEMO VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, m.getId());
			pStmt.setString(2, m.getTitle());
			pStmt.setString(3, m.getMemo());
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public boolean deleteMemo(Memo m) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}	
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM MEMO WHERE ID = ? AND TITLE = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, m.getId());
			pStmt.setString(2, m.getTitle());
			int result = pStmt.executeUpdate();
			if (result != 1) {
			return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public boolean deleteAllMemo(String id) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
			
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM MEMO WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			pStmt.executeUpdate();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
