package com.bootcamp.web.services;

import java.util.Optional;

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
		Optional<User> u = userRepo.findById((long)1);
		if(u.isPresent()) {
			user.setUserlevel(0);
		}
		else {
			user.setUserlevel(5);
		}
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return this.userRepo.save(user);
	}
	
	//Find user in database by email
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	public User findById(Long id) {
		Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			return u.get();
		}
		else {
			return null;
		}
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
