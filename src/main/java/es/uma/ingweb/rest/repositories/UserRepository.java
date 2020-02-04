package es.uma.ingweb.rest.repositories;

import es.uma.ingweb.rest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByNameContaining(String name);
}
