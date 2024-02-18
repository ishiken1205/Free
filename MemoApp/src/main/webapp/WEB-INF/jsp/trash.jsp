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
<h2>削除されたメモ</h2>
${trashMemo}
<br>
<a href="LoginServlet?link=main3">メイン画面へ戻る</a>
</body>
</html>