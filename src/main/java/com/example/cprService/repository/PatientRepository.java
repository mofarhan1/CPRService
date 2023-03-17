package com.example.cprService.repository;

import com.example.cprService.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientById(Long id);

    @Query(value = "SELECT * FROM PATIENT WHERE ID=:id", nativeQuery = true)
    Patient findPatientByCpr(@Param("id") String id);
}
