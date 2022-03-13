package modele.traduction;

public class TraductionFr implements ITraduction {

	@Override
	public String getAbbreviation() {
		return "FR";
	}

	@Override
	public String noPlaylistFound() {
		return "Aucune playlist trouvée";
	}

	@Override
	public String privatePlaylist() {
		return "Votre playlist est peut-être en privée";
	}

}
