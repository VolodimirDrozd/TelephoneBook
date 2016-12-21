package com.lar.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lar.Application;
import com.lar.controller.RestUserController;
import com.lar.dto.UserDTO;
import com.lar.entity.User;
import com.lar.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestUserDAO {

	@Autowired
	IUserService iUserService;

	@Autowired
	RestUserController restUserController;

	private UserDTO userDTOFirst;
	private UserDTO userDTOSecond;

	@Before
	public void setup() throws Exception {
		iUserService.deleteAll();
		userDTOFirst = new UserDTO();
		userDTOSecond = new UserDTO();
		userDTOFirst.setLogin("testUserLogin");
		userDTOFirst.setName("testUserName");
		userDTOFirst.setPassword("testUserPassword");
		userDTOFirst.setPatronymic("testUserPatronymic");
		userDTOFirst.setSurname("testUserSurname");
		userDTOSecond.setLogin("testSecondUserLogin");
		restUserController.save(userDTOFirst);
		restUserController.save(userDTOSecond);
	}

	@Test
	public void testUserDAOFindUserById() {
		User userFinded = iUserService.findUserById((iUserService.findUserByLogin(userDTOFirst.getLogin()).getId()));
		Assert.assertTrue(userFinded.getLogin().equals(userFinded.getLogin()));
	}

	@Test
	public void testUserDAOFindUserByLogin() {
		User userFinded = iUserService.findUserByLogin(userDTOFirst.getLogin());
		Assert.assertTrue(userFinded.getLogin().equals(userDTOFirst.getLogin()));
	}

	@Test
	public void testUserDAOGetAllUsers() {
		Iterable<User> iterableUser = iUserService.getAllUsers();
		List<User> listFindedUser = new ArrayList<>();
		for (User userFinded : iterableUser) {
			if (userFinded.getLogin().equals(userDTOFirst.getLogin())
					|| (userDTOSecond.getLogin().equals(userDTOSecond.getLogin()))) {
				listFindedUser.add(userFinded);
			} else
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		Assert.assertTrue(listFindedUser.size() == 2);
	}

}
