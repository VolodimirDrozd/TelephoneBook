package com.lar.sevice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lar.entity.Contact;
import com.lar.service.IContactService;
import com.lar.validator.TelephoneValidator;

public class JSONContactServiceImpl implements IContactService {

	@Autowired
	private TelephoneValidator telephoneValidator;

	@Value("${pathContactDB}")
	private String fileUserDBPath;

	@Autowired
	private AuthenticationService authenticationService;

	private File file;
	private Gson gson;

	@PostConstruct
	public void innit() {
		gson = new Gson();
		file = new File(fileUserDBPath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Contact getContactById(Long id) {
		Iterable<Contact> contactList = getAllContacts();
		for (Contact contact : contactList) {
			if (contact.getId() == id) {
				return contact;
			}
		}
		return null;
	}

	@Override
	public Iterable<Contact> getAllContacts() {
		Contact[] arrayContact = null;
		try {
			Reader reader = new FileReader(file);
			JsonReader jsonReader = new JsonReader(reader);
			jsonReader.setLenient(true);
			arrayContact = gson.fromJson(jsonReader, Contact[].class);
			if (arrayContact == null) {
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Arrays.asList(arrayContact);
	}

	@Override
	public Contact save(Contact contact) {
		if (telephoneValidator.checkNumber(contact.getTelephone())) {
			return null;
		}
		Iterable<Contact> listSavedContacts = getAllContacts();
		authenticationService.setUserIdForContact(contact);
		List<Contact> list = createListFromIterable(listSavedContacts);
		contact.setId(Long.valueOf(list.size() + 1));
		contact.setTelephone(telephoneValidator.createNumber(contact.getTelephone()));
		list.add(contact);
		writeObjectToFile(list);
		return contact;
	}

	@Override
	public List<Contact> findAllOrderByName() {
		List<Contact> contactsList = createListFromIterable(getAllContacts());
		Collections.sort(contactsList, new Comparator<Contact>() {
			@Override
			public int compare(Contact contactForCompare1, Contact contactForCompare2) {
				return contactForCompare1.getName().compareTo(contactForCompare2.getName());
			}
		});
		writeObjectToFile(contactsList);
		return contactsList;
	}

	@Override
	public List<Contact> findAllByUserId(Long userId) {
		Iterable<Contact> contactList = getAllContacts();
		List<Contact> allContactByUser = new ArrayList<>();
		for (Contact contact : contactList) {
			if (contact.getUserId() == userId) {
				allContactByUser.add(contact);
			}
		}
		return allContactByUser;
	}

	@Override
	public List<Contact> findAllOrderByTelephone() {
		List<Contact> contactsList = createListFromIterable(getAllContacts());
		Collections.sort(contactsList, new Comparator<Contact>() {
			@Override
			public int compare(Contact contactForCompare1, Contact contactForCompare2) {
				return contactForCompare1.getTelephone().compareTo(contactForCompare2.getTelephone());
			}
		});
		writeObjectToFile(contactsList);
		return contactsList;
	}

	@Override
	public void delete(Long id) {
		Iterable<Contact> contactList = getAllContacts();
		List<Contact> listContactWithoutDeleted = new ArrayList<>();
		for (Contact contact : contactList) {
			if (contact.getId() == id) {
				continue;
			}
			listContactWithoutDeleted.add(contact);
		}
		writeObjectToFile(listContactWithoutDeleted);
	}

	@Override
	public void deleteAll() {
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private List<Contact> createListFromIterable(Iterable<Contact> iterableContactList) {
		List<Contact> listContact = new ArrayList<Contact>();
		if (iterableContactList != null) {
			for (Contact contact : iterableContactList) {
				listContact.add(contact);
			}
		}
		return listContact;
	}

	private void writeObjectToFile(List<Contact> list) {
		try {
			Writer osWriter = null;
			osWriter = new FileWriter(file, false);

			gson.toJson(list, osWriter);
			osWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
