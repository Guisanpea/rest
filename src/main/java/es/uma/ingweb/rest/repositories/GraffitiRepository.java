package es.uma.ingweb.rest.repositories;

import es.uma.ingweb.rest.entities.Graffiti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraffitiRepository extends JpaRepository<Graffiti, Integer> {
    List<Graffiti> findAllByArtistNameContainingAndArtistEmailContaining(String name, String email);
}
