package com.example.cprService.controller;
import com.example.cprService.model.Person;
import com.example.cprService.repository.PersonRepository;
import com.example.cprService.service.PersonService;
import com.opencsv.CSVReader;
import jakarta.validation.*;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
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
		this.upload();
	}

	private final PersonRepository personRepository;



	@GetMapping("/api/persons")
	public ResponseEntity<List<Person>> getAllPatients() {
		List<Person> people = personService.getAllPatients();
		return ResponseEntity.ok(people);
	}


	//getPerson?personID=<CPR>&gurdian=<gurdian>
	@GetMapping("/api/getPerson")
	public ResponseEntity<?> getPerson(@RequestParam @Size(min = 10, max = 10, message = "Input must have length of 10") String id) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<String>> violations = validator.validate(id);

		if (!violations.isEmpty()) {
			// Handle validation errors
			String errorMessage = violations.iterator().next().getMessage();
			return ResponseEntity.badRequest().body(errorMessage);
		}
		Person person = personService.findPatientByCpr(id);


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





	public ResponseEntity<String> upload() throws IOException {
		// Java code to illustrate reading a
// CSV file line by line
		List<HashMap<String,String>> hashMaps = new ArrayList<HashMap<String,String>>();


			try {

				FileReader filereader = new FileReader("TestPersoner.csv");
				CSVReader csvReader = new CSVReader(filereader);

				String[] nextRecord;
				String[] arr = nextRecord = csvReader.readNext();

				while ((nextRecord = csvReader.readNext()) != null) {
					HashMap<String, String> dictionary = new HashMap<String, String>();

				for(int i = 0;i<arr.length;i++){
					dictionary.put(arr[i], nextRecord[i]);
				}
				hashMaps.add(dictionary);

				}

			}
			catch (Exception e) {
				e.printStackTrace();
			}

		for (Map<String, String> map : hashMaps) {
			Person person = new Person();
            person.setId(map.get("id"));
            person.setLastname(map.get("lastname"));
            person.setFirstname(map.get("firstname"));
            person.setGender(map.get("gender"));
			person.setAddress(map.get("address"));
			person.setGuardian(map.get("guardian"));
            person.setBirthdate(map.get("birthdate"));
            person.setZip(map.get("zip"));
			person.setComment(map.get("comment"));


			personRepository.save(person);
		}
			 {

		}

		return ResponseEntity.ok("patients are uploaded");
	}

}
