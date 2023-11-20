package sparta.spartaboard.domain.post.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 목록 반환을 위한 간소화된 게시글 정보 DTO
 * 비밀번호는 들어가면 안 된다.
 */
@Getter
@Setter
public class PostPreviewResponseDto {

	private Long id;
	private String title;
	private String author;
	private LocalDateTime createdAt;
}
