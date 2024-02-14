<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通帳アプリ</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
<h1>${loginAccount.id}さんの通帳アプリ</h1>
<hr>
<br>
<a href="AccountServlet?link=logout">ログアウト</a><br><br>
<a href="AccountServlet?link=deleteAccount">アカウント削除</a>
</body>
</html>