<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>計算アプリ</title>
</head>
<body>
<h1>計算アプリ</h1>
<form action="Calc" method="post">
<input type="number" name="数値A">
<select name="enzanshi">
<option value="plus">+</option>
<option value="minus">-</option>
<option value="times">*</option>
<option value="devide">/</option>
</select>
<input type="number" name="数値B"><br>
<input type="submit" value="計算">
</form>
</body>
</html>