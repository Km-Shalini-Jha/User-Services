package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;


public interface UserService {
	// create user
	User saveUser(User user);
	
	// get all user
	List<User> getAllUser();
	
	//get single user for given userId
	User getUser(String userId);
	
	//TODO : delete
	String deleteUser(String userId);
	
	// TODO : UPDATE
	User updateUser(String userId);
}
