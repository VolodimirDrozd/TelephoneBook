
package com.lardi.entity;

public class Contact {

	private Integer contactId;
	private Integer userId;
	private String name;
	private String surname;
	private String patronymic;
	private String telephone;
	private String homeTelephone;
	private String address;
	private String email;

	public Contact() {
	};

	public Contact(Integer contactId, String name, String surname, String patronymic, String telephone,
			String homeTelephone, String address, String email, Integer userId) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.telephone = telephone;
		this.homeTelephone = homeTelephone;
		this.address = address;
		this.email = email;
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

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer userId) {
		this.contactId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
