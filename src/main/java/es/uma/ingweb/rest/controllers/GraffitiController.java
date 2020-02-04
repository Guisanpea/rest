package es.uma.ingweb.rest.controllers;

import es.uma.ingweb.rest.entities.Graffiti;
import es.uma.ingweb.rest.entities.User;
import es.uma.ingweb.rest.repositories.GraffitiRepository;
import es.uma.ingweb.rest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/graffitis")
public class GraffitiController {
    private final GraffitiRepository graffitiRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Graffiti> get(
          @RequestParam Optional<String> userName,
          @RequestParam Optional<String> userMail) {
        return graffitiRepository.findAllByArtistNameContainingAndArtistEmailContaining(
              userName.orElse(""),
              userMail.orElse("")
        );
    }

    @PostMapping
    public ResponseEntity<Graffiti> post(@RequestBody Graffiti graffiti) {
        Optional<User> byId = userRepository.findById(graffiti.getArtistId());
        return byId
              .map(graffiti::setArtist)
              .map(graffitiRepository::save)
              .map(result -> new ResponseEntity<>(result, OK))
              .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Graffiti> get(@PathVariable int id) {
        return graffitiRepository.findById(id)
              .map(result -> new ResponseEntity<>(result, OK))
              .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Graffiti put(@PathVariable int id, @RequestBody Graffiti graffiti) {
        graffiti.setId(id);
        return graffitiRepository.save(graffiti);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        graffitiRepository.deleteById(id);
    }
}
