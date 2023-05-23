package com.example.cprService;

import com.example.cprService.model.Person;
import com.example.cprService.repository.PersonRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Autowired
    public DatabaseInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Map<String, String>> data = readCSVFile("TestPersoner.csv");
        saveDataToDatabase(data);
    }



    private List<Map<String, String>> readCSVFile(String fileName) {
        List<Map<String, String>> data = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] headers = csvReader.readNext();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Map<String, String> record = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    record.put(headers[i], nextRecord[i]);
                }

                data.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private void saveDataToDatabase(List<Map<String, String>> data) {
        for (Map<String, String> record : data) {
            Person person = new Person();
            person.setId(record.get("id"));
            person.setLastname(record.get("lastname"));
            person.setFirstname(record.get("firstname"));
            person.setGender(record.get("gender"));
            person.setAddress(record.get("address"));
            person.setGuardian(record.get("guardian"));
            person.setBirthdate(record.get("birthdate"));
            person.setZip(record.get("zip"));
            person.setComment(record.get("comment"));

            personRepository.save(person);
        }
    }
}
