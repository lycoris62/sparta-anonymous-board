package sparta.spartaboard.post.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sparta.spartaboard.post.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;


}
