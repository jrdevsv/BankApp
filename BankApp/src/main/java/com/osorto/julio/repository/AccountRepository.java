package com.osorto.julio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osorto.julio.model.Account;
import com.osorto.julio.model.Customer;

@Repository
public interface AccountRepository extends CrudRepository<Account, String>  {
	
	List<Account> findAll();
		
	Optional<Account> findByAccountNumber(String accountNumber);

}
