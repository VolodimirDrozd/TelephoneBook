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
	public Contact getContactById(Long id) {
		return contactService.getContactById(id);
	}

	@GetMapping("/get")
	public Iterable<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}

	@GetMapping("/order/name")
	public List<Contact> findAllOrderByName() {
		return contactService.findAllOrderByName();
	}

	@GetMapping("/order/phone")
	public List<Contact> findAllOrderByTelephone() {
		return contactService.findAllOrderByTelephone();
	}

	@GetMapping("/get/userId")
	public List<Contact> findAllByUserId(Long userId) {
		return contactService.findAllByUserId(userId);
	}

}
