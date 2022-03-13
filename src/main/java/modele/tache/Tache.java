package modele.tache;

/**
 * Tache abstraite utilisée pour le maniement d'événements.
 * @author ronan
 *
 * @param <T>
 */
public abstract class Tache<T> {

	protected Tache() {
	}

	protected abstract T download() throws Exception;
}
