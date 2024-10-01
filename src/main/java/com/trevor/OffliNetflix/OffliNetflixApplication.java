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
		SpringApplication.run(OffliNetflixApplication.class, args);
	}
}



