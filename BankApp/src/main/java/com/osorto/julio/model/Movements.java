package com.osorto.julio.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "movement")
public class Movements {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_movement", unique = true, nullable = false)
	private int idMovement;
	
	@Column(name = "date")
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date date;
	
	@Column(name = "movement_type")
	private String movementType;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@ManyToOne
    @JoinColumn(name="account_number", nullable=false)
    private Account account;
}
