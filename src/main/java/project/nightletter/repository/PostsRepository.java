package project.nightletter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nightletter.model.Posts;

import java.time.LocalDateTime;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    List<Posts> findAllByUpdatedAtBetweenOrderByUpdatedAtDesc(LocalDateTime start, LocalDateTime end);
}
