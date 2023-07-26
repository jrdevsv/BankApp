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

import com.osorto.julio.dto.CustomerDTO;
import com.osorto.julio.dto.PersonDTO;
import com.osorto.julio.service.CustomerService;
import com.osorto.julio.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private CustomerService custService;
	
	@PostMapping(value="/save")
	public ResponseEntity<Object> save(@RequestBody PersonDTO dto){
		
		return  service.savePerson(dto);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<Object> update(@RequestBody PersonDTO dto){
		return  service.updatePerson(dto);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<Object> delete(@RequestParam int id){
		 return service.deletePerson(id);
	}
	
	@GetMapping(value="/findById")
	public ResponseEntity<Object> findById(@RequestParam int id){
		return service.findById(id);
	}
	
	@GetMapping(value ="/findAll")
	public ResponseEntity<Object> findAll(){
		return service.findAll();
	}
	
	@PostMapping(value="/saveCustomer")
	public ResponseEntity<Object> save(@RequestBody CustomerDTO dto){
		return  custService.saveCustomer(dto);
	}
	

}
