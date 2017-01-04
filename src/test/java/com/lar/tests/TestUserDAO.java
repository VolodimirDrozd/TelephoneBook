package com.lar.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.lar.ApplicationConfig;
import com.lar.entity.User;
import com.lar.entity.User.UserBuilder;
import com.lar.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class TestUserDAO {

	@Autowired
	private IUserService iUserService;

	private User firstTestUser;
	private User secondTestUser;
	private User thirdTestUser;
	private User fourthTestUser;
	private List<User> beforeUserList;

	@Before
	public void init() throws Exception {
		iUserService.deleteAllUsers();
		firstTestUser = new UserBuilder().id(1L).login("firstLogin").password("firstPassword").name("firstName")
				.surname("firstSurname").patronymic("firstPatronymic").build();
		secondTestUser = new UserBuilder().id(2L).login("secondLogin").password("secondPassword").name("secondName")
				.surname("secondSurname").patronymic("secondPatronymic").build();
		thirdTestUser = new UserBuilder().id(3L).login("thirdLogin").password("thirdPassword").name("thirdName")
				.surname("thirdSurname").patronymic("thirdPatronymic").build();
		fourthTestUser = new UserBuilder().id(4L).login("fourthLogin").password("fourthPassword").name("fourthName")
				.surname("fourthSurname").patronymic("fourthPatronymic").build();
		beforeUserList = new ArrayList<>();
		beforeUserList.addAll(Arrays.asList(firstTestUser, secondTestUser, thirdTestUser, fourthTestUser));
		iUserService.save(firstTestUser);
		iUserService.save(secondTestUser);
		iUserService.save(thirdTestUser);
		iUserService.save(fourthTestUser);
	}

	@Test
	public void testFindUserById() {
		long userId = iUserService.findUserBy(firstTestUser.getLogin()).getId();
		User userFinded = iUserService.findUserBy(userId);
		Assert.assertTrue(userFinded.getLogin().equals(userFinded.getLogin()));
	}

	@Test
	public void testFindUserByLogin() {
		User userFinded = iUserService.findUserBy(firstTestUser.getLogin());
		Assert.assertTrue(userFinded.getLogin().equals(firstTestUser.getLogin()));
	}

	@Test
	public void testGetAllUsers() {
		List<User> listFindedUser = Lists.newArrayList(iUserService.getAllUsers());
		Assert.assertTrue(listFindedUser.size()==beforeUserList.size());
	}
}
