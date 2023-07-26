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

import com.osorto.julio.dto.AccountDTO;
import com.osorto.julio.dto.MovementsDTO;
import com.osorto.julio.model.Person;
import com.osorto.julio.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> save(@RequestBody AccountDTO dto) {
		return service.save(dto);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Object> update(@RequestBody AccountDTO dto) {
		return service.update(dto);
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> delete(@RequestParam String id) {
		return service.delete(id);
	}

	@GetMapping(value = "/findById")
	public ResponseEntity<Object> findById(@RequestParam String id) {
		return service.findById(id);
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<Object> findAll() {
		return service.findAll();
	}

}
