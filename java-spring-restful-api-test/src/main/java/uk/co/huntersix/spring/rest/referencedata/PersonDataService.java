package uk.co.huntersix.spring.rest.referencedata;

import org.springframework.stereotype.Service;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDataService {
    public static  List<Person> PERSON_DATA = new ArrayList<>(Arrays.asList(
        new Person("Mary", "Smith"),
            new Person("Madonna", "Smith"),
        new Person("Brian", "Archer"),
        new Person("Collin", "Brown")));

    public Person findPerson(String lastName, String firstName) {
       boolean exists= PERSON_DATA.stream()
                .anyMatch(p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName));
       if(exists) {
           return PERSON_DATA.stream()
                   .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                           && p.getLastName().equalsIgnoreCase(lastName))
                   .collect(Collectors.toList()).get(0);
       }else{
           return null;
       }
    }

    public List<Person> findPerson(String lastName) {
        boolean exists= PERSON_DATA.stream()
                .anyMatch(p ->
                         p.getLastName().equalsIgnoreCase(lastName));
        if(exists) {
            return PERSON_DATA.stream()
                    .filter(p ->
                             p.getLastName().equalsIgnoreCase(lastName))
                    .collect(Collectors.toList());
        }else{
            return null;
        }
    }

    public Person save(Person person) {
        PERSON_DATA.add(person);
        return person;
    }
}
