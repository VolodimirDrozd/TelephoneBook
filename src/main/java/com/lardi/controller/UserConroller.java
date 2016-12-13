package com.lardi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lardi.db.UserDAOImpl;
import com.lardi.entity.User;

public class UserConroller {

	@Autowired
	@Qualifier("UsersDAO")
	private UserDAOImpl iuserDAOImpl;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationUser() {
		return "registrationUser";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationUserDataInDB(@RequestParam("login") String login,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("surname") String surname, @RequestParam("patronymic") String patronymic) {
		User user = new User(login, password, name, surname, patronymic);
		iuserDAOImpl.save(user);
		return "login";
	}

}
