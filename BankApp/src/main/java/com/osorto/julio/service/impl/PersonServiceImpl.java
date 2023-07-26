package com.osorto.julio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osorto.julio.dto.PersonDTO;
import com.osorto.julio.exception.NotFoundException;
import com.osorto.julio.model.Customer;
import com.osorto.julio.model.Person;
import com.osorto.julio.repository.CustomerRepository;
import com.osorto.julio.repository.PersonRepository;
import com.osorto.julio.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repo;
	
	@Autowired
	private CustomerRepository custRepo;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public ResponseEntity<Object> savePerson(PersonDTO dto) {

		Person person = mapper.convertValue(dto, Person.class);

		return new ResponseEntity<>(repo.save(person), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> updatePerson(PersonDTO dto) {

		Optional<Person> p = repo.findById(dto.getIdPerson());

		if (!p.isPresent()) {
			throw new NotFoundException("Registro no existe");
		}

		p.get().setName(dto.getName());
		p.get().setGenre(dto.getGenre());
		p.get().setAge(dto.getAge());
		p.get().setAddress(dto.getAddress());
		p.get().setIdentification(dto.getIdentification());
		p.get().setPhone(dto.getPhone());

		return new ResponseEntity<>(repo.save(p.get()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deletePerson(int id) {
		Optional<Person> p = repo.findById(id);

		if (!p.isPresent()) {
			throw new NotFoundException("Registro no existe");
		}
		
		List<Customer> list = custRepo.findByIdPerson(p.get().getIdPerson());
		
		custRepo.deleteAll(list); 
				
		repo.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findById(int id) {
		Optional<Person> p = repo.findById(id);

		if (!p.isPresent()) {
			throw new NotFoundException("Registro no existe");
		}

		PersonDTO person = mapper.convertValue(p.get(), PersonDTO.class);

		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findAll() {
		List<Person> personList = repo.findAll();

		List<PersonDTO> list = mapper.convertValue(personList, List.class);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
