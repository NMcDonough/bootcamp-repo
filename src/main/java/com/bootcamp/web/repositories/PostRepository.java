package com.bootcamp.web.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.web.models.Post;


@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll(); 
    List<Post> findByThread(Thread thread);
}