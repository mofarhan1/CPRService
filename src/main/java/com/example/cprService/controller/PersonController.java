package com.example.cprService.controller;
import com.example.cprService.model.Person;
import com.example.cprService.repository.PersonRepository;
import com.example.cprService.service.PersonService;
import jakarta.validation.*;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping
@CrossOrigin
@Validated
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService,
							PersonRepository personRepository) throws IOException {
		this.personService = personService;
		this.personRepository = personRepository;
	}

	private final PersonRepository personRepository;



	@GetMapping("/api/getPersons")
	public ResponseEntity<List<Person>> getPersons() {
		List<Person> people = personService.getAllPersons();
		return ResponseEntity.ok(people);
	}


	//getPerson?personID=<CPR>&gurdian=<gurdian>
	@GetMapping("/api/getPerson")
	public ResponseEntity<?> getPerson(@RequestParam  @Size(min = 10, max = 10, message = "Input must have length of 10") String id) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<String>> violations = validator.validate(id);

		if (!violations.isEmpty()) {
			// Handle validation errors
			String errorMessage = violations.iterator().next().getMessage();
			return ResponseEntity.badRequest().body(errorMessage);
		}
		Person person = personService.findPersonByCpr(id);


		if (person == null) {
			String errorMessage = "Person not found for id: " + id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		}

		return ResponseEntity.ok(person);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleValidationException(ConstraintViolationException e) {
		String errorMessage = e.getConstraintViolations()
				.stream()
				.map(ConstraintViolation::getMessage)
				.findFirst()
				.orElse("Validation error");
		return ResponseEntity.badRequest().body(errorMessage);
	}
}
