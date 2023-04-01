package com.example.cprService.service;
import com.example.cprService.model.*;
import com.example.cprService.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	public Person findPersonByCpr(String id) {

		Person checkIfPersonWithCprExist = personRepository.findPersonByCpr(id);

		return checkIfPersonWithCprExist;
	}


	public Person savePerson(Person person) {
		return personRepository.save(person);
	}


	public Person createPerson(Person person) {
		personRepository.save(person);
		Person savedPerson = personRepository.saveAndFlush(person);
		return savedPerson;
	}


}
