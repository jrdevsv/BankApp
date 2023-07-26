package com.osorto.julio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "person")
public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_person", unique = true, nullable = false)
	private int idPerson;
	
    @Column(name = "name")
	private String name;
	
    @Column(name = "genre")
	private String genre;
	
    @Column(name = "age")
	private String age;
	
    @Column(name = "identification")
	private String identification;
	
    @Column(name = "address")
	private String address;
	
    @Column(name = "phone")
	private String phone;
		
}
