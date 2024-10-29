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
<div class="quiz_top body">
	<div class="title_label">
		K-POPアイドルの誕生日クイズ
		<br>
		(全5問)
	</div>
	<form action="QuizServlet" method="post">
		<div class="start_button_block">
			<button class="start_button" name="button_action" value="quiz_start">
				スタート
			</button>
		</div>
		
		<div class="back_button_block">
			<button class="back_button" name="button_action" value="to_top">
				トップに戻る
			</button>
		</div>
		
	</form>
</div>
</body>
</html>