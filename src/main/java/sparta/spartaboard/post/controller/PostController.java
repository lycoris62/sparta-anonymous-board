package sparta.spartaboard.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sparta.spartaboard.post.dto.PostDetailResponseDto;
import sparta.spartaboard.post.dto.PostPreviewResponseDto;
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
}
