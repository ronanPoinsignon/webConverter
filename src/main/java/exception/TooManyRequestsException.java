package exception;

import org.springframework.http.HttpStatus;

public class TooManyRequestsException extends BaseException {

	/**
	 *
	 */
	private static final long serialVersionUID = 4815843398359718626L;

	public TooManyRequestsException() {
		super("Le nombre de requêtes en cours est actuellement trop important.");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.TOO_MANY_REQUESTS;
	}
}
