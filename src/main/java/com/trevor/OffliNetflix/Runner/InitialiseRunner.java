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
//        initialiseGenres();
//        initialiseStars();
//        initialiseFilms();
    }

    public void initialiseGenres() {
        Genre horror = new Genre("Horror".toLowerCase());
        Genre psychologicalHorror = new Genre("Psychological Horror".toLowerCase());
        Genre thriller = new Genre("Thriller".toLowerCase());
        Genre slasher = new Genre("Slasher".toLowerCase());
        Genre psychologicalThriller = new Genre("Psychological Thriller".toLowerCase());
        Genre romance = new Genre("Romance".toLowerCase());
        Genre scienceFiction = new Genre("Science Fiction".toLowerCase());
        Genre action = new Genre("Action".toLowerCase());
        Genre crime = new Genre("Crime".toLowerCase());
        Genre comedy = new Genre("Comedy".toLowerCase());
        Genre drama = new Genre("Drama".toLowerCase());
        Genre superhero = new Genre("Superhero".toLowerCase());
        Genre adventure = new Genre("Adventure".toLowerCase());
        Genre fantasy = new Genre("Fantasy".toLowerCase());
        Genre sport = new Genre("Sport".toLowerCase());
        Genre mystery = new Genre("Mystery".toLowerCase());

        genreRepo.saveAll(List.of(
                horror,
                psychologicalHorror,
                thriller,
                slasher,
                psychologicalThriller,
                romance,
                scienceFiction,
                action,
                crime,
                comedy,
                drama,
                superhero,
                adventure,
                fantasy,
                sport,
                mystery
        ));
    }
    public void initialiseStars() throws IOException {
        Stream<Path> walkStream = Files.walk(Paths.get(Star.getRootDir()));
        List<Path> allFiles = walkStream.filter(p -> p.toFile().isFile()).toList();

        for (Path p : allFiles) {
            String imageUrl = p.toString().replace(Film.getRootDir(), "http://localhost:8081/");
            String name = p.getFileName().toString();
            name = name.substring(0, name.length() - 4);
            Star star = new Star(name, imageUrl, name.replaceAll(" ", "-"));
            System.out.println(star);
            starRepo.save(star);
        }
    }
    public void initialiseFilms() throws IOException {
        Stream<Path> walkStream = Files.walk(Paths.get(Film.getRootDir()));
        List<Path> allFiles = walkStream.filter(p -> p.toFile().isFile()).toList();

        for (Path p : allFiles) {
            String s = p.toString();
            if (s.endsWith("mp4") || s.endsWith("mkv") || s.endsWith("avi")) {
                if (!s.contains("[Collection]") && !s.contains("web-assets")) {
                    Path fileName = p.getFileName();
                    System.out.printf("fileName: %s\n", fileName);

                    Matcher m = Pattern.compile("(.*)\\((\\d{4})\\)(.*)").matcher(fileName.toString());
                    int filmReleaseYear = -1;
                    if (m.find())
                        filmReleaseYear = Integer.parseInt(m.group(2));
                    System.out.printf("filmReleaseYear: %d\n", filmReleaseYear);

                    String directoryPath = s.substring(0, s.lastIndexOf('\\'));
                    System.out.printf("directoryPath: %s\n", directoryPath);

                    String filmPath = fileName.toString();
                    System.out.printf("filmPath: %s\n", filmPath);

                    String filmName = filmPath
                            .replaceAll(" \\(\\d{4}\\)", "")
                            .replaceAll("(.mp4|.mkv|.avi)", "");
                    System.out.printf("filmName: %s\n", filmName);

                    String posterPath = directoryPath.concat("/poster.jpg").replace(Film.getRootDir(), "http://localhost:8081/");
                    System.out.printf("posterPath: %s\n", posterPath);

                    String backdropPath = directoryPath.concat("/backdrop.jpg").replace(Film.getRootDir(), "http://localhost:8081/");
                    System.out.printf("backdropPath: %s\n", backdropPath);

                    String synopsis = "Synopsis placeholder";

                    filmRepo.save(new Film(filmName, -1, filmReleaseYear, directoryPath, posterPath, backdropPath, filmPath, synopsis));
                }
            }
        }
    }
}
