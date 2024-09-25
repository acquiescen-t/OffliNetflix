package com.trevor.OffliNetflix;

import com.trevor.OffliNetflix.Film.Film;
import com.trevor.OffliNetflix.Util.ExtensionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class OffliNetflixApplication {

	public static void main(String[] args) {
		// EnumerateDirectory();
		SpringApplication.run(OffliNetflixApplication.class, args);
	}

	public static void EnumerateDirectory() {
		File rootDirectory = new File(Film.getRootDir());
		List<File> movieDirectories = List.of(rootDirectory.listFiles());

		if (movieDirectories != null)
			for (File f : movieDirectories)
				System.out.println(f.getPath());

	}


}
