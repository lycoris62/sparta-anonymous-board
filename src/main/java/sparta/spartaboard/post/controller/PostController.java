package sparta.spartaboard.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sparta.spartaboard.post.dto.request.PostCreateRequestDto;
import sparta.spartaboard.post.dto.response.PostCreateResponseDto;
import sparta.spartaboard.post.dto.response.PostDetailResponseDto;
import sparta.spartaboard.post.dto.response.PostPreviewResponseDto;
import sparta.spartaboard.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

	private final PostService postService;

	@GetMapping("/posts")
	public ResponseEntity<List<PostPreviewResponseDto>> getPosts() {
		List<PostPreviewResponseDto> posts = postService.getPosts();

		return ResponseEntity.ok(posts);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDetailResponseDto> getPost(@PathVariable Long postId) {
		PostDetailResponseDto postDetail = postService.getPost(postId);

		return ResponseEntity.ok(postDetail);
	}

	@PostMapping("/posts")
	public ResponseEntity<PostCreateResponseDto> createPost(@RequestBody PostCreateRequestDto request) {
		PostCreateResponseDto createPostDto = postService.createPost(request);

		return ResponseEntity.ok(createPostDto);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestHeader("password") String password) {
		postService.delete(postId, password);

		return ResponseEntity.noContent().build();
	}
}
