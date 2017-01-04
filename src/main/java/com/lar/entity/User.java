package com.lar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	// @GeneratedValue
	private Long id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String patronymic;

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

	public static UserBuilder builder() {
		return new UserBuilder();
	}

	public static class UserBuilder {
		private Long id;
		private String login;
		private String password;
		private String name;
		private String surname;
		private String patronymic;

		public UserBuilder() {
		}

		public UserBuilder id(Long valId) {
			id = valId;
			return this;
		}

		public UserBuilder login(String valLogin) {
			login = valLogin;
			return this;
		}

		public UserBuilder password(String valPassword) {
			password = valPassword;
			return this;
		}

		public UserBuilder name(String valName) {
			name = valName;
			return this;
		}

		public UserBuilder surname(String valSurname) {
			surname = valSurname;
			return this;
		}

		public UserBuilder patronymic(String valPatronymic) {
			patronymic = valPatronymic;
			return this;
		}

		public User build() {
			User user = new User();
			user.setId(id);
			user.setLogin(login);
			user.setName(name);
			user.setPassword(password);
			user.setPatronymic(patronymic);
			user.setSurname(surname);
			return user;
		}
	}
}