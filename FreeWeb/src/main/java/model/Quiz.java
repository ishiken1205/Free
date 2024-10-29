package model;

import java.io.Serializable;

public class Quiz implements Serializable {
	
	private int quizCount;
	private String name,birthday;
	private boolean result;
	
	public Quiz() {}
	
	public Quiz(int quizCount,String name, String birthday, boolean result) {
		this.quizCount = quizCount;
		this.name = name;
		this.birthday = birthday;
		this.result = result;
	}
	
	public int getQuizCount() {return this.quizCount;}
	public String getName() {return this.name;}
	public String getBirthday() {return this.birthday;}
	public boolean getResult() {return this.result;}
	public void setQuizCount(int quizCount) {this.quizCount = quizCount;};
	public void setName(String name) {this.name = name;}
	public void setBirthday(String birthday) {this.birthday = birthday;}
	public void setResult(boolean result) {this.result = result;}
}
