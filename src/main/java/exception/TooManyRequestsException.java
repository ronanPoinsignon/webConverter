package exception;

public class TooManyRequestsException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 4815843398359718626L;

	public TooManyRequestsException() {
		super("Le nombre de requêtes en cours est actuellement trop important.");
	}
}
