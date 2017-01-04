package com.lar.seviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lar.dao.JsonContactDAO;
import com.lar.entity.Contact;
import com.lar.service.IContactService;

public class JsonContactServiceImpl implements IContactService {

	@Autowired
	private JsonContactDAO jsonContactDAO;


	@Override
	public Contact getContactBy(Long contactId) {
		return jsonContactDAO.getContactBy(contactId);
	}

	@Override
	public Iterable<Contact> getAllContacts() {
		return jsonContactDAO.getAllContacts();
	}

	@Override
	public Contact save(Contact contact) {
		return jsonContactDAO.save(contact);
	}

	@Override
	public List<Contact> sortContctsByName() {
		return jsonContactDAO.sortContactsByName();
	}

	@Override
	public List<Contact> sortContctsByTelephone() {
		return jsonContactDAO.sortContactsByTelephone();
	}

	@Override
	public List<Contact> findContactsBy(Long userId) {
		return jsonContactDAO.findContactsBy(userId);
	}

	@Override
	public void deleteContact(Long contactId) {
		jsonContactDAO.deleteContact(contactId);
	}

	@Override
	public void deleteContacts() {
		jsonContactDAO.deleteAll();
	}

}
