package sparta.spartaboard.domain.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sparta.spartaboard.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByOrderByCreatedAtDesc(); // 생성일자를 기준으로 내림차순한 게시글 리스트를 반환
}
