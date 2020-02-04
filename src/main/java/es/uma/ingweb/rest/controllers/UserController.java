package es.uma.ingweb.rest.controllers;

import es.uma.ingweb.rest.entities.User;
import es.uma.ingweb.rest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> get(@RequestParam Optional<String> name) {
        return name
              .map(userRepository::findAllByNameContaining)
              .orElseGet(userRepository::findAll);
    }

    @PostMapping
    public User post(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        return userRepository.findById(id)
              .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
              .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public User put(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
