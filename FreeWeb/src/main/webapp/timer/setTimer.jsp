<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="timer/timer.css">
<script src="timer/timer.js"></script>
<title>タイマー</title>
</head>
<body>
<div class="body">
	<div class="title_label">
		タイマー
	</div>
	
	<div class="set_timer_label">
		タイマーをセットしてください
	</div>
	
	<div class="msg_block">
		${msg}
	</div>
	
	<form action="TimerServlet" method="post">
		<div class="set_timer_form_block">
			<input type="number"  class="set_timer_form" name="hour" value="00">
			<div class="set_timer_unit_hour">
				(h)
			</div>
			
			<input type="number"  class="set_timer_form" name="minute" value="00">
			<div class="set_timer_unit_minute">
				(m)
			</div>
			
			<input type="number"  class="set_timer_form" name="second" value="00">
			<div class="set_timer_unit_second">
				(s)
			</div>
			
			<div class="set_button_block">
				<button type="submit" class="set_button" name="button_action" value="set">セット</button>
			</div>
		</div>
	</form>
	
	
	<form action="TopServlet" method="get">
		<div class="back_button_block">
			<button class="back_button">
				トップに戻る
			</button>
		</div>
	</form>
</div>
</body>
</html>