package com.example.springBootHibernateDemo.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equals("Ranjit")) {
			System.out.println("Username validation started with name Ranjit");
			return new User(username, "Ranjit123", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Exception : User not found!");
		}
	}
}
