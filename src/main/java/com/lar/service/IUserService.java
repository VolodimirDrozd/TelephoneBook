package com.lar.service;

import com.lar.entity.User;

public interface IUserService {

	public User findUserById(Long id);

	public Iterable<User> getAllUsers();

	public User save(User user);

	public void deleteAll();

	public User findUserByLogin(String login);
}
