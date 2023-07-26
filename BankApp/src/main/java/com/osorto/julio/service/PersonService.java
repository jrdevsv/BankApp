package com.osorto.julio.service;

import org.springframework.http.ResponseEntity;

import com.osorto.julio.dto.PersonDTO;

public interface PersonService {

	ResponseEntity<Object> savePerson(PersonDTO dto);

	ResponseEntity<Object> updatePerson(PersonDTO dto);

	ResponseEntity<Object> deletePerson(int id);

	ResponseEntity<Object> findById(int id);

	ResponseEntity<Object> findAll();

}
