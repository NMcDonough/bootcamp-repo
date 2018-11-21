package com.bootcamp.web.services;

import java.util.List;
import java.util.Optional;

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
	
	public void deleteCategory(Long id) {
		Optional<Category> c = cateRepo.findById(id);
		if(c.get() != null)
			cateRepo.delete(c.get());
	}
}
