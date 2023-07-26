package com.osorto.julio.dto;



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
public class CustomerDTO {

	private int idCustomer; 
	
	private PersonDTO person;

	private String password;

	private String state;
}
