package com.osorto.julio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osorto.julio.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	@Query(value = "Select * from Customer  where id_Person = :idPerson", nativeQuery = true)
	public List<Customer> findByIdPerson(@Param("idPerson") int idPerson);

}
