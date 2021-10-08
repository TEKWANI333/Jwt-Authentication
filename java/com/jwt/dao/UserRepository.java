package com.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserName(String name);
	
}
