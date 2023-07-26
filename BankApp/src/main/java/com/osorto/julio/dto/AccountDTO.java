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
public class AccountDTO {

	private String accountNumber;

	private String accountType;

	private String balance;

	private String state;
	
	private int idCustomer;
}
