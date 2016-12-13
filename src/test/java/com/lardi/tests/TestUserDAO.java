package com.lardi.tests;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lardi.DAO.IUserDAO;
import com.lardi.configuration.AppConfig;
import com.lardi.db.UserDAOImpl;
import com.lardi.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class TestUserDAO {

	@Autowired
	@Qualifier("alternativeDataSource")
	DriverManagerDataSource driverManagerDataSource;

	IUserDAO userDAO;

	@Before
	public void createUsersTable() {

		userDAO = new UserDAOImpl(driverManagerDataSource);
		userDAO.createUserTableIfNotExist();
		userDAO.alterCurrentContactId();
	}

	@Test
	public void testSetterUser() {
		User user = new User("login", "password", "name", "surname", "patronymic");
		userDAO.save(user);
		assertNotNull(userDAO.getUser(1));
		userDAO.findUserByLogin(user.getLogin());
	}

	@Test
	public void testSelectAllUsers() {
		User user = new User("login", "password", "name", "surname", "patronymic");
		userDAO.save(user);
		List<User> listContact = userDAO.getAllUser();
		assertNotNull(listContact);
	}

	@Test
	public void testSelectUser() {
		User user = new User("login", "password", "name", "surname", "patronymic");
		userDAO.save(user);
		user = userDAO.getUser(1);
		userDAO.delete(user.getId());

	}

	@After
	public void deleteAll() {
		userDAO.deleteAll();
	}

}
