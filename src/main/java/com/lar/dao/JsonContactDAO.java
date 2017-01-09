package com.lar.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.lar.entity.Contact;
import com.lar.seviceimpl.AuthenticationService;
import com.lar.validatoruserdata.ContactDataValidator;

public class JsonContactDAO extends JsonDAO {

	private File fileContacts;

	@Value("${pathContactDB}")
	private String fileContactDBPath;

	@PostConstruct
	protected void innit() {
		try {
			fileContacts = new File(fileContactDBPath);

			fileContacts.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private ContactDataValidator contactDataValidator;

	public Contact getContactBy(Long id) {
		for (Contact contact : getAllContacts()) {
			if (contact.getId() == id) {
				return contact;
			}
		}
		return null;
	}

	public Iterable<Contact> getAllContacts() {
		ArrayList<Contact> allContact = new ArrayList<>();
		Contact[] arrayContact = gson.fromJson(createJsonReader(), Contact[].class);
		if (arrayContact != null) {
			return Arrays.asList(arrayContact);
		} else {
			return allContact;
		}
	}

	public Contact save(Contact contact) {
		try {
			contactDataValidator.validate(contact);
			contactDataValidator.createNumber(contact.getMobilePhone());
			List<Contact> newListContactsForSave = Lists.newArrayList(getAllContacts());
			contact.setId(Long.valueOf(newListContactsForSave.size() + 1));
			authenticationService.setUserIdForContact(contact);
			newListContactsForSave.add(contact);
			writeToDBFile(newListContactsForSave);
		} catch (Exception e) {
		}
		return contact;
	}

	@SuppressWarnings("unchecked")
	public List<Contact> sortContactsByName() {
		List<Contact> contactsList = Lists.newArrayList(getAllContacts());
		Collections.sort(contactsList, new Comparator<Contact>() {
			@Override
			public int compare(Contact contactForCompare1, Contact contactForCompare2) {
				return contactForCompare1.getName().compareTo(contactForCompare2.getName());
			}
		});
		return writeToDBFile(contactsList);

	}

	@SuppressWarnings("unchecked")
	public List<Contact> sortContactsByTelephone() {
		List<Contact> contactsList = Lists.newArrayList(getAllContacts());
		Collections.sort(contactsList, new Comparator<Contact>() {
			@Override
			public int compare(Contact contactForCompare1, Contact contactForCompare2) {
				return contactForCompare1.getMobilePhone().compareTo(contactForCompare2.getMobilePhone());
			}
		});
		return writeToDBFile(contactsList);

	}

	public List<Contact> findContactsBy(Long userId) {
		List<Contact> allContactByUser = new ArrayList<>();
		for (Contact contact : getAllContacts()) {
			if (contact.getUserId() == userId) {
				allContactByUser.add(contact);
			}
		}
		return allContactByUser;
	}

	public void deleteContact(Long contactId) {
		List<Contact> listContactWithoutDeleted = new ArrayList<>();
		for (Contact contact : getAllContacts()) {
			if (contact.getId().equals(contactId)) {
				continue;
			}
			listContactWithoutDeleted.add(contact);
		}
		writeToDBFile(listContactWithoutDeleted);
	}

	@Override
	protected File getFileWithEntity() {
		return fileContacts;
	}

}
