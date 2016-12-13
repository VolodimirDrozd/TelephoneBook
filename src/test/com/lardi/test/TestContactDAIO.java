package com.lardi.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lardi.DAO.ContactsDAO;
import com.lardi.configuration.AppConfig;
import com.lardi.db.ContactsDAOImpl;
import com.lardi.entity.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)
@ContextConfiguration(classes = { AppConfig.class })
public class TestContactDAIO {

	@Autowired
	@Qualifier("alternativeDataSource")
	DriverManagerDataSource driverManagerDataSource;

	private List<Contact> listContact;

	Contact contact;
	ContactsDAO contactDAO;
	ContactsDAO contactsDAOImpl;

	@Before
	public void createContactsTable() {

		contactsDAOImpl = new ContactsDAOImpl(driverManagerDataSource);
		contactsDAOImpl.createContactTableIfNotExist();

	}

	@Test
	public void testSetterProjectIdCategories() {
		contact = new Contact("name", "surname", "patronymic", "telephone", "homeTelephone", "address", "email");
		contactsDAOImpl.insert(contact);
	}

}
