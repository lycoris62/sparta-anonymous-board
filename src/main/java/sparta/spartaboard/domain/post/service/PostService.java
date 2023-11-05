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

	/**
	 * 레포지토리로부터 생성일자를 기준으로 내림차순 리스트를 받아와 간소화 게시글 DTO 리스트로 변환 후 리턴
	 */
	public List<PostPreviewResponseDto> getPosts() {
		return postRepository.findAllByOrderByCreatedAtDesc()
			.stream()
			.map(PostPreviewResponseDto::new)
			.toList();
	}

	/**
	 * 레포지토리로부터 특정 id의 게시글 세부 정보를 반환
	 * @param id 게시글 id
	 */
	public PostDetailResponseDto getPost(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(PostNotFoundException::new);

		return new PostDetailResponseDto(post);
	}

	/**
	 * 게시글 생성 DTO 에서 비밀번호를 SHA-512로 암호화 후 레포지토리에 저장함
	 */
	@Transactional
	public PostCreateResponseDto createPost(PostCreateRequestDto request) {
		passwordEncoding(request);

		Post createdPost = Post.create(request); // Post 엔티티의 자체 메서드를 이용해서 생성
		Post savedPost = postRepository.save(createdPost);

		return new PostCreateResponseDto(savedPost.getId());
	}

	private void passwordEncoding(PostCreateRequestDto request) {
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		request.changeEncodedPassword(encodedPassword);
	}

	/**
	 * 비밀번호를 검증 후 게시글을 수정함
	 */
	@Transactional
	public PostDetailResponseDto editPost(Long postId, PostEditRequestDto request, String password) {
		Post post = getValidatedPost(postId, password);
		post.edit(request); // Post 엔티티의 자체 수정 메서드를 이용해서 수정

		return new PostDetailResponseDto(post);
	}

	/**
	 * 비밀번호를 검증 후 게시글을 삭제함
	 */
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
