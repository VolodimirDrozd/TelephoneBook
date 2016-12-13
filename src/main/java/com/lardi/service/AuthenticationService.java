package com.lardi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lardi.DAO.IUserDAO;
import com.lardi.entity.CustomUserDetails;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	IUserDAO iUserDAO;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		return new CustomUserDetails(iUserDAO.findUserByLogin(arg0));
	}

}
