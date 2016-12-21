package com.lar.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lar.Application;
import com.lar.validator.TelephoneValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestAppFunctional {

	@Autowired
	TelephoneValidator telephoneValidator;

	@Test
	public void testTelephoneValidator() {
		String telephoneNumber = "1234567890";
		Assert.assertTrue(telephoneValidator.checkNumber(telephoneNumber));
		String resaultNumber = telephoneValidator.createNumber(telephoneNumber);
		Assert.assertTrue(resaultNumber.equals("+380(123)4567890"));
	}

	@Test
	public void testTelephoneValidatorByQuantityNumber() {
		String telephoneExceedsNumber = "123456789";
		String telephoneMissingNumber = "12345678909";
		Assert.assertFalse(telephoneValidator.checkNumber(telephoneExceedsNumber));
		Assert.assertFalse(telephoneValidator.checkNumber(telephoneMissingNumber));
	}

	@Test
	public void testTelephoneValidatorByletter() {
		String letter = "abcdefgeh";
		Assert.assertFalse(telephoneValidator.checkNumber(letter));
	}
}
