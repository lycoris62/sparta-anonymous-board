package sparta.spartaboard.global.error;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * 에러코드를 enum 으로 모아서 어떤 에러가 있는지 한눈에 파악하기 쉽게 함
 * 특정 에러가 발생하면 해당하는 Http Status 와 message 가 리턴됨
 */
@Getter
public enum ErrorCode {

	POST_NOT_FOUND(NOT_FOUND, "없는 게시글"),
	INVALID_INPUT_LENGTH(BAD_REQUEST, "입력 길이 미충족"),
	INVALID_PASSWORD(UNAUTHORIZED, "잘못된 비밀번호"),
	SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러");

	private final HttpStatus httpStatus;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
