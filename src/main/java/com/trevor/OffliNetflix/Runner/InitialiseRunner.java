package com.trevor.OffliNetflix.Runner;

import com.trevor.OffliNetflix.Film.Film;
import com.trevor.OffliNetflix.Film.FilmRepository;
import com.trevor.OffliNetflix.Genre.Genre;
import com.trevor.OffliNetflix.Genre.GenreRepository;
import com.trevor.OffliNetflix.Star.Star;
import com.trevor.OffliNetflix.Star.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Configuration
public class InitialiseRunner implements CommandLineRunner {

    @Autowired
    private FilmRepository filmRepo;
    @Autowired
    private StarRepository starRepo;
    @Autowired
    private GenreRepository genreRepo;

    @Override
    public void run(String...args) throws Exception {
        initialiseGenres();
        initialiseStars();
        initialiseFilms();
    }

    public void initialiseGenres() {
        Genre horror = new Genre("Horror");
        Genre thriller = new Genre("Slasher");
        Genre romance = new Genre("Slasher");
        Genre scienceFiction = new Genre("Science Fiction");
        Genre action = new Genre("Action");
        Genre crime = new Genre("Crime");
        Genre comedy = new Genre("Comedy");
        Genre drama = new Genre("Drama");
        Genre superhero = new Genre("Superhero");
        Genre adventure = new Genre("Adventure");
        Genre fantasy = new Genre("Fantasy");
        Genre sport = new Genre("Sport");

        genreRepo.saveAll(List.of(
                horror,
                thriller,
                romance,
                scienceFiction,
                action,
                crime,
                comedy,
                drama,
                superhero,
                adventure,
                fantasy,
                sport
        ));
    }
    public void initialiseStars() {
        Star emilyBlunt = new Star("Emily Blunt");
        Star johnKrasinski = new Star("John Krasinski");

        starRepo.saveAll(List.of(
                emilyBlunt,
                johnKrasinski
        ));
    }
    public void initialiseFilms() throws IOException {
        Stream<Path> walkStream = Files.walk(Paths.get(Film.getRootDir()));
        List<Path> allFiles = walkStream.filter(p -> p.toFile().isFile()).toList();

        for (Path p : allFiles) {
            String s = p.toString();
            if (s.endsWith("mp4") || s.endsWith("mkv") || s.endsWith("avi")) {
                Path fileName = p.getFileName();
                System.out.printf("fileName: %s\n", fileName);

                Matcher m = Pattern.compile("(.*)\\((\\d{4})\\)(.*)").matcher(fileName.toString());
                int filmReleaseYear = -1;
                if (m.find())
                    filmReleaseYear = Integer.parseInt(m.group(2));
                System.out.printf("filmReleaseYear: %d\n", filmReleaseYear);

                String filmName = fileName.toString()
                        .replaceAll(" \\(\\d{4}\\)", "")
                        .replaceAll("(.mp4|.mkv|.avi)", "");
                System.out.printf("Saving new film(%s, %d, %s)\n", filmName, filmReleaseYear, s);

                filmRepo.save(new Film(filmName, filmReleaseYear, s));
            }
        }
    }
}
