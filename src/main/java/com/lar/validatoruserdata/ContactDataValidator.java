package com.lar.validatoruserdata;

import org.springframework.stereotype.Component;

import com.lar.entity.Contact;

@Component
public class ContactDataValidator extends DataValidator {

	private Field contactType;

	private ContactDataValidator() {
	}

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

	public boolean validate(Contact contact) throws Exception {
		if (!new ContactDataValidator(Field.NAME).validatorTitle(contact.getName()))
			throw new Exception("Doesn't validate contact name");
		if (!new ContactDataValidator(Field.PATRONYMIC).validatorTitle(contact.getPatronymic()))
			throw new Exception("Doesn't validate contact patronymik");
		if (!new ContactDataValidator(Field.SURNAME).validatorTitle(contact.getSurname()))
			throw new Exception("Doesn't validate user surname");
		if (!new ContactDataValidator(Field.MOBILEPHONE).validatorTitle(contact.getMobilePhone()))
			throw new Exception("Doesn't validate contat mobile phone");
		if (!new ContactDataValidator(Field.HOMEPHONE).validatorTitle(contact.getHomePhone()))
			throw new Exception("Doesn't validate user mobile phone");
		return true;
	}

	public String createNumber(String telephoneNumber) {
		StringBuilder phone = new StringBuilder();
		phone.append("+380(").append(telephoneNumber);
		phone.insert(8, ")");
		return phone.toString();
	}

	public static enum Field {
		NAME("\\D+", 4), SURNAME("\\D+", 4), PATRONYMIC("\\D+", 4), MOBILEPHONE("[0-9]+", 9), HOMEPHONE("[0-9]+",
				9), EMAIL(
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
