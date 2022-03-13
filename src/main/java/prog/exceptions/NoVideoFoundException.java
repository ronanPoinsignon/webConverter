package prog.exceptions;

import org.springframework.http.HttpStatus;

import exception.BaseException;

public class NoVideoFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Aucune vidéo n'a pu être trouvée.";

	public NoVideoFoundException() {
		super(NoVideoFoundException.MESSAGE);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
