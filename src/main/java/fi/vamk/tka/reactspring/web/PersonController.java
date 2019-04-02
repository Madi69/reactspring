package fi.vamk.tka.reactspring.web;

import fi.vamk.tka.reactspring.model.PersonRepository;
import fi.vamk.tka.reactspring.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class PersonController {

    private final Logger log = LoggerFactory.getLogger(PersonController.class);
    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    Collection<Person> persons() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{name}")
    ResponseEntity<?> getPerson(@PathVariable String name) {
        Optional<Person> person = personRepository.findByName(name);
        return person.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/person")
    ResponseEntity<Person> createuser(@Valid @RequestBody Person person) throws URISyntaxException {
        log.info("Request to create user: {}", person);
        Person result = personRepository.save(person);
        return ResponseEntity.created(new URI("/api/person/" + result.getName()))
                .body(result);
    }

    @PutMapping("/person")
    ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person) {
        log.info("Request to update user: {}", person);
        Person result = personRepository.save(person);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/person/{name}")
    public ResponseEntity<?> deletePerson(@PathVariable String name) {
        log.info("Request to delete person: {}", name);
        personRepository.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}