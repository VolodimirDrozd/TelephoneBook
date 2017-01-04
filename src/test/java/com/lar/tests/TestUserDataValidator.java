package com.lar.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lar.ApplicationConfig;
import com.lar.dto.UserDTO;
import com.lar.validatoruserdata.DataValidator;
import com.lar.validatoruserdata.UserDataValidator;
import com.lar.validatoruserdata.UserDataValidator.Field;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class TestUserDataValidator {

	private UserDTO testUser;

	@Before
	public void setup() throws Exception {
		testUser = new UserDTO();
	}

	@Test
	public void testUserNameValidator() {
		DataValidator userNameValidator = new UserDataValidator(Field.NAME);
		testUser.setName("testUserName");
		Assert.assertTrue(userNameValidator.validatorTitle(testUser.getName()));
		testUser.setName("TestUserName");
		Assert.assertTrue(userNameValidator.validatorTitle(testUser.getName()));
		// user mustn't have number in name
		testUser.setName("4TestUserName");
		Assert.assertFalse(userNameValidator.validatorTitle(testUser.getName()));
		// user mustn't have 4 letters in name
		testUser.setName("test");
		Assert.assertFalse(userNameValidator.validatorTitle(testUser.getName()));
		// user mustn't have space in name
		// testUser.setName("Test User Name");
		// Assert.assertFalse(userNameValidator.validatorTitle(testUser.getName()));
	}

	@Test
	public void testUserSurnameValidator() {
		DataValidator userSurnameValidator = new UserDataValidator(Field.SURNAME);
		testUser.setSurname("testUserSurname");
		Assert.assertTrue(userSurnameValidator.validatorTitle(testUser.getSurname()));
		testUser.setSurname("TestUserSurname");
		Assert.assertTrue(userSurnameValidator.validatorTitle(testUser.getSurname()));
		// user mustn't have number in name
		testUser.setSurname("4TestUserSurname");
		Assert.assertFalse(userSurnameValidator.validatorTitle(testUser.getSurname()));
		// user mustn't have 4 letters in name
		testUser.setSurname("test");
		Assert.assertFalse(userSurnameValidator.validatorTitle(testUser.getSurname()));
	}

	@Test
	public void testUserPatronymicValidator() {
		DataValidator userPatronymicValidator = new UserDataValidator(Field.SURNAME);
		testUser.setPatronymic("testUserPatronymic");
		Assert.assertTrue(userPatronymicValidator.validatorTitle(testUser.getPatronymic()));
		testUser.setPatronymic("TestUserPatronymic");
		Assert.assertTrue(userPatronymicValidator.validatorTitle(testUser.getPatronymic()));
		// user mustn't have number in name
		testUser.setPatronymic("4TestUserPatronymic");
		Assert.assertFalse(userPatronymicValidator.validatorTitle(testUser.getPatronymic()));
		// user mustn't have 4 letters in name
		testUser.setPatronymic("test");
		Assert.assertFalse(userPatronymicValidator.validatorTitle(testUser.getPatronymic()));
	}

	@Test
	public void testUserLoginValidator() {
		DataValidator userLoginValidator = new UserDataValidator(Field.LOGIN);
		testUser.setLogin("testUserLogin");
		Assert.assertTrue(userLoginValidator.validatorTitle(testUser.getLogin()));
		testUser.setLogin("TestUserLogin");
		Assert.assertTrue(userLoginValidator.validatorTitle(testUser.getLogin()));
		testUser.setLogin("4TestUserLogin");
		Assert.assertTrue(userLoginValidator.validatorTitle(testUser.getLogin()));
		// user mustn't have 3 letters in name
		testUser.setLogin("te");
		Assert.assertFalse(userLoginValidator.validatorTitle(testUser.getLogin()));
	}

	@Test
	public void testUserPasswordValidator() {
		DataValidator userPasswordValidator = new UserDataValidator(Field.PASSWORD);
		testUser.setPassword("testUserPassword");
		Assert.assertTrue(userPasswordValidator.validatorTitle(testUser.getPassword()));
		testUser.setPassword("TestUserPassword");
		Assert.assertTrue(userPasswordValidator.validatorTitle(testUser.getPassword()));
		testUser.setPassword("4TestUserPassword");
		Assert.assertTrue(userPasswordValidator.validatorTitle(testUser.getPassword()));
		// user mustn't have 3 letters in name
		testUser.setPassword("te");
		Assert.assertFalse(userPasswordValidator.validatorTitle(testUser.getPassword()));
	}

}
