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

import com.lardi.DAO.IContactsDAO;
import com.lardi.configuration.AppConfig;
import com.lardi.db.ContactsDAOImpl;
import com.lardi.entity.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class TestContactDAO {

	@Autowired
	@Qualifier("alternativeDataSource")
	DriverManagerDataSource driverManagerDataSource;

	IContactsDAO contactsDAO;

	@Before
	public void createContactsTable() {

		contactsDAO = new ContactsDAOImpl(driverManagerDataSource);
		contactsDAO.createContactTableIfNotExist();
		contactsDAO.alterCurrentContactId();
	}

	// TODO accses about userId
	@Test
	public void testSetterContact() {
		Contact contact = new Contact(1, "name", "surname", "patronymic", "telephone", "homeTelephone", "address",
				"email", 1);
		contactsDAO.save(contact);
		assertNotNull(contactsDAO.getContact(1));
	}

	// TODO accses about userId
	@Test
	public void testSelectAllContacts() {
		Contact contact = new Contact(1, "name", "surname", "patronymic", "telephone", "homeTelephone", "address",
				"email", 1);
		contactsDAO.save(contact);
		List<Contact> listContact = contactsDAO.getAllContacts();
		assertNotNull(listContact);
	}

	// TODO accses about userId
	@Test
	public void testSelectContact() {
		Contact contact = new Contact(1, "name", "surname", "patronymic", "telephone", "homeTelephone", "address",
				"email", 1);
		contactsDAO.save(contact);
		contact = contactsDAO.getContact(1);
		contactsDAO.delete(contact.getContactId());

	}

	@After
	public void deleteAll() {
		contactsDAO.deleteAll();
	}

}
