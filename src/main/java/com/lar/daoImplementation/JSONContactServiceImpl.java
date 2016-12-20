package com.lar.daoImplementation;

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

import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lar.entity.Contact;
import com.lar.service.IContactService;

public class JSONContactServiceImpl implements IContactService {

	@Value("${pathContactDB}")
	String fileUserDBPath;

	File file;
	Gson gson;

	@PostConstruct
	public void innit() {
		file = new File(fileUserDBPath);
		gson = new Gson();
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
		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JsonReader jsonReader = new JsonReader(reader);
		jsonReader.setLenient(true);
		arrayContact = gson.fromJson(jsonReader, Contact[].class);
		if (arrayContact == null) {
			return null;
		}
		return Arrays.asList(arrayContact);
	}

	@Override
	public Contact save(Contact contact) {
		try {
			file.createNewFile();
			List<Contact> list = new ArrayList<>();
			Iterable<Contact> listSavedContacts = getAllContacts();
			// User user =
			// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			long contactId = 1;
			if (listSavedContacts != null) {
				for (Contact contactFromFile : listSavedContacts) {
					list.add(contactFromFile);
					contactId++;
				}
			}
			Writer osWriter = new FileWriter(file, false);
			contact.setId(contactId);
			// contact.setUserId(user.getId());
			list.add(contact);
			gson.toJson(list, osWriter);
			osWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contact;
	}

	@Override
	public List<Contact> findAllOrderByName() {
		Iterable<Contact> contactIterable = getAllContacts();
		List<Contact> contactsSortedList = new ArrayList<>();
		for (Contact contact : contactIterable) {
			contactsSortedList.add(contact);
		}
		Collections.sort(contactsSortedList, new Comparator<Contact>() {
			@Override
			public int compare(Contact contactForCompare1, Contact contactForCompare2) {
				return contactForCompare1.getName().compareTo(contactForCompare2.getName());
			}
		});
		Writer osWriter;
		try {
			osWriter = new FileWriter(file, false);
			gson.toJson(contactsSortedList, osWriter);
			osWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactsSortedList;
	}

	public List<Contact> findAllOrderByTelephone() {
		Iterable<Contact> contactIterable = getAllContacts();
		List<Contact> contactsSortedList = new ArrayList<>();
		for (Contact contact : contactIterable) {
			contactsSortedList.add(contact);
		}
		Collections.sort(contactsSortedList, new Comparator<Contact>() {
			@Override
			public int compare(Contact contactForCompare1, Contact contactForCompare2) {
				return contactForCompare1.getTelephone().compareTo(contactForCompare2.getTelephone());
			}
		});
		Writer osWriter;
		try {
			osWriter = new FileWriter(file, false);
			gson.toJson(contactsSortedList, osWriter);
			osWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactsSortedList;
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
		Writer osWriter;
		try {
			osWriter = new FileWriter(file, false);

			gson.toJson(listContactWithoutDeleted, osWriter);
			osWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAll() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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

}
