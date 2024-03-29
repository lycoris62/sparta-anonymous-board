package sparta.spartaboard.global.error;

import static sparta.spartaboard.global.error.ErrorCode.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import sparta.spartaboard.global.error.exception.InvalidPasswordException;
import sparta.spartaboard.global.error.exception.PostNotFoundException;
import sparta.spartaboard.global.error.exception.ServerErrorException;

/**
 * 에러 핸들링 컨트롤러. 모든 에러는 여기서 처리.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PostNotFoundException.class)
	protected ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
		final ErrorResponse response = new ErrorResponse(POST_NOT_FOUND);
		return new ResponseEntity<>(response, POST_NOT_FOUND.getHttpStatus());
	}

	@ExceptionHandler(InvalidPasswordException.class)
	protected ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
		final ErrorResponse response = new ErrorResponse(INVALID_PASSWORD);
		return new ResponseEntity<>(response, INVALID_PASSWORD.getHttpStatus());
	}

	/**
	 * 커스텀 예외가 아닌, Validation 라이브러리 종속 예외.
	 * 생성 및 수정 요청 시 검증되지 않은 입력값이 들어왔을 때 발생함.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		final ErrorResponse response = new ErrorResponse(INVALID_INPUT_LENGTH);
		return new ResponseEntity<>(response, INVALID_INPUT_LENGTH.getHttpStatus());
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException e) {
		final ErrorResponse response = new ErrorResponse(SERVER_ERROR);
		return new ResponseEntity<>(response, SERVER_ERROR.getHttpStatus());
	}
}
