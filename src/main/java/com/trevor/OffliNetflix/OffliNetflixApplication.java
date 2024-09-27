package com.trevor.OffliNetflix;

import com.trevor.OffliNetflix.Film.Film;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
public class OffliNetflixApplication {

	public static void main(String[] args) throws IOException {
		// EnumerateDirectory();
		SpringApplication.run(OffliNetflixApplication.class, args);
	}

	public static void EnumerateDirectory() throws IOException {
		Stream<Path> walkStream = Files.walk(Paths.get(Film.getRootDir()));
		List<Path> allFiles = walkStream.filter(p -> p.toFile().isFile()).toList();
		List<Path> filmFiles = new ArrayList<>();

		for (Path p : allFiles) {
			String s = p.toString();
			if (s.endsWith("mp4") || s.endsWith("mkv") || s.endsWith("avi")) {
				String filmName = FilenameUtils.removeExtension(s);
				System.out.printf("filmName: %s\n", s);

				Matcher m = Pattern.compile("(.*)\\((\\d{4})\\)(.*)").matcher(filmName);
				int filmReleaseYear = -1;
				if (m.find())
					filmReleaseYear = Integer.parseInt(m.group(2));
				System.out.printf("filmReleaseYear: %d\n", filmReleaseYear);
			}
		}
	}
}



