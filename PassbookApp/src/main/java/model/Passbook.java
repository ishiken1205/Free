package model;

import java.io.Serializable;

public class Passbook implements Serializable {
	private String id, title;
	private int money;
	public String getId() {return this.id;}
	public String getTitle() {return this.title;}
	public int getMoney() {return this.money;}
	public void setId(String id) {this.id = id;}
	public void setTitle(String title) {this.title = title;}
	public void setMoney(int money) {this.money = money;}
}
