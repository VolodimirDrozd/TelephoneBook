package com.lardi.DAO;

import java.util.List;

import com.lardi.entity.Contact;

public interface IContactsDAO {

	Contact getContact(int contactId);

	List<Contact> getAllContacts();

	List<Contact> getAllContactsByUserId(Integer integer);

	void save(Contact contact);

	void orderByName();

	void orderByPhone();

	void delete(int contactId);

	void createContactTableIfNotExist();

	void deleteAll();

	void alterCurrentContactId();

	void updateContact(Integer contactId, String name, String surname, String telephone, String homeTelephone,
			String address, String email);

	Contact findContactByContactId(String contactId);

}
