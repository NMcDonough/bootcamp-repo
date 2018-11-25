package com.bootcamp.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.web.models.Thread;


@Repository
public interface ThreadRepository extends CrudRepository<Thread, Long> {
    List<Thread> findAll(); 
}