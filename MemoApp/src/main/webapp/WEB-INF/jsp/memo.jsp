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
<h1>${id}さんのメモアプリ</h1>
<hr>
<h2>${indicatedMemo.title}</h2>
<p>${indicatedMemo.memo}</p>
<hr>
<br>
<a href="LoginServlet?link=main2">メイン画面へ戻る</a><br><br>
<a href="EditMemoServlet">このメモを編集する</a><br><br>
<a href="TrashMemoServlet?link=del">このメモを削除する</a>
</body>
</html>