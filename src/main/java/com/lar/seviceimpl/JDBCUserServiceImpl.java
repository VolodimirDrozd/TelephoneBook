package com.lar.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lar.dao.IJPAUserDAO;
import com.lar.entity.User;
import com.lar.service.IUserService;

public class JDBCUserServiceImpl implements IUserService {

	@Autowired
	private IJPAUserDAO iJPAUserDAO;

	@Override
	public User findUserBy(Long userId) {
		return iJPAUserDAO.findOne(userId);
	}

	@Override
	public User save(User user) {
		return iJPAUserDAO.save(user);
	}

	@Override
	public void deleteAllUsers() {
		iJPAUserDAO.deleteAll();
	}

	@Override
	public User findUserBy(String userLogin) {
		return iJPAUserDAO.findOneByLogin(userLogin);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return iJPAUserDAO.findAll();
	}

}
