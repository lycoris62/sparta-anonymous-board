package sparta.spartaboard.post.dto.request;

import lombok.Getter;

@Getter
public class PostEditRequestDto {

	private String title;
	private String author;
	private String contents;
}
