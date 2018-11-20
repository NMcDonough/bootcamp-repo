package com.bootcamp.web.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.web.models.Bootcamp;


@Repository
public interface BootcampRepository extends CrudRepository<Bootcamp, Long> {
    List<Bootcamp> findAll(); 
}