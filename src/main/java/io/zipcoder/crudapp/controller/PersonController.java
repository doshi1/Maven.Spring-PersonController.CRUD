package io.zipcoder.crudapp.controller;


import io.zipcoder.crudapp.Repository.PersonRepository;
import io.zipcoder.crudapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }
    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<>(personRepository.findById(id).get(),HttpStatus.OK);

    }
    @GetMapping("/people")
    public ResponseEntity<Iterable <Person>>getPersonList(){
        return new ResponseEntity<>(personRepository.findAll(),HttpStatus.OK);
    }
    @PutMapping(("/people/{id}"))
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person p){
        Person originalPerson = personRepository.findById(id).get(); // to get()
        originalPerson.setFirstName(p.getFirstName());
        originalPerson.setLastName(p.getLastName());
        return new ResponseEntity<>(personRepository.save(originalPerson),HttpStatus.OK);
    }
    @DeleteMapping("/people/{id}")
    public void DeletePerson(@PathVariable Long id){

    }
}
