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
<br>
<form action="SearchMemoServlet" method="post">
メモのタイトルを検索  <input type="text" name="searchMemoTitle">
<input type="submit" value="検索">
</form>
<h2>メモ一覧</h2>
${searchResult}
<br>
<hr>
<br>
<a href="AddMemoServlet">メモを追加</a><br><br>
<a href="TrashMemoServlet?link=trash">削除されたメモ</a><br><br>
<a href="LogoutServlet">ログアウト</a><br><br>
<a href="DeleteAccountServlet?link=deleteCheck">アカウント削除</a>
</body>
</html>