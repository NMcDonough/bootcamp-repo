package com.bootcamp.web.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.web.models.User;
import com.bootcamp.web.services.UserService;
import com.bootcamp.web.validators.UserValidator;

@Controller
public class MainController {
	
	private final UserService userService;
    private final UserValidator userValidator;
	
	public MainController(UserService us, UserValidator uv) {
		this.userService = us;
		this.userValidator = uv;
	}
	
	//Home page; if 'user' value found in session, add user data to 'model' - for persistent login
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String serve(HttpSession session, Model model) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("user", session.getAttribute("user"));
		}
		return "index.jsp";
	}
	
	//Login page for testing login and registration
	@RequestMapping("/login")
	public String logreg(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("user", new User());
		return "logreg.jsp";
	}
	
	//Post method for handling user registration
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String Register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userValidator.validate(user, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			model.addAttribute("user", new User());
			return "redirect:/login";
		}
		
		User u = userService.registerUser(user);
		session.setAttribute("user", u.getId());
		
		return "redirect:/";
	}
	
	//Post method for logging in
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		
		if(userService.authenticateUser(email, password)) {
    		User user = userService.findByEmail(email);
//    		Long id = user.getId();
    		session.setAttribute("user", user);
    		return "redirect:/";
    	} else {
    		model.addAttribute("error", "Could not log you in!");
    		model.addAttribute("user", new User());
    		return "index.jsp";
    	}
	}
	
	//Logout method
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	//Error page - not currently functional
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String errorPage() {
		return "errorPage.jsp";
	}
}
