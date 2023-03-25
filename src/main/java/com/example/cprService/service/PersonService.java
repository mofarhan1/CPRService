package com.example.cprService.service;
import com.example.cprService.model.*;
import com.example.cprService.repository.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAllPatients() {
		return personRepository.findAll();
	}

	public Person findPatientByCpr(String id) {

		Person checkIfPersonWithCprExist = personRepository.findPatientByCpr(id);

		return checkIfPersonWithCprExist;
	}


	public Person savePatient(Person person) {
		return personRepository.save(person);
	}


	public Person createPatient(Person person) {
		personRepository.save(person);
		Person savedPerson = personRepository.saveAndFlush(person);
		return savedPerson;
	}


}
