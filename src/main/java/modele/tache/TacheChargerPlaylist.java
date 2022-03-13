package modele.tache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestPlaylistInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.playlist.PlaylistInfo;
import com.github.kiulian.downloader.model.playlist.PlaylistVideoDetails;

import prog.video.Video;
import prog.video.VideoYtb;

public class TacheChargerPlaylist extends Tache<List<Video>> {

	private String id;
	private boolean hasPb = false;
	private List<String> listeVideoError = new ArrayList<>();

	public TacheChargerPlaylist(String id) {
		this.id = id;
	}

	@Override
	public List<Video> download() {
		YoutubeDownloader downloader = new YoutubeDownloader();
		PlaylistInfo playlist = null;
		List<Video> liste = new ArrayList<>();
		RequestPlaylistInfo request = new RequestPlaylistInfo(id);
		Response<PlaylistInfo> response = downloader.getPlaylistInfo(request);
		playlist = response.data();
		if(playlist == null) {
			return Collections.emptyList();
		}
		List<PlaylistVideoDetails> videos = playlist.videos();
		for(PlaylistVideoDetails video : videos) {
			Video vid = null;
			try {
				vid = new VideoYtb(video.videoId());
				liste.add(vid);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	public boolean hasPb() {
		return hasPb;
	}

	public List<String> getLinkError(){
		return listeVideoError;
	}

}
