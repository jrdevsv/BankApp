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
public class PersonDTO {

	private int idPerson;
	
	private String name;

	private String genre;

	private String age;

	private String identification;

	private String address;

	private String phone;
	


}
