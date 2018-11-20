package com.bootcamp.web.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.web.models.Bootcamp;
import com.bootcamp.web.models.Review;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAll(); 
    List<Review> findByBootcamp(Bootcamp bootcamp);
}