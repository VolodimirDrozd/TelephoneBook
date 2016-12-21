package com.lar.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lar.dao.IJPAContactDAO;
import com.lar.entity.Contact;
import com.lar.service.IContactService;
import com.lar.validator.TelephoneValidator;

public class JDBCContactServiceImpl implements IContactService {
	
	@Autowired
	TelephoneValidator telephoneValidator;

	@Autowired
	private IJPAContactDAO iJPAcontactDao;

	@Autowired
	private AuthenticationService authenticationService;

	public Contact getContactById(Long id) {
		return iJPAcontactDao.findOne(id);
	}

	public Iterable<Contact> getAllContacts() {
		return iJPAcontactDao.findAll();
	}

	public Contact save(Contact contact) {
		if (telephoneValidator.checkNumber(contact.getTelephone())) {
			return null;
		}
		authenticationService.setUserIdForContact(contact);
		contact.setTelephone(telephoneValidator.createNumber(contact.getTelephone()));
		return iJPAcontactDao.save(contact);
	}

	public List<Contact> findAllOrderByName() {
		return iJPAcontactDao.findAllByOrderByNameAsc();
	}

	public List<Contact> findAllOrderByTelephone() {
		return iJPAcontactDao.findAllByOrderByTelephoneAsc();
	}

	public void delete(Long id) {
		iJPAcontactDao.delete(id);
	}

	// Use for junit
	public void deleteAll() {
		iJPAcontactDao.deleteAll();
	}

	public List<Contact> findAllByUserId(Long userId) {
		List<Long> listUserId = new ArrayList<Long>();
		listUserId.add(userId);
		List<Contact> listContact = new ArrayList<>();
		Iterable<Contact> iterableContact = iJPAcontactDao.findAll((Iterable<Long>) listUserId);
		for (Contact contact : iterableContact) {
			listContact.add(contact);
		}
		return listContact;
	}

}
