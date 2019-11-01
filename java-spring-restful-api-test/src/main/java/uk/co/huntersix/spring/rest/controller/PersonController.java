package uk.co.huntersix.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;

import java.net.URI;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonDataService personDataService;

    public PersonController(@Autowired PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping("/person/{lastName}/{firstName}")
    public Person person(@PathVariable(value="lastName") String lastName,
                         @PathVariable(value="firstName") String firstName) {
        Person personData=personDataService.findPerson(lastName, firstName);
        if(personData==null){
            throw  new ResourceNotFoundException( "Person", "First Name",firstName ,"Last Name",lastName);
        }else{

            return personData;
        }

    }

    @GetMapping("/person/{lastName}")
    public List<Person> person(@PathVariable(value="lastName") String lastName
                        ) {
        List<Person> personData=personDataService.findPerson(lastName);
        if(personData==null){
            throw  new ResourceNotFoundException( "Person" ,"Last Name",lastName);
        }else{

            return personData;
        }

    }

    @PostMapping("/newperson/{lastName}/{firstName}")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personDataService.save(person);
        return ResponseEntity.created(URI.create("/newperson/" + savedPerson.getLastName()+ savedPerson.getFirstName())).build();


    }




}