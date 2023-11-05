package sparta.spartaboard.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

	private String message;

	public ErrorResponse(ErrorCode errorCode) {
		this.message = errorCode.getMessage();
	}
}
