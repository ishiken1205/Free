<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String result = (String)request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>計算アプリ</title>
</head>
<body>
<h1>計算結果</h1>
<p><%= result %></p>
<a href="Calc">戻る</a>
</body>
</html>