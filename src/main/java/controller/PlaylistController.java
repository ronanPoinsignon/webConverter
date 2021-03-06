package controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import modele.downloader.Downloader;
import modele.tache.TacheChargerPlaylist;
import modele.tache.TacheConvertirToFile;
import modele.video.Video;
import service.CounterService;
import utils.Utils;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

	protected Downloader downloader = new Downloader();
	private CounterService counterService;

	public PlaylistController(CounterService counterService) {
		this.counterService = counterService;
	}

	@GetMapping(value = "", produces = "application/zip")
	public void convert(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "id") String id) throws IOException {
		TacheChargerPlaylist tache = new TacheChargerPlaylist(id);
		List<Video> videos = tache.download();
		List<File> files = new ArrayList<>();
		List<String> listeExtensions = new ArrayList<>();
		listeExtensions.add("mp3");
		int number = counterService.getNextNumber();
		File folder = Utils.getWebFolder(String.valueOf(number));
		TacheConvertirToFile convert = new TacheConvertirToFile(videos, folder, 0, listeExtensions);
		files.addAll(convert.download());

		final String name = "files.zip";
		response.setStatus(HttpServletResponse.SC_OK);
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"");
		response.getOutputStream().write(zipFiles(files));
		if(!files.isEmpty()) {
			Utils.deleteFolder(folder);
		}
		counterService.removeNumber(number);
	}

	private byte[] zipFiles(List<File> files) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(baos)) {
			byte[] bytes = new byte[2048];

			for (File file : files) {
				try (FileInputStream fis = new FileInputStream(file.getAbsolutePath());
						BufferedInputStream bis = new BufferedInputStream(fis)) {

					zos.putNextEntry(new ZipEntry(file.getName()));

					int bytesRead;
					while ((bytesRead = bis.read(bytes)) != -1) {
						zos.write(bytes, 0, bytesRead);
					}
					zos.closeEntry();
				}
			}
			return baos.toByteArray();
		}
	}

}
