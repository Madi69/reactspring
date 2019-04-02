package fi.vamk.tka.reactspring.web;

import fi.vamk.tka.reactspring.model.EventRepository;
import fi.vamk.tka.reactspring.model.Event;
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
class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);
    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    Collection<Event> events() {
        return eventRepository.findAll();
    }

    @GetMapping("/event/{id}")
    ResponseEntity<?> getEvent(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/event")
    ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) throws URISyntaxException {
        log.info("Request to create event: {}", event);
        Event result = eventRepository.save(event);
        return ResponseEntity.created(new URI("/api/event/" + result.getId()))
                .body(result);
    }

    @PutMapping("/event")
    ResponseEntity<Event> updateEvent(@Valid @RequestBody Event event) {
        log.info("Request to update event: {}", event);
        Event result = eventRepository.save(event);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        log.info("Request to delete event: {}", id);
        eventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}