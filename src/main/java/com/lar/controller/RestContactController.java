package com.lar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lar.dto.ContactDTO;
import com.lar.entity.Contact;
import com.lar.service.IContactService;

@RestController
@RequestMapping("/api/contact")
public class RestContactController {

	@Autowired
	private IContactService contactService;

	@PostMapping("/save")
	public Contact save(@RequestBody ContactDTO contact) {
		return contactService.save(contact.build());
	}

	@GetMapping("/get/id")
	public Contact getContactBy(Long contactId) {
		return contactService.getContactBy(contactId);
	}

	@GetMapping("/get")
	public Iterable<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}

	@GetMapping("/order/name")
	public List<Contact> sortContactsByName() {
		return contactService.sortContctsByName();
	}

	@GetMapping("/order/phone")
	public List<Contact> sortContactsByTelephone() {
		return contactService.sortContctsByTelephone();
	}

	@GetMapping("/get/userId")
	public List<Contact> findAllContactsBy(Long userId) {
		return contactService.findContactsBy(userId);
	}

}
