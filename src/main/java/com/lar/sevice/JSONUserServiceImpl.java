package com.lar.sevice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lar.entity.User;
import com.lar.service.IUserService;

public class JSONUserServiceImpl implements IUserService {

	@Value("${pathUserDB}")
	String fileUserDBPath;

	File file;
	Gson gson;

	@PostConstruct
	public void innit() {
		file = new File(fileUserDBPath);
		gson = new Gson();
	}

	@Override
	public User findUserById(Long id) {
		Iterable<User> userList = getAllUsers();
		for (User user : userList) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public Iterable<User> getAllUsers() {
		User[] arrayUser = null;

		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JsonReader jsonReader = new JsonReader(reader);
		jsonReader.setLenient(true);
		arrayUser = gson.fromJson(jsonReader, User[].class);
		if (arrayUser == null) {
			return null;
		}
		return Arrays.asList(arrayUser);
	}

	@Override
	public User save(User user) {
		try {
			List<User> list = new ArrayList<>();
			Iterable<User> listSavedUsers = getAllUsers();
			long contactId = 1;
			if (listSavedUsers != null) {
				for (User userFromFile : listSavedUsers) {
					list.add(userFromFile);
					contactId++;
				}
			}
			Writer osWriter = new FileWriter(file, false);
			user.setId(contactId);
			list.add(user);
			gson.toJson(list, osWriter);
			osWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User findUserByLogin(String login) {
		Iterable<User> contactList = getAllUsers();
		for (User user : contactList) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}

		return null;
	}

	@Override
	public void deleteAll() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


}
