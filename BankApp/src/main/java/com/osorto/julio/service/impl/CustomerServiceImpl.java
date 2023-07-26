package com.osorto.julio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osorto.julio.dto.CustomerDTO;
import com.osorto.julio.model.Customer;
import com.osorto.julio.repository.CustomerRepository;
import com.osorto.julio.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repo;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public ResponseEntity<Object> saveCustomer(CustomerDTO dto) {

		Customer c = mapper.convertValue(dto, Customer.class);

		return new ResponseEntity<>(repo.save(c), HttpStatus.CREATED);

	}

}
