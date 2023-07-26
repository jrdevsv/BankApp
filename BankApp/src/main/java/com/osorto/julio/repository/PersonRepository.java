package com.osorto.julio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osorto.julio.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>  {
	
	List<Person> findAll();
		
}
