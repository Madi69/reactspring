package fi.vamk.tka.reactspring.web;

import fi.vamk.tka.reactspring.model.UserRepository;
import fi.vamk.tka.reactspring.model.User;
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
class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    Collection<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{name}")
    ResponseEntity<?> getUser(@PathVariable String name) {
        Optional<User> user = userRepository.findByName(name);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user")
    ResponseEntity<User> createuser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("Request to create user: {}", user);
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/user/" + result.getName()))
                .body(result);
    }

    @PutMapping("/user")
    ResponseEntity<User> updateuser(@Valid @RequestBody User user) {
        log.info("Request to update user: {}", user);
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/user/{name}")
    public ResponseEntity<?> deleteuser(@PathVariable String name) {
        log.info("Request to delete user: {}", name);
        userRepository.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}