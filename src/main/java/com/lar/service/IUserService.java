package com.lar.service;

import com.lar.entity.User;

public interface IUserService {

	public User save(User user);
	
	public Iterable<User> getAllUsers();
	
	public User findUserBy(Long userId);
	
	public User findUserBy(String userlogin);

	public void deleteAllUsers();


}
