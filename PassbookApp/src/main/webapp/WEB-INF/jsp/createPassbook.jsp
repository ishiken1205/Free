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
<h2>通帳の新規作成</h2>
<hr>
<br>
<%= date %>
<br><br>
<form action="PassbookServlet?link=createPassbook" method="post">
<label>	タイトル</label><input type="text" name="title"><br>
<label>初期金額</label><input type="number" name="money">円
<input type="submit" value="作成">
</form><br>
<br>
<hr>
<br>
<a href="MainServlet?link=main">メイン画面へ戻る</a></body>
</html>