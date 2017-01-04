package com.lar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

	@Id
	private Long id;
	private Long userId;
	private String name;
	private String surname;
	private String patronymic;
	private String mobilePhone;
	private String homePhone;
	private String address;
	private String email;

	public static ContactBuilder builder() {
		return new ContactBuilder();
	}

	public static class ContactBuilder {
		private Long id;
		private Long userId;
		private String name;
		private String surname;
		private String patronymic;
		private String mobilePhone;
		private String homePhone;
		private String address;
		private String email;

		public ContactBuilder() {
		}


		public ContactBuilder id(long setId) {
			id = setId;
			return this;
		}

		public ContactBuilder userId(long setUserId) {
			userId = setUserId;
			return this;
		}

		public ContactBuilder name(String setName) {
			name = setName;
			return this;
		}

		public ContactBuilder surname(String setSurname) {
			surname = setSurname;
			return this;
		}

		public ContactBuilder patronymic(String setPatronymic) {
			patronymic = setPatronymic;
			return this;
		}

		public ContactBuilder mobilePhone(String setMobilePhone) {
			mobilePhone = setMobilePhone;
			return this;
		}

		public ContactBuilder homePhone(String setHomePhone) {
			homePhone = setHomePhone;
			return this;
		}

		public ContactBuilder address(String setAddress) {
			address = setAddress;
			return this;
		}

		public ContactBuilder email(String setEmail) {
			email = setEmail;
			return this;
		}

		public Contact build() {
			Contact contact = new Contact();
			contact.setId(id);
			contact.setName(name);
			contact.setSurname(surname);
			contact.setPatronymic(patronymic);
			contact.setAddress(address);
			contact.setHomePhone(homePhone);
			contact.setEmail(email);
			contact.setMobilePhone(mobilePhone);
			contact.setUserId(userId);
			return contact;
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
