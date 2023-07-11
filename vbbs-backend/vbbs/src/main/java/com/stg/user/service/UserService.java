package com.stg.user.service;

import java.util.List;

import com.stg.user.entity.User;

public interface UserService {
	public abstract User createUser(User user);

	public abstract User getUserByUserId(int userId);

	public abstract List<User> getAllUsers();
	
	public abstract User login(String emailId,String password);
	
}
