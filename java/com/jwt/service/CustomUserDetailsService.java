package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.dao.UserRepository;
import com.jwt.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 
			User user =this.userRepo.findByUserName(userName);
		if(user== null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No User Found");
		}
		return user;

	}
	
}
