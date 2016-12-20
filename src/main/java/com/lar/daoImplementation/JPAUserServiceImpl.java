package com.lar.daoImplementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.lar.dao.IJPAUserDAO;
import com.lar.entity.User;
import com.lar.service.IUserService;

public class JPAUserServiceImpl implements IUserService {

	@Autowired
	private IJPAUserDAO iJPAUserDAO;

	public User findUserById(Long id) {
		return iJPAUserDAO.findOne(id);
	}

	public Iterable<User> getAllUser() {
		return iJPAUserDAO.findAll();
	}

	public User save(User user) {
		return iJPAUserDAO.save(user);
	}

	public void delete(Long id) {
		iJPAUserDAO.delete(id);
	}

	public void deleteAll() {
		iJPAUserDAO.deleteAll();
	}

	public User findUserByLogin(String login) {
		return iJPAUserDAO.findOneByLogin(login);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return iJPAUserDAO.findAll();
	}

}
