package com.lar.validator;

import org.springframework.stereotype.Component;

@Component
public class TelephoneValidator {

	public String createNumber(String telephoneNumber) {
		StringBuilder phone = new StringBuilder(telephoneNumber);
		phone.append("+380(").append(telephoneNumber);
		phone.insert(5, ")");
		return phone.toString();
	}

	public boolean checkNumber(String telephoneNumber) {
		return !telephoneNumber.toString().matches(".*\\d+.*") && telephoneNumber.toCharArray().length != 9
				&& telephoneNumber.toCharArray()[0] != 0 ;
	}
}
