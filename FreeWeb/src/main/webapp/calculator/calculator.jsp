<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String cancel_flag = (String)session.getAttribute("cancel_flag"); %>
<% if(cancel_flag == null) {cancel_flag = "1";} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="calculator/calculator.css">
<script src="calculator/calculator.js"></script>
<title>電卓</title>
</head>
<body>
<div class="main_body">
	<div class="result">${display_num}</div>
	<form id="calcForm" action="CalculatorServlet" method="post">
		<input type="hidden" id="hid_param" name="hid_param">
		<div class="row_1">
		
		<% if(cancel_flag.equals("1")) { %>
			<button class="button_type3" type="button" onclick="setParam('all_cancel')">AC</button>
		<% } else{ %>
			<button class="button_type3" type="button" onclick="setParam('cancel')">C</button>
		<% } %>
			<button class="button_type3" type="button" onclick="setParam('plus_minus')">±</button>
			<button class="button_type3" type="button" onclick="setParam('percent')">%</button>
			<button class="button_type4" type="button" onclick="setParam('divide')">÷</button>
		</div>
		<div class="row_2">
			<button class="button_type1" type="button" onclick="setParam(7)">7</button>
			<button class="button_type1" type="button" onclick="setParam(8)">8</button>
			<button class="button_type1" type="button" onclick="setParam(9)">9</button>
			<button class="button_type4" type="button" onclick="setParam('multiply')">×</button>
		</div>
		<div class="row_3">
			<button class="button_type1" type="button" onclick="setParam(4)">4</button>
			<button class="button_type1" type="button" onclick="setParam(5)">5</button>
			<button class="button_type1" type="button" onclick="setParam(6)">6</button>
			<button class="button_type4" type="button" onclick="setParam('minus')">－</button>
		</div>
		<div class="row_4">
			<button class="button_type1" type="button" onclick="setParam(1)">1</button>
			<button class="button_type1" type="button" onclick="setParam(2)">2</button>
			<button class="button_type1" type="button" onclick="setParam(3)">3</button>
			<button class="button_type4" type="button" onclick="setParam('plus')">＋</button>
		</div>
		<div class="row_5">
			<button class="button_type2" type="button" onclick="setParam(0)">0</button>
			<button class="button_type1" type="button" onclick="setParam('comma')">.</button>
			<button class="button_type4" type="button" onclick="setParam('equal')">＝</button>
		</div>
	</form>
	
	<form action="TopServlet" method="get">
		<div class="back_button_block">
			<button class="back_button">
				トップに戻る
			</button>
		</div>
	</form>
	
</div>
</body>
</html>