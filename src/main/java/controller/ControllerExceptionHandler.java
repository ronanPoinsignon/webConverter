package controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import exception.TooManyRequestsException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String internalServerError(Exception ex) {
		return "Internal error";
	}

	@ExceptionHandler(value = {TooManyRequestsException.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String internalServerError(TooManyRequestsException ex) {
		return ex.getMessage();
	}
}