package modele.log;

/**
 * Classe de log permettant l'affichage d'erreur ou de mauvaise manipulations.
 * @author ronan
 *
 */
public class Logger {

	private static Logger logger = null;

	public static Logger getInstance() {
		if(Logger.logger == null) {
			Logger.logger = new Logger();
		}
		return Logger.logger;
	}

	private Logger() {

	}

}
