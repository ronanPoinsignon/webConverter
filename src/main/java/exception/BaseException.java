package exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 2060456749244544636L;

	protected BaseException() {

	}

	protected BaseException(String msg) {
		super(msg);
	}

	public abstract HttpStatus getHttpStatus();
}
