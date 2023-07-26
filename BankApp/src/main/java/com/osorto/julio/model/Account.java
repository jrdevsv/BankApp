package com.osorto.julio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "account")
public class Account {
	
	@Id
    @Column(name = "account_number", unique = true, nullable = false)
	private String accountNumber;
    
    @Column(name="account_type")
	private String accountType;
    
    @Column(name="balance")
	private String balance;
    
    @Column(name="state")
	private String state;
	
	@ManyToOne
    @JoinColumn(name="id_customer", nullable=false)
	private Customer customer;
	
	@Embedded
	@OneToMany(mappedBy="account")
	@JsonProperty(value = "movements_list")
	private List<Movements> movements;

}
