package com.lar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.lar.daoImplementation.JPAContactServiceImpl;
import com.lar.daoImplementation.JPAUserServiceImpl;
import com.lar.daoImplementation.JSONContactServiceImpl;
import com.lar.daoImplementation.JSONUserServiceImpl;
import com.lar.service.IContactService;
import com.lar.service.IUserService;

@SpringBootApplication()
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Value("${property}")
	String property;

	@Bean
	public IUserService getJsonUserService() {
		if (property.equals("jdbc")) {
			return new JPAUserServiceImpl();
		} else
			return new JSONUserServiceImpl();
	}
	@Bean
	public IContactService contactService() {
		if (property.equals("jdbc")) {
			return new JPAContactServiceImpl();
		} else {
			return new JSONContactServiceImpl();
		}

	}
}
