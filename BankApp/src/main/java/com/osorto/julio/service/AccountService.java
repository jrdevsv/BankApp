package com.osorto.julio.service;

import org.springframework.http.ResponseEntity;

import com.osorto.julio.dto.AccountDTO;

public interface AccountService {
	
	ResponseEntity<Object> save(AccountDTO dto);

	ResponseEntity<Object> update(AccountDTO dto);

	ResponseEntity<Object> delete(String acountNumber);

	ResponseEntity<Object> findById(String accountNumber);

	ResponseEntity<Object> findAll();
	

}
