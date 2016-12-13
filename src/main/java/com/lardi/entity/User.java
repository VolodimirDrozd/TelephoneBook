package com.lardi.entity;

public class User {

	private String login;
	private String password;
	private String name;
	private String surname;
	private String patronymic;
	private Integer userId;

	public User() {
	}

	public User(String login, String password, String name, String surname, String patronymic) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
	}

	public User(Integer userId, String login, String password, String name, String surname, String patronymic) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
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

	public Integer getId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}