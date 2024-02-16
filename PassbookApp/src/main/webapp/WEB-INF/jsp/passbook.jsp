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
<%= date %>
<h2>${passbook.title} 残高[${passbook.money}円]</h2>
<br>
<br>
<hr>
<br>
<a href="MainServlet?link=main">メイン画面へ戻る</a></body>
</html>