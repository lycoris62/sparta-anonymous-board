package sparta.spartaboard.domain.post.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 게시글 생성 요청 DTO
 * 생성되기 전 암호화된 비밀번호로 변경이 필요
 */
@Getter
public class PostCreateRequestDto {

	@NotBlank
	@Length(min = 1, max = 30)
	private String title;

	@NotBlank
	@Length(min = 2, max = 20)
	private String author;

	@NotBlank
	@Length(min = 4, max = 50)
	private String password;

	@NotBlank
	private String contents;

	public void changeEncodedPassword(String encodedPassword) {
		this.password = encodedPassword;
	}
}