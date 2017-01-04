package com.lar.validatoruserdata;

public class UserDataValidator extends DataValidator {

	Field userType;

	public UserDataValidator(Field userType) {
		this.userType = userType;
	}

	public String getRegexPattern() {
		return userType.getRegexPattern();
	}

	public int getQuantityLeters() {
		return userType.getQuantityLeters();
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
