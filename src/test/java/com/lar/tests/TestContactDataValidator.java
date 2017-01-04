package com.lar.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lar.ApplicationConfig;
import com.lar.entity.Contact;
import com.lar.validatoruserdata.ContactDataValidator;
import com.lar.validatoruserdata.ContactDataValidator.Field;
import com.lar.validatoruserdata.DataValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class TestContactDataValidator {

	private Contact testContact;

	@Before
	public void setup() throws Exception {
		testContact = new Contact();
	}

	@Test
	public void testUserNameValidator() {
		
		DataValidator contactNameValidator = new ContactDataValidator(Field.NAME);
		testContact.setName("testUserName");
		Assert.assertTrue(contactNameValidator.validatorTitle(testContact.getName()));
		testContact.setName("TestUserName");
		Assert.assertTrue(contactNameValidator.validatorTitle(testContact.getName()));
		// user mustn't have number in name
		testContact.setName("4TestUserName");
		Assert.assertFalse(contactNameValidator.validatorTitle(testContact.getName()));
		// user mustn't have 4 letters in name
		testContact.setName("tes");
		Assert.assertFalse(contactNameValidator.validatorTitle(testContact.getName()));
		// user mustn't have space in name
		// testUser.setName("Test User Name");
		// Assert.assertFalse(userNameValidator.validatorTitle(testUser.getName()));
	}

	@Test
	public void testUserSurnameValidator() {
		DataValidator contactSurnameValidator = new ContactDataValidator(Field.SURNAME);
		testContact.setSurname("testUserSurname");
		Assert.assertTrue(contactSurnameValidator.validatorTitle(testContact.getSurname()));
		testContact.setSurname("TestUserSurname");
		Assert.assertTrue(contactSurnameValidator.validatorTitle(testContact.getSurname()));
		// user mustn't have number in name
		testContact.setSurname("4TestUserSurname");
		Assert.assertFalse(contactSurnameValidator.validatorTitle(testContact.getSurname()));
		// user mustn't have 4 letters in name
		testContact.setSurname("tes");
		Assert.assertFalse(contactSurnameValidator.validatorTitle(testContact.getSurname()));
	}

	@Test
	public void testUserPatronymicValidator() {
		DataValidator contactPatronymicValidator = new ContactDataValidator(Field.SURNAME);
		testContact.setPatronymic("testUserPatronymic");
		Assert.assertTrue(contactPatronymicValidator.validatorTitle(testContact.getPatronymic()));
		testContact.setPatronymic("TestUserPatronymic");
		Assert.assertTrue(contactPatronymicValidator.validatorTitle(testContact.getPatronymic()));
		// user mustn't have number in name
		testContact.setPatronymic("4TestUserPatronymic");
		Assert.assertFalse(contactPatronymicValidator.validatorTitle(testContact.getPatronymic()));
		// user mustn't have 4 letters in name
		testContact.setPatronymic("tes");
		Assert.assertFalse(contactPatronymicValidator.validatorTitle(testContact.getPatronymic()));
	}

	@Test
	public void testEmailValidator() {
		DataValidator userLoginValidator = new ContactDataValidator(Field.EMAIL);
		testContact.setEmail("email@gmail.com");
		Assert.assertTrue(userLoginValidator.validatorTitle(testContact.getEmail()));
		testContact.setEmail("e4mail@e4mail.com");
		Assert.assertTrue(userLoginValidator.validatorTitle(testContact.getEmail()));
		testContact.setEmail("E4MAIL@E4MAIL.com");
		Assert.assertTrue(userLoginValidator.validatorTitle(testContact.getEmail()));
		testContact.setEmail("te");
		Assert.assertFalse(userLoginValidator.validatorTitle(testContact.getEmail()));
		testContact.setEmail("test.com");
		Assert.assertFalse(userLoginValidator.validatorTitle(testContact.getEmail()));
		testContact.setEmail("E4MAIL@E4MAIL");
		Assert.assertFalse(userLoginValidator.validatorTitle(testContact.getEmail()));
	}

	@Test
	public void testMobilePhoneValidator() {
		DataValidator userPasswordValidator = new ContactDataValidator(Field.MOBILEPHONE);
		testContact.setMobilePhone("123456789");
		Assert.assertTrue(userPasswordValidator.validatorTitle(testContact.getMobilePhone()));
		testContact.setMobilePhone("987654321");
		Assert.assertTrue(userPasswordValidator.validatorTitle(testContact.getMobilePhone()));
		testContact.setMobilePhone("1234567");
		Assert.assertFalse(userPasswordValidator.validatorTitle(testContact.getMobilePhone()));
		testContact.setMobilePhone("4Test");
		Assert.assertFalse(userPasswordValidator.validatorTitle(testContact.getMobilePhone()));
		// user mustn't have 7 letters in name
		testContact.setMobilePhone("te");
		Assert.assertFalse(userPasswordValidator.validatorTitle(testContact.getMobilePhone()));
	}
}
