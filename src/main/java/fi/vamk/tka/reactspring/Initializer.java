package fi.vamk.tka.reactspring;

import fi.vamk.tka.reactspring.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import java.util.HashSet;

@Component
class Initializer implements CommandLineRunner {

    private  final GroupRepository repository;
    private  final UserRepository userRepository;
    private  final EventRepository eventRepository;


    public Initializer(GroupRepository repository, UserRepository userRepository, EventRepository eventRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );
        User a = new User();
        a.setName("Timo");
        userRepository.save(a);
        User b = new User();
        b.setName("Juhani");
        userRepository.save(b);
        Set<User> users = new HashSet<User>();
        users.add(a);
        users.add(b);
    
        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        e.setAttendees(users);
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);
        eventRepository.findAll().forEach(System.out::println);
    }
}