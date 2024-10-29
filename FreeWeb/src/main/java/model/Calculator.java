package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
	public BigDecimal execute(BigDecimal num1, BigDecimal num2, String operator, String display_flag) {
		if(display_flag.equals("2")) {
			if(operator.equals("plus")) {
				num1 = num1.add(num2);
			}else if(operator.equals("minus")) {
				num1 = num1.subtract(num2);
			}else if(operator.equals("multiply")) {
				num1 = num1.multiply(num2);
			}else if(operator.equals("divide")) {
				try {
					num1 = num1.divide(num2);
				}catch (Exception e) {
					num1 = num1.divide(num2, 10, RoundingMode.HALF_UP);
				}
			}
		}
		return num1;
	}
}
