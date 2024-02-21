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
<h1>ログイン</h1>
<hr>
<br>
<form action="LoginServlet" method="post">
<label>ID　</label><input type="text" name="id"><br>
<label>パスワード　</label><input type="password" name="pass"><br>
<label></label><input type="submit" value="ログイン">
</form>
<br>
<hr>
<br>
<a href="TopServlet">トップ画面へ戻る</a>
</body>
</html>