package com.lardi.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lardi.DAO.IContactsDAO;
import com.lardi.entity.Contact;
import com.lardi.entity.CustomUserDetails;
import com.lardi.entity.User;

@Controller
public class ContactConroller {

	@Autowired
	@Qualifier("ContactsDAO")
	private IContactsDAO iContactsDAO;

	@RequestMapping(value = "/addContact", method = RequestMethod.GET)
	public String addContact() {
		return "addContact";
	}

	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String registrationUserDataInDB(@RequestParam String name, @RequestParam String surname,
			@RequestParam String patronymic, @RequestParam String telephone, @RequestParam String homeTelephone,
			@RequestParam String address, @RequestParam String email) throws SQLException {
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User user = customUserDetails.getUser();
		Contact cantact = new Contact(user.getId(), name, surname, patronymic, telephone, homeTelephone, address, email,
				1);
		iContactsDAO.save(cantact);
		return "redirect:/getContacts";
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public String editContact1(Model model, @RequestParam String contactId) {
		model.addAttribute("contact", iContactsDAO.findContactByContactId(contactId));
		model.addAttribute("contactId", contactId);
		return "editContact";
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.POST)
	public String editContact(@RequestParam Integer contactId, @RequestParam String name, @RequestParam String surname,
			@RequestParam String telephone, @RequestParam String homeTelephone, @RequestParam String address,
			@RequestParam String email) throws SQLException {
		iContactsDAO.updateContact(contactId, name, surname, telephone, homeTelephone, address, email);
		return "redirect:/getContacts";
	}

	@RequestMapping(value = "/getContacts", method = RequestMethod.GET)
	public ModelAndView getAllContact() throws SQLException {
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User user = customUserDetails.getUser();
		ModelAndView model = new ModelAndView("telephoneBook");
		model.addObject("contacts", iContactsDAO.getAllContactsByUserId(user.getId()));
		return model;
	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public String editContact1(@RequestParam String contactId) {
		iContactsDAO.delete(Integer.valueOf(contactId));
		return "redirect:/getContacts";
	}

}
