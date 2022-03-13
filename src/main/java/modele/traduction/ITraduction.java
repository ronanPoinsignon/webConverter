package modele.traduction;

public interface ITraduction {

	/**
	 * Retourne l'abbréviation du nom de la langue (ex : Français = FR)
	 * @return
	 */
	String getAbbreviation();

	String noPlaylistFound();
	String privatePlaylist();
}
