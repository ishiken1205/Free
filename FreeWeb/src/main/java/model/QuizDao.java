package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDao {
	
	String db_url = "jdbc:postgresql://localhost/ecwdb";
	String db_user = "postgres";
	String db_pass = "69201610";
	
	// 作問のメソッド
	public ArrayList<Quiz> makeQuiz() {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con = DriverManager.getConnection(db_url, db_user, db_pass)) {
			
			// データベースからランダムに5問を取得
			String sql ="select name, birthday from birthday order by random() limit 5";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			// 取得した問題をArrayListに格納
			ArrayList<Quiz> quizList = new ArrayList<>();
			
			int i = 1;
			
			while(rs.next()) {
				String str = rs.getDate("birthday").toString();
				String birthday = str.substring(0,4) + "年 " + str.substring(5,7) + "月 " + str.substring(8,10) + "日";
				Quiz quiz = new Quiz(i,rs.getString("name"),birthday, true);
				i++;
				quizList.add(quiz);
			}
			return quizList;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}