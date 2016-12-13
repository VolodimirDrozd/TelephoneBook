package com.lardi.validator;

import org.springframework.stereotype.Component;

@Component
public class TelephoneValidator {

	public String createNumber(String telephoneNumber) {
		char[] tNumber = telephoneNumber.toCharArray();
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("+380(");
		for (int i = 0; i < tNumber.length; i++) {
			if (i == 03) {
				strBuilder.append(")");
			}
			strBuilder.append(tNumber[i]);
		}
		return strBuilder.toString();
	}

	public boolean checkNumber(String telephoneNumber) {
		if (!telephoneNumber.toString().matches(".*\\d+.*") && telephoneNumber.toCharArray().length != 9
				&& telephoneNumber.toCharArray()[0] != 0) {
			return false;
		}
		return true;
	}
}
