package com.osorto.julio.exception;

import java.time.LocalDateTime;

import com.osorto.julio.dto.AccountDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	
	private LocalDateTime date;
	private String message;
	private String details;

}
