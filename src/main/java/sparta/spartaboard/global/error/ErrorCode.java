package sparta.spartaboard.global.error;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	POST_NOT_FOUND(NOT_FOUND, "없는 게시글"),
	INVALID_PASSWORD(UNAUTHORIZED, "잘못된 비밀번호"),
	SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러");

	private final HttpStatus httpStatus;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
