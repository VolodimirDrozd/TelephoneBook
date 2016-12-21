package com.lar.dao;

import org.springframework.data.repository.CrudRepository;

import com.lar.entity.User;

public interface IJPAUserDAO extends CrudRepository<User, Long> {

	User findOneByLogin(String login);

}