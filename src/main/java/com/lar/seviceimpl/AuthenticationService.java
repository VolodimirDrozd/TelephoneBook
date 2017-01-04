package com.lar.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lar.entity.Contact;
import com.lar.entity.CustomUserDetails;
import com.lar.entity.User;
import com.lar.service.IUserService;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return new CustomUserDetails(userService.findUserBy(login));
	}

	public void setUserIdForContact(Contact contact) {
		try {
			Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (object != null) {
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				contact.setUserId(user.getId());
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage() + "User is not registated");
		}
	}

}
