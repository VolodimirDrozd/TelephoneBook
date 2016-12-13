package com.lardi.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.lardi.db.ContactsDAOImpl;
import com.lardi.db.UserDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.lardi" })
@PropertySource("classpath:config.properties")
public class AppConfig {

	@Value("${dbaseUser}")
	protected String userName;

	@Value("${dbasePassword}")
	protected String userPassword;

	@Value("${driverClass}")
	protected String driverClass;

	@Value("${URL}")
	protected String url;

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(userPassword);
		return dataSource;
	}

	@Bean(name = "alternativeDataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(driverClass);
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(userName);
		driverManagerDataSource.setPassword(userPassword);
		return driverManagerDataSource;
	}

	@Bean(name = "ContactsDAO")
	public ContactsDAOImpl getIContactsDAO() {
		return new ContactsDAOImpl(getDataSource());
	}

	@Bean(name = "UsersDAO")
	public UserDAOImpl getIUserDAO() {
		return new UserDAOImpl(getDataSource());
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
