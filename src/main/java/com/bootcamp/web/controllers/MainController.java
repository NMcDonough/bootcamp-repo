package com.bootcamp.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.web.models.Bootcamp;
import com.bootcamp.web.models.User;
import com.bootcamp.web.models.Category;
import com.bootcamp.web.services.BootcampService;
import com.bootcamp.web.services.CategoryService;
import com.bootcamp.web.services.UserService;
import com.bootcamp.web.validator.UserValidator;

@Controller
public class MainController {
	
	private final UserService userService;
    private final UserValidator userValidator;
    private final BootcampService bServ;
    private final CategoryService cServ;
	
	public MainController(UserService us, UserValidator uv, BootcampService bs, CategoryService cs) {
		this.userService = us;
		this.userValidator = uv;
		this.bServ = bs;
		this.cServ = cs;
	}
	
	//Home page; if 'user' value found in session, add user data to 'model' - for persistent login
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String serve(HttpSession session, Model model) {
		if(session.getAttribute("user") != null) {
			User u = userService.findById((Long) session.getAttribute("user"));
			u.setPassword(null);// Set password to null so no information on the users pw can be derived from session data stored on the browser
			model.addAttribute("user", u);
		}
		List<Bootcamp> bootcamps = bServ.getAll();
		model.addAttribute("bootcamps", bootcamps);
		return "index";
	}
	
	//Login page for testing login and registration
	@RequestMapping("/login")
	public String logreg(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("user", new User());
		return "logreg";
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
    		session.setAttribute("user", user.getId());
    		return "redirect:/";
    	} else {
    		model.addAttribute("error", "Could not log you in!");
    		model.addAttribute("user", new User());
    		model.addAttribute("newUser", new User());
    		return "logreg";
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
		return "errorPage";
	}
	
	@RequestMapping(value="/bootcamp/{name}/{id}")
	public String bootcamp(@PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user");
		Bootcamp bootcamp = bServ.findById(id);
		User u;
		if(userId != null) {
			u = userService.findById(userId);
			u.setPassword(null);
			model.addAttribute("user", u);
		}
		List<Category> categories = cServ.findAll();
		model.addAttribute("bootcamp", bootcamp);
		model.addAttribute("categories", categories);
		return "bootcamp";
	}
}
