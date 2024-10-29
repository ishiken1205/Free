<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ChatUser"%>
<%@ page import="model.Chat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Timestamp"%>
<% ChatUser user = (ChatUser)session.getAttribute("user"); %>
<% ChatUser toUser = (ChatUser)session.getAttribute("toUser"); %>
<% ArrayList<Chat> chatList = (ArrayList<Chat>)request.getAttribute("chatList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="chat/chat.css">
<title>チャット画面</title>
</head>
<body>
<script>
	window.onload = function() {
	    const scrollableDiv = document.getElementById('scroll');
	    scrollableDiv.scrollTop = scrollableDiv.scrollHeight;
	};
</script>
<div class="body">
	<div class="title_label">
		チャット
	</div>
	
	<div class="subtitle_label">
		<%= user.getName() %>さんでログイン中
	</div>
	
	<form action="ChatServlet" method="post">
		
		<div class="to_my_page_button_block">
			<button class="to_my_page_button" name="button_action" value="toMyPage">
				マイページへ
			</button>
			
			<button class="logout_button" name="button_action" value="logout">
				ログアウト
			</button>
		</div>
		
		<hr class="line">
		
		<div class="to_user_name_label">
			<%= toUser.getName() %>さんとのチャット
		</div>
			
		<% String chatDate = new String(); %>
		
		<% if(chatList == null || chatList.size() == 0) { %>
			<div class="no_chat">
				まだチャットはありません
			</div>
		<% } else{ %>
		
			<div class="chat_scroll" id="scroll">
			
				<% for(Chat chat : chatList) { %>
					
					<% if(!chatDate.equals(chat.getDate().toString().substring(0, 10))) { %>
					
						<% chatDate = chat.getDate().toString().substring(0, 10); %>
						<div class="chat_date_block">
							<div class="chat_date">
								<%= chatDate %>
							</div>
						</div>
					
					<% } %>
					
					<% if(chat.getFromId().equals(user.getId())) { %>
						<div class="my_text_block">
							<div class="my_date"><%= chat.getDate().toString().substring(11, 16) %></div>
							<div class="my_text"><%= chat.getText() %></div>
						</div>
					<% }else { %>
						<div class="your_text_block">
							<div class="your_text"><%= chat.getText() %></div>
							<div class="your_date"><%= chat.getDate().toString().substring(11, 16) %></div>
						</div>
					<% } %>
				<% } %>
			</div>
		<% } %>
		
		<div class="msg_block_chat">
			${msg}
		</div>
		
		<textarea name="send_text" class="send_text"></textarea>
		
		<div class="send_chat_button_block">
			<button class="send_chat_button" name="button_action" value="send_chat">
				送信
			</button>
		</div>
		
	</form>
</div>
</body>
</html>