package sparta.spartaboard.domain.post.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 세부 정보를 응답하는 DTO
 * 비밀번호는 들어가면 안 된다.
 */
@Getter
@Setter
public class PostDetailResponseDto {

	private Long id;
	private String title;
	private String author;
	private String contents;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}
