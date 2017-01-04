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
import com.lar.entity.Contact;
import com.lar.entity.Contact.ContactBuilder;
import com.lar.service.IContactService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class TestContactDAO {

	@Autowired
	private IContactService iContactService;

	private Contact fistContact;
	private Contact secondContact;
	private Contact thirdContact;
	private Contact fourthContact;
	private List<Contact> beforeContactsList;

	@Before
	public void setUp() {
		iContactService.deleteContacts();
		fistContact = new ContactBuilder().id(1L).name("firstName").surname("firstSurname")
				.patronymic("firstPatronymic").address("firstAddress").email("first@email.com").homePhone("0441234567")
				.mobilePhone("631234567").build();
		secondContact = Contact.builder().id(2L).name("secondName").surname("secondSurname")
				.patronymic("secondPatronymic").address("secondAddress").email("second@email.com")
				.homePhone("0441234567").mobilePhone("632234567").build();
		thirdContact = Contact.builder().id(3L).name("thirdName").surname("thirdSurname").patronymic("thirdPatronymic")
				.address("thirdAddress").email("third@email.com").homePhone("0441234567").mobilePhone("633234567")
				.build();
		fourthContact = Contact.builder().id(4L).name("fourthName").surname("fourthSurname")
				.patronymic("fourthPatronymic").address("fourthAddress").email("fourth@email.com")
				.homePhone("0441234567").mobilePhone("634234567").build();
		beforeContactsList = new ArrayList<>();
		beforeContactsList.addAll(Arrays.asList(fistContact, secondContact, thirdContact,fourthContact));
		iContactService.save(fistContact);
		iContactService.save(secondContact);
		iContactService.save(thirdContact);
		iContactService.save(fourthContact);

	}

	@Test
	public void getAllContacts() throws Exception {
		List<Contact> listContact = Lists.newArrayList(iContactService.getAllContacts());
		Assert.assertTrue(listContact.size() == beforeContactsList.size());

	}

	@Test
	public void testFindContactById() {
		Contact contactFindedById = iContactService.getContactBy(fistContact.getId());
		Assert.assertTrue(contactFindedById.getEmail().equals(fistContact.getEmail()));
	}

	@Test
	public void testingSortingContactsByName() throws Exception {
		List<Contact> listContactFindedById = iContactService.sortContctsByName();
		for (int i = 1; i < listContactFindedById.size(); i++) {
			char[] arrayLeterPreviousContact = listContactFindedById.get(i - 1).getName().toCharArray();
			char[] arrayLeterNextContact = listContactFindedById.get(i).getName().toCharArray();
			for (int x = 0; x <= arrayLeterPreviousContact.length; x++) {
				if (arrayLeterPreviousContact[x] > arrayLeterNextContact[x]) {
					throw new Exception("Contacts are not sorted");
				} else {
					break;
				}
			}
		}
		Assert.assertTrue(true);
	}

	@Test
	public void testingSortingContactsByTelephone() throws Exception {

		List<Contact> listContactFindedById = iContactService.sortContctsByTelephone();
		char[] arrayLeterPreviousContact;
		char[] arrayLeterNextContact;

		for (int i = 1; i < listContactFindedById.size(); i++) {
			arrayLeterPreviousContact = listContactFindedById.get(i - 1).getMobilePhone().toCharArray();
			arrayLeterNextContact = listContactFindedById.get(i).getMobilePhone().toCharArray();
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
	public void testingSortingContactsByUserId() throws Exception {
		Iterable<Contact> itarableContactFindedByUserId = iContactService.findContactsBy(fistContact.getId());
		List<Contact> listContactFindedByUserId = new ArrayList<Contact>();
		for (Contact contact : itarableContactFindedByUserId) {
			if (contact.getEmail().equals(fistContact.getEmail())
					|| (contact.getEmail().equals(secondContact.getEmail()))) {
				listContactFindedByUserId.add(contact);
			} else
				throw new Exception("These contacts do not match");
		}
		Assert.assertTrue(listContactFindedByUserId.size() >= 1);
	}

}
