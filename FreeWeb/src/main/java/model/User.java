package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private String userId, userNameK, userNameE, startDate, endDate;
	
	public User() {}
	
	public User(String userId, String userNameK, String userNameE, String startDate, String endDate) {
		
		this.userId = userId;
		this.userNameK = userNameK;
		this.userNameE = userNameE;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}
	
	public String getUserId() {return this.userId;}
	public String getUserNameK() {return this.userNameK;}
	public String getUserNameE() {return this.userNameE;}
	public String getStartDate() {return this.startDate;}
	public String getEndDate() {return this.endDate;}
	public void setUserId(String userId) {this.userId = userId;}
	public void setUserNameK(String userNameK) {this.userNameK = userNameK;}
	public void setUserNameE(String userNameE) {this.userNameE = userNameE;}
	public void setStartDate(String startDate) {this.startDate = startDate;}
	public void setEndDate(String endDate) {this.endDate = endDate;}
}
