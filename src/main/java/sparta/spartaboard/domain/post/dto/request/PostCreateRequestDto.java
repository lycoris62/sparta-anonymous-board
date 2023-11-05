package sparta.spartaboard.domain.post.dto.request;

import lombok.Getter;

@Getter
public class PostCreateRequestDto {

	private String title;
	private String author;
	private String password;
	private String contents;
}