package sparta.spartaboard.post.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import sparta.spartaboard.post.entity.Post;

@Getter
public class PostDetailResponseDto {

	private final Long id;
	private final String title;
	private final String author;
	private final String contents;
	private final LocalDateTime createdAt;
	private final LocalDateTime modifiedAt;

	public PostDetailResponseDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.author = post.getAuthor();
		this.contents = post.getContents();
		this.createdAt = post.getCreatedAt();
		this.modifiedAt = post.getModifiedAt();
	}
}
