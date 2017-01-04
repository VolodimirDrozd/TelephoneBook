package com.lar.seviceimpl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.lar.dao.JsonUserDAO;
import com.lar.entity.User;
import com.lar.service.IUserService;

public class JsonUserServiceImpl implements IUserService {

	@Value("${pathUserDB}")
	String fileUserDBPath;

	@Autowired
	JsonUserDAO jsonUserDAO;

	File file;

	@Override
	public User findUserBy(Long userId) {
		return jsonUserDAO.findUserBy(userId);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return jsonUserDAO.getAllUsers();
	}

	@Override
	public User save(User user) {
		return jsonUserDAO.save(user);
	}

	@Override
	public User findUserBy(String userLogin) {
		return jsonUserDAO.findUserBy(userLogin);
	}

	@Override
	public void deleteAllUsers() {
		jsonUserDAO.deleteAll();
	}

}
