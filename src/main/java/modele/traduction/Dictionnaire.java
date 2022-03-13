package modele.traduction;

public class Dictionnaire {

	private static Dictionnaire dictionnaire = null;

	ITraduction traduction = null;

	private Dictionnaire() {
		traduction = getSavedTraduction();
	}

	public ITraduction getTraduction() {
		return traduction;
	}

	/**
	 * Retourne la bonne traduction selon si l'utilisateur en a sauvegardé une ou non.
	 * @return
	 */
	private ITraduction getSavedTraduction() {
		//TODO
		return new TraductionFr();
	}

	public static Dictionnaire getInstance() {
		if(Dictionnaire.dictionnaire == null) {
			Dictionnaire.dictionnaire = new Dictionnaire();
		}
		return Dictionnaire.dictionnaire;
	}

	public void setTraduction(ITraduction traduction){
		this.traduction = traduction;
	}
}
