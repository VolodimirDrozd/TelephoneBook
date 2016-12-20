package com.lar.service;

import java.util.List;

import com.lar.entity.Contact;

public interface IContactService {

	public Contact getContactById(Long id);

	public Iterable<Contact> getAllContacts();

	public Contact save(Contact contact);

	public List<Contact> findAllOrderByName();

	public List<Contact> findAllOrderByTelephone();

	// Use for junit
	public void deleteAll();

	// TODO : better add references ManyToOne
	public List<Contact> findAllByUserId(Long userId);

	void delete(Long id);

}
