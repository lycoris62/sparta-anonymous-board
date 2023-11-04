package sparta.spartaboard.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sparta.spartaboard.post.dto.request.PostCreateRequestDto;
import sparta.spartaboard.post.dto.response.PostCreateResponseDto;
import sparta.spartaboard.post.dto.response.PostDetailResponseDto;
import sparta.spartaboard.post.dto.response.PostPreviewResponseDto;
import sparta.spartaboard.post.entity.Post;
import sparta.spartaboard.post.repository.PostRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public List<PostPreviewResponseDto> getPosts() {
		return postRepository.findAllByOrderByCreatedAtDesc()
			.stream()
			.map(PostPreviewResponseDto::new)
			.toList();
	}

	public PostDetailResponseDto getPost(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("없는 게시글"));

		return new PostDetailResponseDto(post);
	}

	@Transactional
	public PostCreateResponseDto createPost(PostCreateRequestDto request) {
		Post createdPost = Post.create(request);
		Post savedPost = postRepository.save(createdPost);

		return new PostCreateResponseDto(savedPost.getId());
	}

	@Transactional
	public void delete(Long postId, String password) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("없는 게시글"));

		if (!post.getPassword().equals(password)) {
			throw new IllegalArgumentException("잘못된 비밀번호");
		}

		postRepository.delete(post);
	}
}
