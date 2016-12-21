package com.lar.sevice;

import org.springframework.beans.factory.annotation.Autowired;

import com.lar.dao.IJPAUserDAO;
import com.lar.entity.User;
import com.lar.service.IUserService;

public class JDBCUserServiceImpl implements IUserService {

	@Autowired
	private IJPAUserDAO iJPAUserDAO;

	@Override
	public User findUserById(Long id) {
		return iJPAUserDAO.findOne(id);
	}

	@Override
	public User save(User user) {
		return iJPAUserDAO.save(user);
	}

	@Override
	public void deleteAll() {
		iJPAUserDAO.deleteAll();
	}

	@Override
	public User findUserByLogin(String login) {
		return iJPAUserDAO.findOneByLogin(login);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return iJPAUserDAO.findAll();
	}

}
