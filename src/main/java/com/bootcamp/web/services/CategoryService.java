package com.bootcamp.web.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.web.models.Category;
import com.bootcamp.web.repositories.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository cateRepo;
	public CategoryService(CategoryRepository tr) {
		this.cateRepo = tr;
	}
	public void createCategory(Category c) {
		cateRepo.save(c);
	}
	public List<Category> getAll(){
		return cateRepo.findAll();
	}
	
}
