package com.uxzzi.apirest.Person;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepo;

    public void createPerson(Person person) {
        personRepo.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    public Person getPersonById(Integer id) {
        return personRepo.findById(id).orElse(null);
    }

    public void updatePerson(Integer id, Person updatedPerson) {
        Person person = getPersonById(id);
        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setEmail(updatedPerson.getEmail());
        personRepo.save(person);
    }

    public void deletePerson(Integer id) {
        Person person = getPersonById(id);
        personRepo.delete(person);
    }

    public PersonDTO login(String user, String password) {
        Person person = personRepo.findByUserAndPassword(user, password);
        PersonDTO personDTO = new PersonDTO(person.getFirstName(), person.getLastName(), person.getEmail());
        return personDTO;
    }
}
