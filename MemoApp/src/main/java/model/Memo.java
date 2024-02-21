package model;

import java.io.Serializable;

public class Memo implements Serializable {
	private String id,title, memo;
	public Memo() {}
	public String getId() {return this.id;}
	public String getTitle() {return this.title;}
	public String getMemo() {return this.memo;}
	public void setId(String id) {this.id = id;}
	public void setTitle(String title) {this.title = title;}
	public void setMemo(String memo) {this.memo = memo;}
}
