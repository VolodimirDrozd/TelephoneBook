package com.lardi.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lardi.DAO.IUserDAO;
import com.lardi.entity.User;

public class UserDAOImpl implements IUserDAO {

	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		createUserTableIfNotExist();
	}

	@Override
	public void createUserTableIfNotExist() {
		String sql = "create table if not exists Users (user_id INT NOT NULL AUTO_INCREMENT, login varchar(255), password varchar(255), name varchar(255), surname varchar(255), patronymic varchar(255),PRIMARY KEY (user_id)) ";
		jdbcTemplate.execute(sql);
	}

	@Override
	public User getUser(int userId) {
		String sql = "SELECT * FROM Users WHERE user_id = ?";
		User user = (User) jdbcTemplate.queryForObject(sql, new Object[] { userId },
				new BeanPropertyRowMapper(User.class));
		return user;
	}

	@Override
	public List<User> getAllUser() {
		String sql = "SELECT * FROM Users";
		List<User> listUser = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
		return listUser;
	}

	@Override
	public void save(User user) {
		String sql = "INSERT INTO Users (login, password, name, surname , patronymic) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getName(), user.getSurname(),
				user.getPatronymic());
	}

	@Override
	public void orderByName() {
		String sql = "SELECT * FROM Users ORDER BY name";
		jdbcTemplate.execute(sql);
	}

	@Override
	public void orderById() {
		String sql = "SELECT * FROM Users ORDER BY user_id";
		jdbcTemplate.execute(sql);
	}

	@Override
	public void delete(int userId) {
		String sql = "DELETE FROM Users WHERE user_id=?";
		jdbcTemplate.update(sql, userId);

	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM Users";
		jdbcTemplate.update(sql);

	}

	@Override
	public void alterCurrentContactId() {
		String sql = "ALTER TABLE Users AUTO_INCREMENT = 1";
		jdbcTemplate.update(sql);

	}

	@Override
	public User findUserByLogin(String userLogin) {
		String sql = "SELECT * FROM Users WHERE login=?;";
		User user = (User) jdbcTemplate.queryForObject(sql, new Object[] { userLogin },
				new BeanPropertyRowMapper(User.class));
		return user;
	}

}
