package com.stg.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.exception.VbbsException;
import com.stg.user.entity.User;
import com.stg.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User getUserByUserId(int userId) {

		Optional<User> optional = userRepo.findById(userId);

		return optional.get();
	}

	@Override
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}

	@Override
	public User login(String emailId, String password) {
		
		if(emailId!=null && password != null) {
			User tempUser = userRepo.findByEmailId(emailId);

			if (password.equals(tempUser.getPassword())) {

				return tempUser;
			} else {  
				throw new VbbsException("Wrong Password!");
			}
		}else {
			throw new VbbsException("Required Input");
		}
	}
	
	
 
}
