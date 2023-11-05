package sparta.spartaboard.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import sparta.spartaboard.global.error.exception.InvalidPasswordException;
import sparta.spartaboard.global.error.exception.PostNotFoundException;
import sparta.spartaboard.global.error.exception.ServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PostNotFoundException.class)
	protected ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
		final ErrorResponse response = new ErrorResponse(ErrorCode.POST_NOT_FOUND);
		return new ResponseEntity<>(response, ErrorCode.POST_NOT_FOUND.getHttpStatus());
	}

	@ExceptionHandler(InvalidPasswordException.class)
	protected ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
		final ErrorResponse response = new ErrorResponse(ErrorCode.INVALID_PASSWORD);
		return new ResponseEntity<>(response, ErrorCode.INVALID_PASSWORD.getHttpStatus());
	}

	@ExceptionHandler(ServerErrorException.class)
	protected ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException e) {
		final ErrorResponse response = new ErrorResponse(ErrorCode.SERVER_ERROR);
		return new ResponseEntity<>(response, ErrorCode.SERVER_ERROR.getHttpStatus());
	}
}
