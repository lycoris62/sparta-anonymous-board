package sparta.spartaboard.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sparta.spartaboard.global.error.exception.InvalidPasswordException;
import sparta.spartaboard.global.error.exception.PostNotFoundException;
import sparta.spartaboard.post.dto.request.PostCreateRequestDto;
import sparta.spartaboard.post.dto.request.PostEditRequestDto;
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
			.orElseThrow(PostNotFoundException::new);

		return new PostDetailResponseDto(post);
	}

	@Transactional
	public PostCreateResponseDto createPost(PostCreateRequestDto request) {
		Post createdPost = Post.create(request);
		Post savedPost = postRepository.save(createdPost);

		return new PostCreateResponseDto(savedPost.getId());
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

		if (!post.getPassword().equals(password)) {
			throw new InvalidPasswordException();
		}

		return post;
	}
}
