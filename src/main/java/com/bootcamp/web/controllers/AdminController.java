package com.bootcamp.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bootcamp.web.models.Category;
import com.bootcamp.web.models.User;
import com.bootcamp.web.services.CategoryService;
import com.bootcamp.web.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final CategoryService cServ;
	private final UserService uServ;
	
	public AdminController(UserService uServ, CategoryService cServ) {
		this.cServ = cServ;
		this.uServ = uServ;
	}
	@RequestMapping("/categories")
	public String getCategories(Model model){
		List<Category> cates = cServ.getAll();
		model.addAttribute("categories", cates);
		return "categories";
	}
	@RequestMapping(value="/categories/new", method=RequestMethod.GET)
	public String newCategory(@ModelAttribute("category") Category category, Model model, HttpSession session) {		
		Long id =  (Long) session.getAttribute("user");
		if (id == null) {			
			return "redirect:/";			
		}
		User u = uServ.findById(id);
		if(u.getUserlevel()<5) {
			return "redirect:/";			
		}
		model.addAttribute("category", new Category());
		return "newcategory";
	}
	@RequestMapping(value="/categories/new", method=RequestMethod.POST)
	public String Register(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, HttpSession session) {		
		Long id =  (Long) session.getAttribute("user");
		if (id == null) {			
			return "redirect:/";			
		}
		User u = uServ.findById(id);
		if(u.getUserlevel()<5) {
			return "redirect:/";			
		}
		if(result.hasErrors()) {
			model.addAttribute("category", new Category());
			return "redirect:/admin/categories/new";
		}
		cServ.createCategory(category);
		return "redirect:/admin/categories";
	}
	
}
