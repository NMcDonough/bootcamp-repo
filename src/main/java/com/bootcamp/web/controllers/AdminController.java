package com.bootcamp.web.controllers;

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

import com.bootcamp.web.models.Bootcamp;
import com.bootcamp.web.models.Category;
import com.bootcamp.web.models.User;
import com.bootcamp.web.models.Thread;
import com.bootcamp.web.services.BootcampService;
import com.bootcamp.web.services.CategoryService;
import com.bootcamp.web.services.ThreadService;
import com.bootcamp.web.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final CategoryService cServ;
	private final UserService uServ;
	private final ThreadService tServ;
	private final BootcampService bServ;
	
	public AdminController(UserService uServ, CategoryService cServ, ThreadService tServ, BootcampService bServ) {
		this.cServ = cServ;
		this.uServ = uServ;
		this.tServ = tServ;
		this.bServ = bServ;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public String categories(@ModelAttribute("category") Category category, Model model, HttpSession session) {		
		Long id =  (Long) session.getAttribute("user");
		if (id == null) {			
			return "redirect:/";			
		}
		User u = uServ.findById(id);
		u.setPassword(null);
		if(u.getUserlevel()<5) {
			return "redirect:/";			
		}
		List<Category> cates = cServ.getAll();
		model.addAttribute("categories", cates);
		model.addAttribute("category", new Category());
		model.addAttribute("user", u);
		return "categories";
	}
	
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, HttpSession session) {		
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
		//Test
		cServ.createCategory(category);
		return "redirect:/admin/categories";
	}
	
	@RequestMapping("categories/delete/{id}")
	public String deleteCategory(HttpSession session, @PathVariable("id") Long id) {
		User u = uServ.findById((Long) session.getAttribute("user"));
		if(id == null)
			return "redirect:/";
		if(u.getUserlevel() == 5)
			cServ.deleteCategory(id);
		return "redirect:/admin/categories";
	}
	
	@RequestMapping(value="/threads", method=RequestMethod.GET)
	public String threads(@ModelAttribute("thread") Thread thread, Model model, HttpSession session) {		
		Long id =  (Long) session.getAttribute("user");
		if (id == null) {			
			return "redirect:/";			
		}
		User u = uServ.findById(id);
		if(u.getUserlevel()<5) {
			return "redirect:/";			
		}
		List<Thread> threads = tServ.getAll();
		model.addAttribute("threads", threads);
		model.addAttribute("thread", new Thread());
		return "threads";
	}
	
	
	@RequestMapping(value="/threads", method=RequestMethod.POST)
	public String createThread(@Valid @ModelAttribute("thread") Thread thread, BindingResult result, Model model, HttpSession session) {		
		Long id =  (Long) session.getAttribute("user");
		if (id == null) {	
			return "redirect:/";
		}	
		User u = uServ.findById(id);
		if(u.getUserlevel()<5) {
			return "redirect:/";			
		}
		if(result.hasErrors()) {
			model.addAttribute("thread", new Thread());
			return "redirect:/admin/categories/new";
		}
		tServ.createThread(thread);
		return "redirect:/admin/categories";
	}
	
	@RequestMapping("threads/delete/{id}")
	public String deleteThread(HttpSession session, @PathVariable("id") Long id) {
		User u = uServ.findById((Long) session.getAttribute("user"));
		if(id == null)
			return "redirect:/";
		if(u.getUserlevel() == 5)
			tServ.deleteThread(id);
		return "redirect:/admin/threads";
	}
	
	@RequestMapping("bootcamps")
	public String bootcamps(HttpSession session, @ModelAttribute("bootcamp") Bootcamp bootcamp, Model model) {
		User u;
		if(session.getAttribute("user") != null) {
			u = uServ.findById((Long) session.getAttribute("user"));
			u.setPassword(null);// Set password to null so no information on the users pw can be derived from session data stored on the browser
			model.addAttribute("user", u);
		} else {
			return "redirect:/";
		}
		if(u.getUserlevel() < 5) {
			return "redirect:/";
		}
		List<Bootcamp> bootcamps = bServ.getAll();
		model.addAttribute("bootcamps", bootcamps);
		model.addAttribute("bootcamp", new Bootcamp());
		return "bootcamps";
	}
	
	@RequestMapping(value="bootcamps", method=RequestMethod.POST)
	public String addBootcamp(HttpSession session, @Valid @ModelAttribute("bootcamp") Bootcamp bootcamp, Model model, BindingResult result) {
		Long id =  (Long) session.getAttribute("user");
		if (id == null) {			
			return "redirect:/";			
		}
		User u = uServ.findById(id);
		if(u.getUserlevel()<5) {
			return "redirect:/";			
		}
		if(result.hasErrors()) {
			return "redirect:/admin/bootcamps";
		} else {
			bServ.createBootcamp(bootcamp);
			return "redirect:/admin/bootcamps";
		}
	}
	
	@RequestMapping(value="bootcamps/delete/{id}", method=RequestMethod.GET)
	public String delBootCamp(HttpSession session, @PathVariable("id") Long bootId, Model model) {
		Long id =  (Long) session.getAttribute("user");
		if (id == null) {			
			return "redirect:/";			
		}
		User u = uServ.findById(id);
		if(u.getUserlevel()<5) {
			return "redirect:/";			
		}
		bServ.deleteBootcamp(bootId);
		return "redirect:/admin/bootcamps";
	}
}
