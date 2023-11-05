package sparta.spartaboard.domain.post.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import sparta.spartaboard.domain.post.entity.Post;

/**
 * 게시글 목록 반환을 위한 간소화된 게시글 정보 DTO
 * 비밀번호는 들어가면 안 된다.
 */
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
