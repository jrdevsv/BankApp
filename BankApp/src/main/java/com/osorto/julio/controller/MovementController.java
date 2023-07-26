package com.osorto.julio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osorto.julio.dto.MovementsDTO;
import com.osorto.julio.dto.PersonDTO;
import com.osorto.julio.model.Person;
import com.osorto.julio.service.MovementService;
import com.osorto.julio.service.PersonService;

@RestController
@RequestMapping("/movement")
public class MovementController {

	@Autowired
	private MovementService service;

	@PostMapping(value = "/save")
	public ResponseEntity<Object> save(@RequestBody MovementsDTO dto) {
		return service.save(dto);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Object> update(@RequestBody MovementsDTO dto) {
		return service.update(dto);
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> delete(@RequestParam int id) {
		return service.delete(id);
	}

	@GetMapping(value = "/findById")
	public ResponseEntity<Object> findById(@RequestParam int id) {
		return service.findById(id);
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<Object> findAll() {
		return service.findAll();
	}

}
