<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タイトル</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
<h1>アカウント削除</h1>
${msg}
<hr>
<br>
<form action="AccountServlet?link=deleteAccount" method="post">
パスワードを入力してください<input type="password" name="passCheck">
<input type="submit" value="アカウント削除">
</form>
<br>
<hr>
<br>
<a href="MainServlet?link=main">メイン画面へ戻る</a>
</body>
</html>