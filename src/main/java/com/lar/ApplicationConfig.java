package com.lar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lar.dao.JsonContactDAO;
import com.lar.dao.JsonUserDAO;
import com.lar.service.IContactService;
import com.lar.service.IUserService;
import com.lar.seviceimpl.JDBCContactServiceImpl;
import com.lar.seviceimpl.JDBCUserServiceImpl;
import com.lar.seviceimpl.JsonContactServiceImpl;
import com.lar.seviceimpl.JsonUserServiceImpl;

@SpringBootApplication()
public class ApplicationConfig {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationConfig.class, args);
	}

	@Value("${property}")
	private String property;

	@Bean
	public JsonContactDAO getJsonDAO() {
		return new JsonContactDAO();
	}

	@Bean
	public JsonUserDAO getUserDAO() {
		return new JsonUserDAO();
	}

	@Bean
	public IUserService getJsonUserService() {
		if (property.equals("jdbc")) {
			return new JDBCUserServiceImpl();
		} else
			return new JsonUserServiceImpl();
	}

	@Bean
	public IContactService contactService() {
		if (property.equals("jdbc")) {
			return new JDBCContactServiceImpl();
		} else {
			return new JsonContactServiceImpl();
		}
	}

}
