package com.lardi.DAO;

import java.util.List;

import com.lardi.entity.User;

public interface IUserDAO {

	User getUser(int userId);

	List<User> getAllUser();

	void save(User user);

	void orderByName();

	void orderById();

	void delete(int userId);

	void createUserTableIfNotExist();

	void deleteAll();

	void alterCurrentContactId();

	User findUserByLogin(String userLogin);

}
