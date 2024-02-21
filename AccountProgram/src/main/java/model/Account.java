package model;

import java.io.Serializable;

public class Account implements Serializable {
	private String id, pass;
	public Account() {}
	public String getId() {return this.id;}
	public String getPass() {return this.pass;}
	public void setId(String id) {this.id = id;}
	public void setPass(String pass) {this.pass = pass;}
}
