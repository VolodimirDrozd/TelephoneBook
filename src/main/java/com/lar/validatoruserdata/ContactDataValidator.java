package com.lar.validatoruserdata;

public class ContactDataValidator extends DataValidator {

	Field contactType;

	public ContactDataValidator(Field contactType) {
		this.contactType = contactType;
	}

	@Override
	public String getRegexPattern() {
		return contactType.getRegexPattern();
	}

	@Override
	public int getQuantityLeters() {
		return contactType.getQuantityLeters();
	}

	public static enum Field {
		NAME("\\D+", 4), SURNAME("\\D+", 4), PATRONYMIC("\\D+", 4), MOBILEPHONE("[0-9]+", 9), HOMEPHONE(
				"[0-9]+", 9), EMAIL(
						"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
						6);

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
