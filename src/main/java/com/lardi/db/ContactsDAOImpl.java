package com.lardi.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lardi.DAO.IContactsDAO;
import com.lardi.entity.Contact;

public class ContactsDAOImpl implements IContactsDAO {

	private JdbcTemplate jdbcTemplate;

	public ContactsDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		createContactTableIfNotExist();
	}

	@Override
	public void createContactTableIfNotExist() {
		String sql = "create table if not exists Contacts (contact_id INT NOT NULL AUTO_INCREMENT, name varchar(255),surname varchar(255), telephone varchar(255) NOT NULL UNIQUE, homeTelephone varchar(255), address varchar(255), email varchar(255), user_id varchar(255),PRIMARY KEY (contact_id)) ";
		jdbcTemplate.execute(sql);
	}

	public Contact getContact(int contactId) {
		String sql = "SELECT * FROM Contacts WHERE contact_id = ?";
		Contact contact = (Contact) jdbcTemplate.queryForObject(sql, new Object[] { contactId },
				new BeanPropertyRowMapper(Contact.class));
		return contact;
	}

	@Override
	public List<Contact> getAllContacts() {
		String sql = "SELECT * FROM Contacts";
		List<Contact> listContact = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Contact.class));
		return listContact;
	}

	@Override
	public void save(Contact contact) {
		String sql = "INSERT INTO Contacts (name, surname, telephone, homeTelephone, address, email, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, contact.getName(), contact.getSurname(), contact.getTelephone(),
				contact.getHomeTelephone(), contact.getAddress(), contact.getEmail(), contact.getUserId());
	}

	@Override
	public void orderByName() {
		String sql = "SELECT * FROM Contacts ORDER BY name";
		jdbcTemplate.execute(sql);
	}

	@Override
	public void orderByPhone() {
		String sql = "SELECT * FROM Contacts ORDER BY telephone";
		jdbcTemplate.execute(sql);
	}

	@Override
	public void delete(int contactId) {
		String sql = "DELETE FROM Contacts WHERE contact_id=?";
		jdbcTemplate.update(sql, contactId);
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM Contacts";
		jdbcTemplate.update(sql);

	}

	@Override
	public void alterCurrentContactId() {
		String sql = "ALTER TABLE Contacts AUTO_INCREMENT = 1";
		jdbcTemplate.update(sql);

	}

	@Override
	public void updateContact(Integer contactId, String name, String surname, String telephone, String homeTelephone,
			String address, String email) {
		String sql = "UPDATE Contacts SET name = ?, surname=?, telephone=?, homeTelephone=?, address=?, email=? WHERE contact_id =?;";
		jdbcTemplate.update(sql, name, surname, telephone, homeTelephone, address, email, contactId);

	}

	@Override
	public Contact findContactByContactId(String contactId) {
		String sql = "SELECT * FROM Contacts WHERE contact_Id=?;";
		Contact contact = (Contact) jdbcTemplate.queryForObject(sql, new Object[] { contactId },
				new BeanPropertyRowMapper(Contact.class));
		return contact;
	}

	@Override
	public List<Contact> getAllContactsByUserId(Integer userId) {
		String sql = "SELECT * FROM Contacts WHERE user_Id=?";
		List<Contact> listContact = (List<Contact>) jdbcTemplate.query(sql, new Object[] { userId },
				new BeanPropertyRowMapper(Contact.class));
		return listContact;
	}

}
