<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Quiz"%>
<% ArrayList<Quiz> quizList = (ArrayList<Quiz>)session.getAttribute("quizList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="quiz/quiz.css">
<title>クイズ</title>
</head>
<body>
<div class="quiz_final_result body">
	<div class="title_label">
		K-POPアイドルの誕生日クイズ
		<br>
		(全5問)
	</div>
	
	<div class="final_result">
		全5問中 <div class="true_count">${true_count}</div>問正解！
	</div>
	
	<% for(Quiz quiz : quizList) { %>
		<div class="result_block">
		
			<!-- 正誤 -->
			<div class="result_boolean_block">
				<% if(quiz.getResult()){ %>
					<div class="result_true">
						〇
					</div>
				<% }else { %>
					<div class="result_false">
						×
					</div>
				<% } %>
			</div>
			
			<!-- 何問目か -->
			<div class="quiz_count_block">
				第<%= quiz.getQuizCount() %>問
			</div>
			
			<div class="quiz_block">
				<!-- 問題 -->
				<div class="question_block">
					<%= quiz.getName() %> の誕生日は？
				</div>
				
				<!-- 解答 -->
				<div class="answer_block">
					<%= quiz.getBirthday() %>
				</div>
			</div>
			
		</div>
	<% } %>
	
	<form action="QuizServlet" method="post">
	
		<div class="back_button_block">
			<button class="back_button" name="button_action" value="to_quiz_top">
				戻る
			</button>
		</div>
		
	</form>
</div>
</body>
</html>