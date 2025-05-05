package br.com.techchallenge.techbites.repositories;

import br.com.techchallenge.techbites.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByActive(Boolean active);
    Optional<User> findByEmail(String email);
}