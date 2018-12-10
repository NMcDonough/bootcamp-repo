package com.bootcamp.web.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bootcamp.web.models.Bootcamp;
import com.bootcamp.web.models.Category;
import com.bootcamp.web.repositories.BootcampRepository;

@Service
public class BootcampService {
	private final BootcampRepository bootRepo;
	
	public BootcampService(BootcampRepository br) {
		this.bootRepo = br;
	}
	
	public List<Bootcamp> getAll(){
		return bootRepo.findAll();
	}

	public void createBootcamp(@Valid Bootcamp bootcamp) {
		this.bootRepo.save(bootcamp);
	}
	
	public void deleteBootcamp(Long id) {
		Optional<Bootcamp> b = bootRepo.findById(id);
		if(b.get() != null)
			bootRepo.delete(b.get());
	}
}
