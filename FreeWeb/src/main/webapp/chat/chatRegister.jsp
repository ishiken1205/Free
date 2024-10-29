<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="chat/chat.css">
<title>新規登録画面</title>
</head>
<body>
<div class="body">
	<div class="title_label">
		チャット
	</div>
	
	<div class="subtitle_label">
		新規登録画面
	</div>
	
	<form action="ChatServlet" method="post">
	
		<div class="msg_block">
			${ msg }
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
		
		<div class="name_block">
		
			<div class="name_label">
				ニックネーム
			</div>
			
			<div class="colon">
				:
			</div>
			
			<input type="text" name="name" class="login_form">
			
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
		
		<div class="password_check_block">
		
			<div class="password_check_label">
				パスワード(確認用)
			</div>
			
			<div class="colon">
				:
			</div>
			
			<input type="password" name="password_check" class="login_form">
			
		</div>
		
		<div class="register_button_block">
			<button class="register_button" name="button_action" value="register">
				登録
			</button>
		</div>
	
		<div class="to_login_button_block">
			<button class="to_login_button" name="button_action" value="to_login">
				ログイン画面へ
			</button>
		</div>
		
	</form>
</div>
</body>
</html>