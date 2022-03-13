package controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import exception.TooManyRequestsException;
import prog.exceptions.NoVideoFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String internalServerError(Exception ex) {
		return "Internal error";
	}

	@ExceptionHandler(value = {TooManyRequestsException.class})
	public ResponseEntity<ErrorResponse> internalServerError(TooManyRequestsException ex) {
		return buildError(ex, HttpStatus.TOO_MANY_REQUESTS);
	}

	private ResponseEntity<ErrorResponse> buildError(Exception ex, HttpStatus status){
		return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
	}

	private class ErrorResponse {

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
		private Date timestamp;

		private int code;

		private String status;

		private String message;

		public ErrorResponse() {
			timestamp = new Date();
		}

		public ErrorResponse(
				HttpStatus httpStatus,
				String message
				) {
			this();

			code = httpStatus.value();
			status = httpStatus.name();
			this.message = message;
		}

		@SuppressWarnings("unused")
		public Date getTimestamp() {
			return timestamp;
		}

		@SuppressWarnings("unused")
		public int getCode() {
			return code;
		}

		@SuppressWarnings("unused")
		public String getStatus() {
			return status;
		}

		@SuppressWarnings("unused")
		public String getMessage() {
			return message;
		}
	}
}