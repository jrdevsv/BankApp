package com.osorto.julio.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osorto.julio.model.Movements;

@Repository
public interface MovementRepository extends CrudRepository<Movements, Integer> {

	List<Movements> findAll();

	@Query(value = "SELECT m FROM Movements m JOIN m.account a WHERE a.accountNumber= :accountNumber "
			+ "AND m.date BETWEEN :startDate AND :endDate ORDER BY m.date DESC")
	public List<Movements> getAccountStatement(@Param("accountNumber") String accountNumber,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "Select * from movement where account_number = :accountNumber", nativeQuery = true)
	public List<Movements> findMovsByAccountNumber(@Param("accountNumber") String accountNumber);

	@Query(value = "Select SUM(ABS(amount)) from movement where account_number = :accountNumber and date = :pDate "
			+ "and amount < 0", nativeQuery = true)
	public Optional<BigDecimal> findDailyMovs(@Param("accountNumber") String accountNumber, @Param("pDate") Date pDate);
	
	@Query(value = "Select MAX(id_movement) from movement where account_number = :accountNumber ", nativeQuery = true)
	public Integer findLastMovId(@Param("accountNumber") String accountNumber);

}
