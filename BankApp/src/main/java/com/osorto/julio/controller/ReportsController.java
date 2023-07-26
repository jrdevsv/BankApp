package com.osorto.julio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osorto.julio.service.MovementService;

@RestController
@RequestMapping("/report")
public class ReportsController {

	@Autowired
	private MovementService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMovement(@RequestParam(value = "accountNumber") String accountNumber,
			@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {
		return service.getAccountStatement(accountNumber, startDate, endDate);
	}

}
