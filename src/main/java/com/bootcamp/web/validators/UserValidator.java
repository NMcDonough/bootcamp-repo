package com.bootcamp.web.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bootcamp.web.models.User;
import com.bootcamp.web.repositories.UserRepository;

@Component
public class UserValidator implements Validator {
	
	private final UserRepository userRepo;
	
	public UserValidator(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }
        if (userRepo.findByEmail(user.getEmail()) !=null ) {
            errors.rejectValue("email", "Taken");
        }
    }
}