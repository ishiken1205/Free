<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="top.css">
<title>トップページ</title>
</head>
<body>
<div class="body">

	<div class="top_label">
		ウェブアプリケーション選択
	</div>
	
	<div class="main">
	
		<form action="TopServlet" method="post">
		
			<div class="button_block">
				<button class="button" name="button_action" value="user_master_maintenance">
					ユーザーマスターメンテナンス
				</button>
			</div>
			
			<div class="button_block">
				<button class="button" name="button_action" value="calculator">
					電卓
				</button>
			</div>
			
			<div class="button_block">
				<button class="button" name="button_action" value="quiz">
					クイズ
				</button>
			</div>
			
			<div class="button_block">
				<button class="button" name="button_action" value="timer">
					タイマー
				</button>
			</div>
			
			<div class="button_block">
				<button class="button" name="button_action" value="chat">
					チャット
				</button>
			</div>
			
		</form>
	
	</div>
</div>
</body>
</html>