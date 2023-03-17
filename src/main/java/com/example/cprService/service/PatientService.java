package com.example.cprService.service;
import com.example.cprService.*;
import com.example.cprService.exception.PatientNotFoundException;
import com.example.cprService.model.*;
import com.example.cprService.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientService {

	private final PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public Patient findPatientById(Long id) {
		return patientRepository.findPatientById(id)
				.orElseThrow(() -> new PatientNotFoundException("patient with id: " + id + " does not exist."));
	}

	public Patient findPatientByCpr(String id) {

		Patient checkIfPatientWithCprExist = patientRepository.findPatientByCpr(id);
		if (checkIfPatientWithCprExist == null) {
			throw new PatientNotFoundException("patient with id: " + id + " does not exist.");
		}
		return checkIfPatientWithCprExist;
	}

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}


	public Patient createPatient(Patient patient) {
		patientRepository.save(patient);
		Patient savedPatient = patientRepository.saveAndFlush(patient);
		return savedPatient;
	}
}
