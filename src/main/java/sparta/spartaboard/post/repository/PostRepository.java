package sparta.spartaboard.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sparta.spartaboard.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByOrderByCreatedAtDesc();
}
