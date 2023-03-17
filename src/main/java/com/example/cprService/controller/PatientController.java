package com.example.cprService.controller;



import com.example.cprService.model.Patient;
import com.example.cprService.repository.PatientRepository;
import com.example.cprService.service.PatientService;
import com.opencsv.CSVReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService,
                             PatientRepository patientRepository) throws IOException {
		this.patientService = patientService;
		this.patientRepository = patientRepository;
		this.upload();
	}

	private final PatientRepository patientRepository;



	@GetMapping("/api/patients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patients = patientService.getAllPatients();
		return ResponseEntity.ok(patients);
	}


	//getPatient?patientID=<CPR>&doctorID=<DOCTOR_WITH_ACCESS>
	@GetMapping("/api/getPatient")
	public ResponseEntity<Patient> getPatient(@RequestParam String id) {
		Patient patient = patientService.findPatientByCpr(id);
		System.out.println("//getPatient?patientID=<CPR>&doctorID=<DOCTOR_WITH_ACCESS>");
		return ResponseEntity.ok(patient);

	}




	public ResponseEntity<String> upload() throws IOException {
		// Java code to illustrate reading a
// CSV file line by line
		List<HashMap<String,String>> hashMaps = new ArrayList<HashMap<String,String>>();


			try {

				FileReader filereader = new FileReader("TestPatienter.csv");
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
			Patient patient = new Patient();
            patient.setId(map.get("id"));
            patient.setLastname(map.get("lastname"));
            patient.setFirstname(map.get("firstname"));
            patient.setGender(map.get("gender"));
			patient.setAddress(map.get("address"));
			patient.setMunicipality(map.get("municipality"));
			patient.setGuardian(map.get("guardian"));
            patient.setBirthdate(map.get("birthdate"));
            patient.setZip(map.get("zip"));
			patient.setComment(map.get("comment"));
			patient.setRegion(map.get("region"));

			patientRepository.save(patient);
		}
			 {

		}

		return ResponseEntity.ok("patients are uploaded");
	}

}
