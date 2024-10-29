package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Chat implements Serializable{
	
	private String chatId, fromId, toId, text;
	private Timestamp date;
	
	public Chat() {}
	
	public Chat(String chatId, String fromId, String toId, String text, Timestamp date) {
		this.chatId = chatId;
		this.fromId = fromId;
		this.toId = toId;
		this.text = text;
		this.date = date;
	}
	
	public String getChatId() {return this.chatId;}
	public String getFromId() {return this.fromId;}
	public String getToId() {return this.toId;}
	public String getText() {return this.text;}
	public Timestamp getDate() {return this.date;}
	public void setChatId(String chatId) {this.chatId = chatId;}
	public void setFromId(String fromId) {this.fromId = fromId;}
	public void setToId(String toId) {this.toId = toId;}
	public void setText(String text) {this.text = text;}
	public void setDate(Timestamp date) {this.date = date;}
	
}
