package sparta.spartaboard.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sparta.spartaboard.domain.post.dto.request.PostCreateRequestDto;
import sparta.spartaboard.domain.post.dto.request.PostEditRequestDto;
import sparta.spartaboard.domain.post.dto.response.PostCreateResponseDto;
import sparta.spartaboard.domain.post.dto.response.PostDetailResponseDto;
import sparta.spartaboard.domain.post.dto.response.PostPreviewResponseDto;
import sparta.spartaboard.domain.post.entity.Post;
import sparta.spartaboard.domain.post.repository.PostRepository;
import sparta.spartaboard.global.error.exception.InvalidPasswordException;
import sparta.spartaboard.global.error.exception.PostNotFoundException;
import sparta.spartaboard.global.security.PasswordEncoder;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final PasswordEncoder passwordEncoder;

	public List<PostPreviewResponseDto> getPosts() {
		return postRepository.findAllByOrderByCreatedAtDesc()
			.stream()
			.map(PostPreviewResponseDto::new)
			.toList();
	}

	public PostDetailResponseDto getPost(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(PostNotFoundException::new);

		return new PostDetailResponseDto(post);
	}

	@Transactional
	public PostCreateResponseDto createPost(PostCreateRequestDto request) {
		passwordEncoding(request);

		Post createdPost = Post.create(request);
		Post savedPost = postRepository.save(createdPost);

		return new PostCreateResponseDto(savedPost.getId());
	}

	private void passwordEncoding(PostCreateRequestDto request) {
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		request.changeEncodedPassword(encodedPassword);
	}

	@Transactional
	public PostDetailResponseDto editPost(Long postId, PostEditRequestDto request, String password) {
		Post post = getValidatedPost(postId, password);
		post.edit(request);

		return new PostDetailResponseDto(post);
	}

	@Transactional
	public void delete(Long postId, String password) {
		Post post = getValidatedPost(postId, password);

		postRepository.delete(post);
	}

	private Post getValidatedPost(Long postId, String password) {
		Post post = postRepository.findById(postId)
			.orElseThrow(PostNotFoundException::new);

		validatePassword(post, password);

		return post;
	}

	private void validatePassword(Post post, String password) {
		String encodedPassword = passwordEncoder.encode(password);

		if (!post.getPassword().equals(encodedPassword)) {
			throw new InvalidPasswordException();
		}
	}
}
