package com.lar.service;

import java.util.List;

import com.lar.entity.Contact;

public interface IContactService {
	
	public Contact save(Contact contact);

	public Contact getContactBy(Long contactId);

	public Iterable<Contact> getAllContacts();

	public List<Contact> sortContctsByName();

	public List<Contact> sortContctsByTelephone();

	// Use for JUnit
	public void deleteContacts();

	public List<Contact> findContactsBy(Long userId);

	void deleteContact(Long contactId);

}
