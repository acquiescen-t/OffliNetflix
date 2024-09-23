package com.trevor.OffliNetflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class OffliNetflixApplication {

	public static void main(String[] args) {
		// EnumerateDirectory();
		SpringApplication.run(OffliNetflixApplication.class, args);
	}

	public static void EnumerateDirectory() throws InterruptedException {
		File dir = new File("");
		File[] filesInDir = dir.listFiles();
		if (filesInDir != null)
			for (File f : filesInDir)
				System.out.println(f.getPath());
	}
}
