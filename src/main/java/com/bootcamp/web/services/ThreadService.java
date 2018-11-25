package com.bootcamp.web.services;

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
}
