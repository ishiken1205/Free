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

<script>
	window.onload = function() {

    	// 現在のタイマーの時間をビューに反映
		document.getElementById('timer').innerHTML = "${hour}:${minute}:${second}";

}
</script>

<div class="body">
	<div class="title_label">
		タイマー
	</div>
	
	<div class="time_main" id="timer">
	</div>
	
	<form action="TimerServlet" method="post">
		
		<div class="timer_button_block">
			<div class="start_button_block">
				<button type="button" class="start_button" id="start_button" onclick="startTimerFirst(${hour}, ${minute}, ${second})">スタート</button>
			</div>
			
			<div class="reset_button_block">
				<button type="submit" class="reset_button" name="button_action" value="reset">リセット</button>
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