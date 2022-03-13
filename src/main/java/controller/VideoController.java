package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exception.NoVideoFoundException;
import modele.Counter;
import modele.downloader.Downloader;
import modele.tache.TacheConvertirInstant;
import utils.Utils;

@Controller
@RequestMapping("/video")
public class VideoController {

	protected Downloader downloader = new Downloader();

	@GetMapping(value = "")
	public void convert(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "id") String id) throws Exception {
		List<String> listeExtensions = new ArrayList<>();
		listeExtensions.add("mp3");
		Counter counter = Counter.getInstance();
		int number = counter.getNextNumber();
		File folder = Utils.getWebFolder(String.valueOf(number));
		TacheConvertirInstant tache = new TacheConvertirInstant(id, folder, Utils.BIT_RATE, listeExtensions);
		File file = tache.download();

		if(file == null) {
			Files.walk(folder.toPath())
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach(File::delete);
			counter.removeNumber(number);
			throw new NoVideoFoundException();
		}

		//get the mimetype
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			//unknown mimetype so set the mimetype to application/octet-stream
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "attachment;" + String.format("inline; filename=\"" + file.getName() + "\""));
		//Here we have mentioned it to show as attachment
		//response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());

		Files.walk(folder.toPath())
		.sorted(Comparator.reverseOrder())
		.map(Path::toFile)
		.forEach(File::delete);
		counter.removeNumber(number);
	}
}
