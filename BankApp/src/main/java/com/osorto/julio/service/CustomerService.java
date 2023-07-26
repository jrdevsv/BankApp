package com.osorto.julio.service;

import org.springframework.http.ResponseEntity;

import com.osorto.julio.dto.CustomerDTO;


public interface CustomerService {
	
	ResponseEntity<Object> saveCustomer (CustomerDTO dto);

}
