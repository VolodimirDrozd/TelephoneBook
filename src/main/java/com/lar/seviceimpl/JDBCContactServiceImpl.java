package com.lar.seviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lar.dao.IJPAContactDAO;
import com.lar.entity.Contact;
import com.lar.service.IContactService;
import com.lar.validatorcontactdata.TelephoneValidator;

public class JDBCContactServiceImpl implements IContactService {

	@Autowired
	TelephoneValidator telephoneValidator;

	@Autowired
	private IJPAContactDAO iJPAcontactDao;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public Contact getContactBy(Long id) {
		return iJPAcontactDao.findOne(id);
	}

	@Override
	public Iterable<Contact> getAllContacts() {
		return iJPAcontactDao.findAll();
	}

	@Override
	public Contact save(Contact contact) {
		if (telephoneValidator.checkNumber(contact.getMobilePhone())) {
			throw new NullPointerException("Incorrect phone number");
		}
		authenticationService.setUserIdForContact(contact);
		contact.setMobilePhone(telephoneValidator.createNumber(contact.getMobilePhone()));
		return iJPAcontactDao.save(contact);
	}

	@Override
	public List<Contact> sortContctsByName() {
		return iJPAcontactDao.findAllByOrderByNameAsc();
	}

	@Override
	public List<Contact> sortContctsByTelephone() {
		return iJPAcontactDao.findAllByOrderByMobilePhoneAsc();
	}

	@Override
	public void deleteContact(Long contactId) {
		iJPAcontactDao.delete(contactId);
	}

	@Override
	public void deleteContacts() {
		iJPAcontactDao.deleteAll();
	}

	@Override
	public List<Contact> findContactsBy(Long userId) {
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
