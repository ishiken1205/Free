package model;

public class QuizLogic {
	public boolean execute(Quiz quiz, String answerYear, String answerMonth, String answerDay) {
		
		// 入力された解答が1桁だった場合の処理
		if(answerMonth.length() == 1) {
			answerMonth = "0" + answerMonth;
		}
		
		if(answerDay.length() == 1) {
			answerDay = "0" + answerDay;
		}
		
		// 入力された解答をyyyy-MM-dd形式へ変換
		String answer = answerYear + "年 " + answerMonth + "月 " + answerDay + "日";
		
		// 正誤の結果を返却
		return answer.equals(quiz.getBirthday());
	}
}
