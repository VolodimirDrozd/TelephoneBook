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
import com.lar.entity.Contact;
import com.lar.service.IContactService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestContactDAO {

	@Autowired
	private IContactService iContactService;

	Contact testContactFirst;
	Contact testContactSecond;

	@Before
	public void setUp() {
		iContactService.deleteAll();
		testContactFirst = new Contact();
		testContactSecond = new Contact();
		testContactFirst.setName("FirstContactName");
		testContactFirst.setSurname("ContactSurname");
		testContactFirst.setPatronymic("ContactPatronymic");
		testContactFirst.setPatronymic("ContactPatronymic");
		testContactFirst.setAddress("ContactAdress");
		testContactFirst.setEmail("ContactEmail");
		testContactFirst.setHomeTelephone("ContactHomeTelphone");
		testContactFirst.setTelephone("3806326388888");
		testContactFirst.setUserId(1l);
		testContactSecond.setName("SecondContactName");
		testContactSecond.setTelephone("380632639");
		testContactSecond.setUserId(1l);
		testContactSecond.setEmail("FirstContactEmail");
		iContactService.save(testContactFirst);
		iContactService.save(testContactSecond);
	}

	@Test
	public void testFindContactById() {
		Contact contactFindedById = iContactService.getContactById(1L);
		Assert.assertTrue(contactFindedById.getEmail().equals(testContactFirst.getEmail()));
	}

	@Test
	public void testFindAllOrderByName() throws Exception {
		List<Contact> listContactFindedById = iContactService.findAllOrderByName();
		char[] arrayLeterPreviousContact;
		char[] arrayLeterNextContact;

		for (int i = 1; i < listContactFindedById.size(); i++) {
			arrayLeterPreviousContact = listContactFindedById.get(i - 1).getName().toCharArray();
			arrayLeterNextContact = listContactFindedById.get(i).getName().toCharArray();
			for (int x = 1; x <= arrayLeterPreviousContact.length; x++) {
				if (arrayLeterNextContact[x] > arrayLeterNextContact[x]) {
					throw new Exception("Contacts are not sorted");
				} else
					break;
			}
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testFindAllOrderByTelephone() throws Exception {
		List<Contact> listContactFindedById = iContactService.findAllOrderByTelephone();
		char[] arrayLeterPreviousContact;
		char[] arrayLeterNextContact;

		for (int i = 1; i < listContactFindedById.size(); i++) {
			arrayLeterPreviousContact = listContactFindedById.get(i - 1).getTelephone().toCharArray();
			arrayLeterNextContact = listContactFindedById.get(i).getTelephone().toCharArray();
			for (int x = 1; x <= arrayLeterPreviousContact.length; x++) {
				if (arrayLeterNextContact[x] > arrayLeterNextContact[x]) {
					throw new Exception("Contacts are not sorted");
				} else
					break;
			}
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testFindAllByUserId() throws Exception {
		Iterable<Contact> itarableContactFindedByUserId = iContactService.findAllByUserId(testContactFirst.getId());
		List<Contact> listContactFindedByUserId = new ArrayList<Contact>();
		for (Contact contact : itarableContactFindedByUserId) {
			if (contact.getEmail().equals(testContactFirst.getEmail())
					|| (contact.getEmail().equals(testContactSecond.getEmail()))) {
				listContactFindedByUserId.add(contact);
			} else
				throw new Exception("These contacts do not match");
		}
		Assert.assertTrue(listContactFindedByUserId.size() >= 1);
	}

	@Test
	public void getAllContacts() throws Exception {
		Iterable<Contact> itarableContact = iContactService.getAllContacts();
		List<Contact> listContact = new ArrayList<>();
		for (Contact contact : itarableContact) {
			if (!contact.getEmail().equals(testContactFirst.getEmail())
					|| (!contact.getEmail().equals(testContactSecond.getEmail()))) {
				listContact.add(contact);
			} else
				throw new Exception("These contacts do not match");
		}
		Assert.assertTrue(listContact.size() == 2);

	}

}
