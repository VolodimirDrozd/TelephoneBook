package com.lar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lar.service.IContactService;
import com.lar.service.IUserService;
import com.lar.sevice.JDBCContactServiceImpl;
import com.lar.sevice.JDBCUserServiceImpl;
import com.lar.sevice.JSONContactServiceImpl;
import com.lar.sevice.JSONUserServiceImpl;

@SpringBootApplication()
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Value("${property}")
	private String property;

	@Bean
	public IUserService getJsonUserService() {
		if (property.equals("jdbc")) {
			return new JDBCUserServiceImpl();
		} else
			return new JSONUserServiceImpl();
	}

	@Bean
	public IContactService contactService() {
		if (property.equals("jdbc")) {
			return new JDBCContactServiceImpl();
		} else {
			return new JSONContactServiceImpl();
		}

	}
}
