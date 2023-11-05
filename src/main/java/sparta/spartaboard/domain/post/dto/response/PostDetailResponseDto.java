package sparta.spartaboard.domain.post.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import sparta.spartaboard.domain.post.entity.Post;

/**
 * 게시글 세부 정보를 응답하는 DTO
 * 비밀번호는 들어가면 안 된다.
 */
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
