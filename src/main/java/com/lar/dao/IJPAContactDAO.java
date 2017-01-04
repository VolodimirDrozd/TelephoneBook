package com.lar.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lar.entity.Contact;

public interface IJPAContactDAO extends CrudRepository<Contact, Long> {

	List<Contact> findAllByOrderByNameAsc();

	List<Contact> findAllByOrderByMobilePhoneAsc();

}