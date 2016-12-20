package com.lar.daoImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lar.dao.IJPAContactDAO;
import com.lar.entity.Contact;
import com.lar.service.IContactService;

public class JPAContactServiceImpl implements IContactService {

	@Autowired
	private IJPAContactDAO iJPAcontactDao;

	public Contact getContactById(Long id) {
		return iJPAcontactDao.findOne(id);
	}

	public Iterable<Contact> getAllContacts() {
		return iJPAcontactDao.findAll();
	}

	public Contact save(Contact contact) {
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

	// TODO : better add references ManyToOne
	public List<Contact> findAllByUserId(Long userId) {
		return iJPAcontactDao.findAllByUserId(userId);
	}

}
