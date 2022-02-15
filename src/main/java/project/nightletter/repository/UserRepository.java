package project.nightletter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nightletter.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);
}
