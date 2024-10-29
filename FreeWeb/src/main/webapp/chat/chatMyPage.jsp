<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ChatUser"%>
<%@ page import="model.Chat"%>
<%@ page import="model.ChatDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Timestamp"%>
<% ChatUser user = (ChatUser)session.getAttribute("user"); %>
<% ArrayList<Chat> chatLog = (ArrayList<Chat>)request.getAttribute("chatLog"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="chat/chat.css">
<title>ログイン画面</title>
</head>
<body>
<script>
	function toChatPage(toId) {
		document.getElementById('chat_log_to_id').setAttribute('value',toId);
		document.getElementById('chatForm').submit();
	}
</script>
<div class="body">
	<div class="title_label">
		チャット
	</div>
	
	<div class="subtitle_label">
		${ user.getName() }さんでログイン中
	</div>
	
	<form action="ChatServlet" method="post" id="chatForm">
		
		<div class="logout_button_block">
			<button class="logout_button" name="button_action" value="logout">
				ログアウト
			</button>
		</div>
		
		<hr class="line">
		
		<div class="search_user_label">
			ユーザーを検索
		</div>
		
		<div class="msg_block_my_page">
			${msg}
		</div>
		
		<div class="search_user_id_block">
		
			<div class="search_user_id_label">
				ID
			</div>
			
			<div class="search_user_colon">
				:
			</div>
			
			<input type="text" name="search_user_id" class="search_user_id">
			
			<button class="search_user_button" name="button_action" value="search_user">
				検索
			</button>
			
		</div>
		
		<hr class="line">
		
		<div class="chat_log_label">
			チャット履歴
		</div>
		
		<% if(chatLog == null || chatLog.size() == 0) { %>
			<div class="no_chat">
				まだチャットはありません
			</div>
		<% } else{ %>
		
			<div class="chat_log_scroll">
		
				<% String toId = new String(); %>
				<% int chatLogSize = chatLog.size(); %>
				<% int count = 0; %>
				
				<% for(Chat chat : chatLog) { %>
					
					<!-- チャット相手のIDを取得 -->
					<% if(user.getId().equals(chat.getFromId())) { %>
							
						<% toId = chat.getToId(); %>
							
					<% } else { %>
							
						<% toId = chat.getFromId(); %>
							
					<% } %>
					
					<!-- チャット相手のニックネームを取得して表示 -->
					
					<% ChatDao dao = new ChatDao(); %>
					
					<div class="chat_log_block" onclick="toChatPage('<%= toId %>')">
						
						<div class="chat_log_name">
							<%= dao.getName(toId) %>
						</div>
						
						<div class="chat_log_info">
							
							<div class="chat_log_text">
								<%= dao.getName(chat.getFromId()) %> : <%= chat.getText() %>
							</div>
							
							<div class="chat_log_date">
								<%= chat.getDate().toString().substring(0, 10) %>
								<%= chat.getDate().toString().substring(11, 16) %>
							</div>
						
						</div>
						
						<input type="hidden" name="chat_log_to_id" id="chat_log_to_id">
						<input type="hidden" name="button_action" value="toChatPage">
						
					</div>
					
					<% count++; %>
					
					<% if(!(chatLogSize == count)) { %>
						<hr class="line">
					<% } %>
					
				<% } %>
			</div>
		<% } %>
	</form>
</div>
</body>
</html>