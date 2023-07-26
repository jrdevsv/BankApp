package com.osorto.julio.service;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.osorto.julio.dto.MovementsDTO;

public interface MovementService {

	ResponseEntity<Object> save(MovementsDTO dto);

	ResponseEntity<Object> update(MovementsDTO dto);

	ResponseEntity<Object> delete(int id);

	ResponseEntity<Object> findById(int id);

	ResponseEntity<Object> findAll();
	
	ResponseEntity<Object> getAccountStatement(String accountNumber, String startDate, String endDate);

}
