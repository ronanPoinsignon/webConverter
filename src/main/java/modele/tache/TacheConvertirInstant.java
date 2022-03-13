package modele.tache;

import java.io.File;
import java.util.List;

/**
 * {@link Tache} permettant une conversion instantannée en vidéo depuis un lien donné sans passer par les actions
 * normales d'ajout dans la liste d'une vidéo puis de conversion.
 * @author ronan
 *
 */
public class TacheConvertirInstant extends Tache<File> {

	private String url;
	private File folder;
	private int bitRate;
	private List<String> listeExtensions;

	public TacheConvertirInstant(String url, File folder, int bitRate, List<String> listeExtensions) {
		this.url = url;
		this.folder = folder;
		this.bitRate = bitRate;
		this.listeExtensions = listeExtensions;
	}

	@Override
	public File download() throws Exception {
		final TacheCharger tache = new TacheCharger(url);
		final TacheConvertirToFile tacheConv = new TacheConvertirToFile(tache.download(), folder, bitRate, listeExtensions);

		List<File> files = tacheConv.download();
		if(files.isEmpty()) {
			return null;
		} else {
			return files.get(0);
		}
	}

}
