package com.bootcamp.web.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.bootcamp.web.models.User;
import com.bootcamp.web.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepo;
	
	public UserService(UserRepository ur) {
		this.userRepo = ur;
	}
	
	//Register the user and hash their password
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return this.userRepo.save(user);
	}
	
	//Find user in database by email
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public boolean authenticateUser(String email, String password) {
		//Find user by email
		User u = userRepo.findByEmail(email);
		
		//If u is null, then the user doesn't exist, return false. Else, return the user
		if(u == null) {
			return false;
		//If u is a user, then check the password with the hashed password
		} else {
			if(BCrypt.checkpw(password, u.getPassword()))
				return true;
			else
				return false;
		}
	}

}
