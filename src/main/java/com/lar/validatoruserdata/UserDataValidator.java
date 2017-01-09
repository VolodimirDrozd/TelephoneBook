package com.lar.validatoruserdata;

import org.springframework.stereotype.Component;

import com.lar.entity.User;

@Component
public class UserDataValidator extends DataValidator {

	private Field userType;

	private UserDataValidator(){}
	
	public UserDataValidator(Field userType) {
		this.userType = userType;
	}

	public String getRegexPattern() {
		return userType.getRegexPattern();
	}

	public int getQuantityLeters() {
		return userType.getQuantityLeters();
	}

	public boolean validate(User user) throws Exception {
		if (new UserDataValidator(Field.LOGIN).validatorTitle(user.getLogin()))
			throw new Exception("Doesn't validate user login");
		if (new UserDataValidator(Field.PASSWORD).validatorTitle(user.getPassword()))
			throw new Exception("Doesn't validate user password");
		if (new UserDataValidator(Field.NAME).validatorTitle(user.getName()))
			throw new Exception("Doesn't validate user name");
		if (new UserDataValidator(Field.PATRONYMIC).validatorTitle(user.getPatronymic()))
			throw new Exception("Doesn't validate user patronymik");
		if (new UserDataValidator(Field.SURNAME).validatorTitle(user.getSurname()))
			throw new Exception("Doesn't validate user surname");
		return true;
	}

	public static enum Field {
		NAME("\\D+", 5), SURNAME("\\D+", 5), PATRONYMIC("\\D+", 5), LOGIN("[a-zA-Z0-9]*", 3), PASSWORD("[a-zA-Z0-9]*",
				5);
		private String regexPattern;
		private int quantityLeters;

		Field(String regexPattern, int quantityLeters) {
			this.regexPattern = regexPattern;
			this.quantityLeters = quantityLeters;
		}

		public String getRegexPattern() {
			return regexPattern;
		}

		public int getQuantityLeters() {
			return quantityLeters;
		}
	}

}
