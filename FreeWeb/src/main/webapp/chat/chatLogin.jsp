<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="chat/chat.css">
<title>ログイン画面</title>
</head>
<body>
<div class="body">
	<div class="title_label">
		チャット
	</div>
	
	<div class="subtitle_label">
		ログイン画面
	</div>
	
	<form action="ChatServlet" method="post">
	
	<div class="msg_block">
		${msg}
	</div>
	
		<div class="id_block">
		
			<div class="id_label">
				ID
			</div>
			
			<div class="colon">
				:
			</div>
			
			<input type="text" name="id" class="login_form">
			
		</div>
		
		<div class="password_block">
		
			<div class="password_label">
				パスワード
			</div>
			
			<div class="colon">
				:
			</div>
			
			<input type="password" name="password" class="login_form">
			
		</div>
		
		<div class="login_button_block">
			<button class="login_button" name="button_action" value="login">
				ログイン
			</button>
		</div>
		
		<div class="to_register_button_block">
			<button class="to_register_button" name="button_action" value="to_register">
				新規登録
			</button>
		</div>
	
		<div class="back_button_block">
			<button class="back_button" name="button_action" value="back">
				トップに戻る
			</button>
		</div>
		
	</form>
</div>
</body>
</html>