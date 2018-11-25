package com.bootcamp.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.web.repositories.ThreadRepository;
import com.bootcamp.web.models.Thread;

@Service
public class ThreadService {
	
	private final ThreadRepository threadRepo;
	
	public ThreadService(ThreadRepository tr) {
		this.threadRepo = tr;
	}
	
	public void createThread (Thread t) {
		threadRepo.save(t);
	}

	public List<Thread> getAll() {
		return threadRepo.findAll();
	}
	
	public void deleteThread(Long id) {
		Optional<Thread> thread = threadRepo.findById(id);
		if(thread.get() != null)
			threadRepo.delete(thread.get());
	}
}
