package sparta.spartaboard.domain.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sparta.spartaboard.domain.post.dto.request.PostCreateRequestDto;
import sparta.spartaboard.domain.post.dto.request.PostEditRequestDto;
import sparta.spartaboard.domain.post.dto.response.PostCreateResponseDto;
import sparta.spartaboard.domain.post.dto.response.PostDetailResponseDto;
import sparta.spartaboard.domain.post.dto.response.PostPreviewResponseDto;
import sparta.spartaboard.domain.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	/**
	 * 게시글 목록 조회
	 * @return 간소화한 게시글 정보 리스트를 반환함
	 */
	@GetMapping("")
	public ResponseEntity<List<PostPreviewResponseDto>> getPosts() {
		List<PostPreviewResponseDto> posts = postService.getPosts();

		return ResponseEntity.ok(posts);
	}

	/**
	 * 게시글 조회
	 * @return 게시글 세부 정보를 반환함
	 */
	@GetMapping("/{postId}")
	public ResponseEntity<PostDetailResponseDto> getPost(@PathVariable Long postId) {
		PostDetailResponseDto postDetail = postService.getPost(postId);

		return ResponseEntity.ok(postDetail);
	}

	/**
	 * 게시글 작성
	 * @param request JSON 으로 생성 정보를 받아오며 검증되지 않으면 예외 발생
	 * @return 생성한 게시글 세부 정보를 반환함
	 */
	@PostMapping("")
	public ResponseEntity<PostCreateResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto request) {
		PostCreateResponseDto createPostDto = postService.createPost(request);

		return ResponseEntity.ok(createPostDto);
	}

	/**
	 * 게시글 수정
	 * @param postId URL로부터 게시글 id 받아옴
	 * @param request JSON 으로 수정 정보를 받아오며 검증되지 않으면 예외 발생
	 * @param password 보안상 중요한 비밀번호는 Header 에 담아서 받아옴
	 * @return 수정된 게시글 세부 정보를 반환함.
	 */
	@PatchMapping("/{postId}")
	public ResponseEntity<PostDetailResponseDto> editPost(
		@PathVariable Long postId,
		@Valid @RequestBody PostEditRequestDto request,
		@RequestHeader String password) {

		PostDetailResponseDto editedPostDetail = postService.editPost(postId, request, password);

		return ResponseEntity.ok(editedPostDetail);
	}

	/**
	 * 게시글 삭제
	 * @param postId URL로부터 게시글 id 받아옴
	 * @param password 보안상 중요한 비밀번호는 Header 에 담아서 받아옴
	 * @return HttpStatus는 204 No Content 를 반환하며 body는 비어있음
	 */
	@DeleteMapping("/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestHeader("password") String password) {
		postService.delete(postId, password);

		return ResponseEntity.noContent().build();
	}
}
