package sparta.spartaboard.domain.post.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 게시글 수정 요청 DTO
 */
@Getter
public class PostEditRequestDto {

	@NotBlank
	@Length(min = 1, max = 30)
	private String title;

	@NotBlank
	@Length(min = 2, max = 20)
	private String author;

	@NotBlank
	private String contents;
}
