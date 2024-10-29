<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="userMasterMaintenance/userMasterMaintenance.css">
<title>ユーザーマスターメンテナンス</title>
</head>
<body>
<div class="body">

	<div class="title_label">
		ユーザーマスターメンテナンス
	</div>
	
	<div class="main">
	
		<!-- メッセージ -->
		<div class="msg">
			${msg}
		</div>
	
		<form action="UserMasterMaintenanceServlet" method="post">
		
			<!-- 処理区分の行 -->
			<div class="process_division_block">
			
				<div class="process_division_label">
					処理区分
				</div>
				
				<div class="process_division_colon">
					:
				</div>
				
				<div class="process_division">
					<select class="process_division_list" name="process_division">
						<option value="blank"></option>
						<option value="reference">参照</option>
						<option value="register">登録</option>
						<option value="update">更新</option>
						<option value="delete">削除</option>
					</select>
				</div>
				
			</div>
			
			<!-- ヘッダ.ユーザーIDの行 -->
			<div class="user_id_block">
			
				<div class="user_id_label">
					ユーザーID
				</div>
				
				<div class="user_id_colon">
					:
				</div>
				
				<div class="user_id_form">
					<input type="text" name="user_id_header" value="${user_id_header}">
				</div>
				
				<div class="execute_button_block">
					<button class="execute_button" name="button_action" value="execute">
						実行
					</button>
				</div>
				
				<div class="cancel_button_block">
					<button class="cancel_button" name="button_action" value="cancel">
						キャンセル
					</button>
				</div>
				
			</div>
			
			<!-- ユーザー情報 -->
			<div class="user_info_block">
			
				<div class="user_info_label">
					ユーザー情報
				</div>
				
				<div class="user_info_table_block">
					<table class="user_info_table">
						<tr>
							<td class="td_left">
								ユーザーID
							</td>
							<td class="td_right">
								<input type="text" name="user_id_info" value="${user.getUserId()}">
							</td>
						</tr>
						
						<tr>
							<td class="td_left">
								ユーザー名(漢字)
							</td>
							<td class="td_right">
								<input type="text" name="user_name_k" value="${user.getUserNameK()}">
							</td>
						</tr>
						
						<tr>
							<td class="td_left">
								ユーザー名(英字)
							</td>
							<td class="td_right">
								<input type="text" name="user_name_e" value="${user.getUserNameE()}">
							</td>
						</tr>
						
						<tr>
							<td class="td_left">
								適用開始日
							</td>
							<td class="td_right">
								<input type="text" name="start_date" value="${user.getStartDate()}">
							</td>
						</tr>
						
						<tr>
							<td class="td_left">
								適用終了日
							</td>
							<td class="td_right">
								<input type="text" name="end_date" value="${user.getEndDate()}">
							</td>
						</tr>
					</table>
				</div>
				
			</div>
			
			<!-- 戻るボタン -->
			<div class="back_button_block">
				<button class="back_button" name="button_action" value="back">
					トップに戻る
				</button>
			</div>
	
		</form>
		
	</div>
	
</div>
</body>
</html>