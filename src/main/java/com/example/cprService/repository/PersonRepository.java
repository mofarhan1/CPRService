package com.example.cprService.repository;

import com.example.cprService.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findPersonById(Long id);

    @Query(value = "SELECT * FROM PERSON WHERE ID=:id", nativeQuery = true)
    Person findPersonByCpr(@Param("id") String id);
}
