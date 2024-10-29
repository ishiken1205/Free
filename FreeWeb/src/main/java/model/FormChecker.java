package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormChecker {
	public String execute(String userIdInfo, String userNameK, 
			String userNameE, String startDate, String endDate) {
		
		if(userIdInfo.equals("")) {
			return "ユーザーIDが入力されていません。";
		} else if(userIdInfo.length() > 10) {
			return "ユーザーIDは10文字以内で入力してください。";
		} else if(userNameK.equals("")) {
			return "ユーザー名(漢字)が入力されていません。";
		} else if(userNameK.length() > 20) {
			return "ユーザー名(漢字)は20文字以内で入力してください。";
		} else if(userNameE.equals("")) {
			return "ユーザー名(英字)が入力されていません。";
		} else if(userNameE.length() > 40) {
			return "ユーザー名(英字)は40文字以内で入力してください。";
		} else if(!userNameE.matches("^[a-zA-Z ]+$")) {
			return "ユーザー名(英字)は半角英字と半角スペースのみ入力してください。";
		} else if(startDate.equals("")) {
			return "適用開始日が入力されていません。";
		} else if(startDate.length() != 8) {
			return "適用開始日はyyyyMMdd形式で入力してください。";
		} else if(endDate.equals("")) {
			return "適用終了日が入力されていません。";
		} else if(endDate.length() != 8) {
			return "適用終了日はyyyyMMdd形式で入力してください。";
		}
		
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = sdFormat.parse(startDate);
		} catch(Exception e) {
			return "適用開始日はyyyyMMdd形式で入力してください。";
		}
		
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = sdFormat.parse(endDate);
		} catch(Exception e) {
			return "適用終了日はyyyyMMdd形式で入力してください。";
		}
		
		return null;
	}
	
	public String executeChatUser(String id, String password, String passwordCheck, String name) {
		
		if(id == null || id.trim().equals("")) {
			return "IDが入力されていません";
		} else if(id.trim().length() > 20) {
			return "IDは20文字以内で入力してください";
		} else if(name == null || name.trim().equals("")) {
			return "ニックネームが入力されていません";
		} else if(name.trim().length() > 20) {
			return "ニックネームは20文字以内で入力してください";
		} else if(password == null || password.trim().equals("")) {
			return "パスワードが入力されていません";
		} else if(password.trim().length() > 20) {
			return "パスワードは20文字以内で入力してください";
		} else if(!password.equals(passwordCheck)) {
			return "パスワード(確認用)が異なります";
		}
		
		return null;
	}
}
