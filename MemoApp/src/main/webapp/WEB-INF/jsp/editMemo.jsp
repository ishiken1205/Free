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
<h2>メモの編集</h2>
<form action="EditMemoServlet" method="post">
<label>タイトル　</label><input type="text" name="title" value="${indicatedMemo.title}"><br><br>
<label class="naiyou">内容　</label><textarea name="memo" rows="30" cols="60">${indicatedMemo.memo}</textarea>
<input type="submit" value="確定">
</form>
<br>
<hr>
<br>
<a href="LoginServlet?link=main2">メイン画面へ戻る</a><br><br>
</body>
</html>