package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.Account;
import model.Passbook;

public class PassbookDAO {
	
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public String findByPassbookList(Account account) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, TITLE FROM PASSBOOKLIST WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getId());
			ResultSet rs = pStmt.executeQuery();
		String passbookList = "";
			while(rs.next()) {
				passbookList += "<a class =\"initial\" href=\"PassbookServlet?title=" + rs.getString("TITLE") + "\">" + rs.getString("TITLE") + "</a><br>";
			}
			return passbookList;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean createPassbook(Passbook passbook) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String tableName = passbook.getId() + "_" + passbook.getTitle();
			String sql1 = "CREATE TABLE " + tableName  + " (ID INTEGER AUTO_INCREMENT, DATE DATE, MONEY INTEGER, PURPOSE VARCHAR(100), PRIMARY KEY(ID))";
			PreparedStatement pStmt1 = conn.prepareStatement(sql1);
			pStmt1.executeUpdate();
			
			String sql2 = "INSERT INTO " + tableName  + " (DATE, MONEY, PURPOSE) VALUES(?, ?, '初期作成')";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			LocalDate localDate = LocalDate.now();
			String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			pStmt2.setString(1, date);
			pStmt2.setInt(2, passbook.getMoney());
			int result1 = pStmt2.executeUpdate();
			
			String sql3 = "INSERT INTO PASSBOOKLIST VALUES(?, ?)";
			PreparedStatement pStmt3 = conn.prepareStatement(sql3);
			pStmt3.setString(1, passbook.getId());
			pStmt3.setString(2, passbook.getTitle());
			int result2 = pStmt3.executeUpdate();
			if(result1 == 1 && result2 == 1) {
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
	
	public String indicatePassbook(Passbook passbook) {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String tableName = passbook.getId() + "_" + passbook.getTitle();
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			String str = "<table border=\"1\"><tr><th>日付</th><th>金額</th><th>詳細</th></tr>";
			while(rs.next()) {
				String date = new SimpleDateFormat("yyyy/MM/dd").format(rs.getDate("DATE"));
				int money = rs.getInt("MONEY");
				String purpose = rs.getString("PURPOSE");
				str += "<tr><td>" + date + "</td><td>" + money + "</td><td>" + purpose + "</td></tr>";
			}
			str += "</table>";
			return str;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
