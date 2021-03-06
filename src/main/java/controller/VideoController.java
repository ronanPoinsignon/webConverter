package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exception.NoVideoFoundException;
import modele.downloader.Downloader;
import modele.tache.TacheConvertirInstant;
import service.CounterService;
import utils.Utils;

@Controller
@RequestMapping("/video")
public class VideoController {

	protected Downloader downloader = new Downloader();
	private CounterService counterService;

	public VideoController(CounterService counterService) {
		this.counterService = counterService;
	}

	@GetMapping(value = "")
	public void convert(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "id") String id) throws Exception {
		List<String> listeExtensions = new ArrayList<>();
		listeExtensions.add("mp3");
		int number = counterService.getNextNumber();
		File folder = Utils.getWebFolder(String.valueOf(number));
		TacheConvertirInstant tache = new TacheConvertirInstant(id, folder, Utils.BIT_RATE, listeExtensions);
		File file = tache.download();

		if(file == null) {
			Utils.deleteFolder(folder);
			throw new NoVideoFoundException();
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		response.setContentLength((int) file.length());
		try(InputStream inputStream = new BufferedInputStream(new FileInputStream(file))){
			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
		Utils.deleteFolder(folder);
		counterService.removeNumber(number);
	}
}
