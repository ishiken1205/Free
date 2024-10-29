<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="quiz/quiz.css">
<title>クイズ</title>
</head>
<body>
<div class="quiz_question body">
	<div class="title_label">
		K-POPアイドルの誕生日クイズ
		<br>
		(全5問)
	</div>
	<form action="QuizServlet" method="post">
		<div class="quiz_count_block">
			第${quiz.quizCount}問
		</div>
		
		<div class="question_block">
			${quiz.name}
			<br>
			の誕生日は？
		</div>
		
		<div class="answer_input_block">
			<input type="number" name="answer_year" class="input_form_year input_form">
			年
			<input type="number" name="answer_month" class="input_form_month input_form">
			月
			<input type="number" name="answer_day" class="input_form_day input_form">
			日
		</div>
		
		<div class="answer_button_block">
			<button class="answer_button" name="button_action" value="answer">
				解答する
			</button>
		</div>
		
		<div class="back_button_block">
			<button class="back_button" name="button_action" value="to_quiz_top">
				戻る
			</button>
		</div>
		
	</form>
</div>
</body>
</html>