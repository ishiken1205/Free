<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter"%>
<% 
	LocalDate localDate = LocalDate.now();
	String date = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
%>
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
<%= date %><br>
<h2>通帳一覧</h2>
${passbookList}<br>
<br>
<hr>
<br>
<a href="MainServlet?link=createPassbook">通帳の新規作成</a><br><br>
<a href="AccountServlet?link=logout">ログアウト</a><br><br>
<a href="AccountServlet?link=deleteAccount">アカウント削除</a>
</body>
</html>