package com.osorto.julio.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osorto.julio.dto.AccountDTO;
import com.osorto.julio.exception.BadRequestException;
import com.osorto.julio.exception.NotFoundException;
import com.osorto.julio.model.Account;
import com.osorto.julio.model.Customer;
import com.osorto.julio.model.Movements;
import com.osorto.julio.repository.AccountRepository;
import com.osorto.julio.repository.CustomerRepository;
import com.osorto.julio.repository.MovementRepository;
import com.osorto.julio.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private MovementRepository movRepo;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public ResponseEntity<Object> save(AccountDTO dto) {

		Optional<Customer> customer = customerRepo.findById(dto.getIdCustomer());

		if (!customer.isPresent()) {
			throw new NotFoundException("Cliente no existe");
		}

		Optional<Account> account = repo.findByAccountNumber(dto.getAccountNumber());

		if (account.isPresent()) {
			throw new BadRequestException("Cuenta ya existe");
		}

		Account newAccount = Account.builder().accountNumber(dto.getAccountNumber()).accountType(dto.getAccountType())
				.balance(dto.getBalance()).customer(customer.get()).state(dto.getState()).build();

		newAccount = repo.save(newAccount);

		AccountDTO obj = AccountDTO.builder().accountNumber(newAccount.getAccountNumber())
				.accountType(newAccount.getAccountType()).balance(newAccount.getBalance()).state(newAccount.getState())
				.idCustomer(newAccount.getCustomer().getIdCustomer()).build();

		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> update(AccountDTO dto) {
		Optional<Account> obj = repo.findById(dto.getAccountNumber());

		if (!obj.isPresent()) {
			throw new NotFoundException("Cliente no existe");
		}

		obj.get().setAccountType(dto.getAccountType());
		obj.get().setBalance(dto.getBalance());
		obj.get().setState(dto.getState());

		return new ResponseEntity<>(repo.save(obj.get()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> delete(String accountNumber) {
		Optional<Account> obj = repo.findByAccountNumber(accountNumber);

		if (!obj.isPresent()) {
			throw new NotFoundException("Cliente no existe");
		}

		List<Movements> movs = movRepo.findMovsByAccountNumber(accountNumber);

		movRepo.deleteAll(movs);

		repo.deleteById(accountNumber);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findById(String accountNumber) {
		Optional<Account> obj = repo.findByAccountNumber(accountNumber);

		if (!obj.isPresent()) {
			throw new NotFoundException("Cliente no existe");
		}

		AccountDTO dto = AccountDTO.builder().accountNumber(obj.get().getAccountNumber())
				.accountType(obj.get().getAccountType()).balance(obj.get().getBalance()).state(obj.get().getState())
				.idCustomer(obj.get().getCustomer() != null ? obj.get().getCustomer().getIdCustomer() : 0).build();

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findAll() {
		List<Account> list = repo.findAll();

		List<AccountDTO> listDTO = new ArrayList<>(); 
		
		list.forEach(account ->{
			AccountDTO dto = AccountDTO.builder().accountNumber(account.getAccountNumber())
					.accountType(account.getAccountType()).balance(account.getBalance()).state(account.getState())
					.idCustomer(account.getCustomer() != null ? account.getCustomer().getIdCustomer() : 0).build();
			listDTO.add(dto);
		});
		
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

}
