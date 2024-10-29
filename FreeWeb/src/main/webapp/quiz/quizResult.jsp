<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Quiz"%>
<% Quiz quiz = (Quiz)session.getAttribute("quiz"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="quiz/quiz.css">
<title>クイズ</title>
</head>
<body>
<div class="quiz_result body">
	<div class="title_label">
		K-POPアイドルの誕生日クイズ
		<br>
		(全5問)
	</div>
	<form action="QuizServlet" method="post">
		<div class="quiz_count_block">
			第${quiz.getQuizCount()}問
		</div>
		
		<div class="question_block">
			${quiz.getName()}
			<br>
			の誕生日は？
		</div>
		
		<div class="answer_block">
			${quiz.getBirthday()}
		</div>
		
		<div class="true_msg_block">
			${true_msg}
		</div>
		
		<div class="false_msg_block">
			${false_msg}
		</div>
		
		<% if(quiz.getQuizCount() == 5) { %>
			<div class="final_result_button_block">
				<button class="final_result_button" name="button_action" value="final_result">
					結果を見る
				</button>
			</div>
		<% } else { %>
			<div class="next_quiz_button_block">
				<button class="next_quiz_button" name="button_action" value="next_quiz">
					次の問題へ
				</button>
			</div>
		<% } %>
		
		<div class="back_button_block">
			<button class="back_button" name="button_action" value="to_quiz_top">
				戻る
			</button>
		</div>
		
	</form>
</div>
</body>
</html>