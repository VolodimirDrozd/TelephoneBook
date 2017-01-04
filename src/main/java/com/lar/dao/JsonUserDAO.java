package com.lar.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.lar.entity.User;

public class JsonUserDAO extends JsonDAO {

	private static long userId;
	private File dbUsersFile;
	private ArrayList<User> savedUsers;

	@Value("${pathUserDB}")
	private String pathUserDBFile;

	@PostConstruct
	protected void innit() {
		try {
			dbUsersFile = new File(pathUserDBFile);
			dbUsersFile.createNewFile();
			subtractionUserId();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Iterable<User> getAllUsers() {
		savedUsers = new ArrayList<User>();
		User[] arrayUser = gson.fromJson(createJsonReader(), User[].class);
		if (arrayUser != null) {
			return Arrays.asList(arrayUser);
		} else {
			return savedUsers;
		}
	}

	public User save(User user) {
		user.setId(userId++);
		savedUsers = Lists.newArrayList(getAllUsers());
		List<User> newUserlistForSave = new ArrayList<>(savedUsers);
		newUserlistForSave.add(user);
		writeToDBFile(newUserlistForSave);
		return user;
	}

	public User findUserBy(Long userId) {
		for (User user : getAllUsers()) {
			if (user.getId().equals(userId)) {
				return user;
			}
		}
		return null;
	}

	public User findUserBy(String userLogin) {
		for (User user : getAllUsers()) {
			if (user.getLogin().equals(userLogin)) {
				return user;
			}
		}
		return null;
	}

	@Override
	protected File getFileWithEntity() {
		return dbUsersFile;
	}

	private void subtractionUserId() {
		List<User> quantityUserInFileDB = Lists.newArrayList(getAllUsers());
		userId = quantityUserInFileDB.size() > 0 ? quantityUserInFileDB.size() + 1 : 1;
	}
}
