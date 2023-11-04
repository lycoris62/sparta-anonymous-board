package sparta.spartaboard.post.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import sparta.spartaboard.post.entity.Post;

@Getter
public class PostPreviewResponseDto {

	private final Long id;
	private final String title;
	private final String author;
	private final LocalDateTime createdAt;

	public PostPreviewResponseDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.author = post.getAuthor();
		this.createdAt = post.getCreatedAt();
	}
}
