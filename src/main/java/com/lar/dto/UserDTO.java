package com.lar.dto;

import com.lar.entity.User;

public class UserDTO {

	private Long id;
	private String login;
	private String password;
	private String name;
	private String patronymic;
	private String surname;

	public User build() {
		return User.builder().login(login).password(password).name(name).surname(surname).patronymic(patronymic)
				.build();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
