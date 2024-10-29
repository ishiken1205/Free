package model;

import java.io.Serializable;

public class ChatUser implements Serializable{
	
	private String id, password, name;
	
	public ChatUser() {}
	
	public ChatUser(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	public String getId() {return this.id;}
	public String getPassword() {return this.password;}
	public String getName() {return this.name;}
	public void setId(String id) {this.id = id;}
	public void setPassword(String password) {this.password = password;}
	public void setName(String name) {this.name = name;}
	
}
