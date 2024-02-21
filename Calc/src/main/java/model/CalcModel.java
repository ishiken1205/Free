	package model;

public class CalcModel {
	public String execute(int a, int b, String enzanshi) {
		if(enzanshi.equals("plus")) {
			int result = a + b;
			String str = result + "";
			return str;
		}if(enzanshi.equals("minus")) {
			int result = a - b;
			String str = result + "";
			return str;
		}if(enzanshi.equals("times")) {
			int result = a * b;
			String str = result + "";
			return str;
		}if(enzanshi.equals("devide")) {
			int result = a / b;
			int result2 = a % b;
			String str;
			if(result2 != 0) {
				str = result + "あまり" + result2;
				}else{
				str = result + "";
				}
			return str;
		}else {return "エラー";}
	}
}
