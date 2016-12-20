package com.lar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

	@Id
	@GeneratedValue
	private Long id;
	private Long userId;
	private String name;
	private String surname;
	private String patronymic;
	private String telephone;
	private String homeTelephone;
	private String address;
	private String email;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHomeTelephone() {
		return homeTelephone;
	}

	public void setHomeTelephone(String homeTelephone) {
		this.homeTelephone = homeTelephone;
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

	public static ContactBuilder builder() {
		return new ContactBuilder();
	}

	public static class ContactBuilder {
		private Long id;
		private Long userId;
		private String name;
		private String surname;
		private String patronymic;
		private String telephone;
		private String homeTelephone;
		private String address;
		private String email;

		public ContactBuilder id(long id) {
			id(id);
			return this;
		}

		public ContactBuilder userId(long userId) {
			userId(userId);
			return this;
		}

		public ContactBuilder name(String name) {
			name(name);
			return this;
		}

		public ContactBuilder surname(String surname) {
			surname(surname);
			return this;
		}

		public ContactBuilder patronymic(String patronymic) {
			patronymic(patronymic);
			return this;
		}

		public ContactBuilder telephone(String telephone) {
			patronymic(telephone);
			return this;
		}

		public ContactBuilder homeTelephone(String homeTelephone) {
			homeTelephone(homeTelephone);
			return this;
		}

		public ContactBuilder address(String address) {
			address(address);
			return this;
		}

		public ContactBuilder email(String email) {
			email(email);
			return this;
		}

		public Contact build() {
			Contact contact = new Contact();
			contact.setId(id);
			contact.setName(name);
			contact.setSurname(surname);
			contact.setPatronymic(patronymic);
			contact.setAddress(address);
			contact.setHomeTelephone(homeTelephone);
			contact.setEmail(email);
			contact.setTelephone(telephone);
			contact.setUserId(userId);
			return contact;
		}

	}
}
