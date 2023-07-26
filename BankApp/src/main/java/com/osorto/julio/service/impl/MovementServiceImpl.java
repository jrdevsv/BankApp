package com.osorto.julio.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osorto.julio.dto.MovementsDTO;
import com.osorto.julio.exception.BadRequestException;
import com.osorto.julio.exception.NotFoundException;
import com.osorto.julio.model.Account;
import com.osorto.julio.model.Movements;
import com.osorto.julio.repository.AccountRepository;
import com.osorto.julio.repository.MovementRepository;
import com.osorto.julio.service.MovementService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovementServiceImpl implements MovementService {

	@Value("${param.max.daily.withdrawal}")
	private String maxWithdrawal;

	@Autowired
	private MovementRepository repo;

	@Autowired
	private AccountRepository accountRepo;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public ResponseEntity<Object> save(MovementsDTO dto) {

		Date movDate = new Date();
		try {
			movDate = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Optional<Account> account = accountRepo.findByAccountNumber(dto.getAccountNumber());

		if (!account.isPresent()) {
			throw new NotFoundException("Cuenta no Encontrada");
		}

		if (dto.getAmount().compareTo(BigDecimal.ZERO) == 0) {
			throw new BadRequestException("Monto del movimiento no puede ser 0");
		}

		Integer lastIdMov = repo.findLastMovId(dto.getAccountNumber());

		Optional<Movements> lastMov = Optional.empty();
		if (lastIdMov != null) {
			lastMov = repo.findById(lastIdMov);
		}

		BigDecimal newBalance = BigDecimal.ZERO;
		if (lastMov.isPresent()) {
			newBalance = lastMov.get().getBalance().add(dto.getAmount());
		} else {
			newBalance = new BigDecimal(account.get().getBalance()).add(dto.getAmount());
		}

		if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
			throw new BadRequestException("Saldo no disponible");
		}

		Optional<BigDecimal> dailyAmount = repo.findDailyMovs(dto.getAccountNumber(), movDate);

		if ((dailyAmount.isPresent()
				&& dailyAmount.get().add(dto.getAmount().abs()).compareTo(new BigDecimal(maxWithdrawal)) > 0
				&& dto.getAmount().compareTo(BigDecimal.ZERO) < 0)
				|| (dto.getAmount().abs().compareTo(new BigDecimal(maxWithdrawal)) > 0
						&& dto.getAmount().compareTo(BigDecimal.ZERO) < 0)) {
			throw new BadRequestException("Cupo diario Excedido");
		}

		dto.setBalance(newBalance);

		Movements obj = Movements.builder().account(account.get()).amount(dto.getAmount())
				.movementType(dto.getAmount().compareTo(BigDecimal.ZERO) < 0 ? "DEBITO" : "CREDITO")
				.balance(dto.getBalance()).date(movDate).build();

		Movements mov = repo.save(obj);

		MovementsDTO response = MovementsDTO.builder().accountNumber(mov.getAccount().getAccountNumber())
				.idMovement(mov.getIdMovement()).amount(mov.getAmount()).balance(mov.getBalance())
				.date(new SimpleDateFormat("yyyy-MM-dd").format(mov.getDate())).movementType(mov.getMovementType())
				.build();

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> update(MovementsDTO dto) {

		Date movDate = new Date();
		try {
			movDate = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Optional<Movements> obj = repo.findById(dto.getIdMovement());

		if (!obj.isPresent()) {
			throw new NotFoundException("Movimiento no Encontrado");
		}
		Optional<Account> account = accountRepo.findByAccountNumber(dto.getAccountNumber());

		if (!account.isPresent()) {
			throw new NotFoundException("Cuenta no Encontrada");
		}

		if (!obj.get().getAccount().getAccountNumber().equals(dto.getAccountNumber())) {
			throw new BadRequestException("Cuenta no coincide con la cuenta del movimiento");
		}

		obj.get().setAmount(dto.getAmount());
		obj.get().setBalance(dto.getBalance());
		obj.get().setDate(movDate);
		obj.get().setMovementType(dto.getMovementType());

		Movements mov = repo.save(obj.get());

		MovementsDTO response = MovementsDTO.builder().accountNumber(mov.getAccount().getAccountNumber())
				.amount(mov.getAmount()).balance(mov.getBalance())
				.date(new SimpleDateFormat("yyyy-MM-dd").format(mov.getDate())).movementType(mov.getMovementType())
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> delete(int id) {
		Optional<Movements> obj = repo.findById(id);

		if (!obj.isPresent()) {
			throw new NotFoundException("Registro no existe");
		}
		repo.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findById(int id) {
		Optional<Movements> obj = repo.findById(id);

		if (!obj.isPresent()) {
			throw new NotFoundException("Registro no existe");
		}

		MovementsDTO dto = MovementsDTO.builder().accountNumber(obj.get().getAccount().getAccountNumber())
				.idMovement(obj.get().getIdMovement())
				.amount(obj.get().getAmount()).balance(obj.get().getBalance())
				.date(new SimpleDateFormat("yyyy-MM-dd").format(obj.get().getDate()))
				.movementType(obj.get().getMovementType()).build();

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findAll() {
		List<Movements> list = repo.findAll();

		List<MovementsDTO> listDTO = new ArrayList<>();

		list.forEach(mov -> {
			MovementsDTO dto = MovementsDTO.builder().accountNumber(mov.getAccount().getAccountNumber())
					.idMovement(mov.getIdMovement()).amount(mov.getAmount()).balance(mov.getBalance())
					.date(new SimpleDateFormat("yyyy-MM-dd").format(mov.getDate())).movementType(mov.getMovementType())
					.build();
			listDTO.add(dto);
		});

		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAccountStatement(String accountNumber, String startDate, String endDate) {
		List<Map<String, Object>> response = new LinkedList<>();
		try {
			Date startDateFormat = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
			Date endDateFormat = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);

			List<Movements> movs = repo.getAccountStatement(accountNumber, startDateFormat, endDateFormat);

			movs.stream().forEach(mov -> {
				Map<String, Object> statement = new LinkedHashMap<>();

				statement.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(mov.getDate()));
				statement.put("Cliente", mov.getAccount().getCustomer().getPerson().getName());
				statement.put("Numero Cuenta", mov.getAccount().getAccountNumber());
				statement.put("Tipo", mov.getAccount().getAccountType());
				statement.put("Saldo inicial", mov.getAccount().getBalance());
				statement.put("Estado", mov.getAccount().getState());
				statement.put("Movimiento", mov.getAmount());
				statement.put("Saldo Disponible", mov.getBalance());
				response.add(statement);
			});

		} catch (Exception e) {
			log.error("Error [getAccountStatement]", e.getStackTrace());

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
