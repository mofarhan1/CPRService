package com.example.cprService;

import com.example.cprService.model.Person;
import com.example.cprService.repository.PersonRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
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




    }
}