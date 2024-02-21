<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモアプリ</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
<h1>アカウント登録</h1>
<hr>
<br>
<form action="RegisterAccountServlet" method="post">
<label class="register">ID　</label><input type="text" name="id"><br>
<label class="register">パスワード　</label><input type="password" name="pass"><br>
<label class="register">パスワード(確認用)　</label><input type="password" name="pass2"><br>
<label class="register"></label><input type="submit" value="登録">
</form>
<br>
<hr>
<br>
<a href="TopServlet">トップ画面へ戻る</a>
</body>
</html>