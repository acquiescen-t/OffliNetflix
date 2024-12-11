package com.trevor.OffliNetflix.Film;

import com.trevor.OffliNetflix.Genre.Genre;
import com.trevor.OffliNetflix.Genre.GenreRepository;
import com.trevor.OffliNetflix.Star.Star;
import com.trevor.OffliNetflix.Star.StarRepository;
import com.trevor.OffliNetflix.TMDBFilm.Result;
import com.trevor.OffliNetflix.TMDBFilm.TMDBCredits;
import com.trevor.OffliNetflix.TMDBFilm.TMDBStar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

// Interacts with the database via FilmRepository to return responses to FilmController

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    private final FilmRepositoryCustom filmRepositoryCustom;
    private final GenreRepository genreRepository;
    private final StarRepository starRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, FilmRepositoryCustom filmRepositoryCustom, GenreRepository genreRepository, StarRepository starRepository) {
        this.filmRepository = filmRepository;
        this.filmRepositoryCustom = filmRepositoryCustom;
        this.genreRepository = genreRepository;
        this.starRepository = starRepository;
    }

    public List<Film> getAllFilms() {
        System.out.println("Calling filmRepository.findAll() in getAllFilms()");
        return filmRepository.findAll();
    }

    public Optional<Film> getFilmById(UUID id) {
        return filmRepository.findById(id);
    }

    public Optional<List<Film>> getFilmsByName(String name) {
        return filmRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<List<Film>> getFilmsByGenre(String genre) {
        System.out.printf("Calling getFilmsByGenre(%s) in FilmService", genre);
        Optional<List<Genre>> g = genreRepository.findByNameContainingIgnoreCase(genre);

        if (g.isPresent()) {
            g.get().stream().forEach(System.out::print);
        }

        return filmRepository.findByGenresOfFilmIn(g);
    }

    public Optional<List<Film>> getFilmsByStar(String star) {
        System.out.printf("Calling getFilmsByStar(%s) in FilmService\n", star);
        Optional<List<Star>> s = starRepository.findBySearchableNameContainingIgnoreCase(star);

        if (s.isPresent()) {
            s.get().stream().forEach(System.out::print);
        }

        return filmRepository.findByStarsOfFilmIn(s);
    }

    public List<UUID> getFilmsByMostGenres(int count) {
        List<Object[]> filmsWithMostGenres = filmRepositoryCustom.getFilmsByMostGenres(count);
        List<UUID> filmIds = new ArrayList<>();
        for (Object[] objArr : filmsWithMostGenres) {
            UUID filmId = (UUID)objArr[1];
            filmIds.add(filmId);
        }
        return filmIds;
    }

    public List<Film> getRandomFilms(int count) {
        List<Film> allFilms = filmRepository.findAll();
        Collections.shuffle(allFilms);
        allFilms = allFilms.subList(0, count);
        return allFilms;
    }
    public Film updateGenres(UUID filmId, String genres) {
        Optional<Film> film = getFilmById(filmId);
        if (film.isEmpty())
            throw new IllegalStateException("Film '" + film + "' not found!");

        Set<Genre> genreSet = Genre.extractGenres(genres, genreRepository);

        film.get().setGenresOfFilm(genreSet);
        filmRepository.save(film.get());

        return film.get();
    }

    public Film updateStars(UUID filmId, TMDBCredits credits) {
        System.out.println("Calling updateFilm with:");
        System.out.printf("UUID: %s\n", filmId);
        System.out.printf("credits: %s\n", credits);

        Optional<Film> film = getFilmById(filmId);
        if (film.isEmpty())
            throw new IllegalStateException("Film '" + film + "' not found!");

        TMDBStar[] cast = credits.getCast();
        Set<Star> starSet = new HashSet<>();
        for (int loop = 0; loop < cast.length; loop++) {
            Star star = new Star(cast[loop].getName(), cast[loop].getProfile_path(), cast[loop].getName().replaceAll(" ", "-"));
            System.out.printf("Current star: %s\n", star);
            Optional<Star> starLookup = starRepository.getByNameIgnoreCase(star.getName());
            if (starLookup.isEmpty()) {
                System.out.printf("Star %s does not exist in database, adding them in.\n", star.getName());
                Star newStar = starRepository.save(star);
                starSet.add(newStar);
            } else {
                System.out.printf("Star %s already exists in database, not adding them in.\n", star.getName());
                starSet.add(starLookup.get());
            }
        }

        film.get().setStarsOfFilm(starSet);
        filmRepository.save(film.get());

        return film.get();
    }

    public Film updateFilm(UUID filmId, Result result) {
        System.out.println("Calling updateFilm with:");
        System.out.printf("UUID: %s\n", filmId);
        System.out.printf("result: %s\n", result);

        Optional<Film> film = getFilmById(filmId);
        if (film.isEmpty())
            throw new IllegalStateException("Film '" + film + "' not found!");

        System.out.printf("result.backdrop_path: %s\n", result.getBackdrop_path());
        film.get().setBackdropUrl(result.getBackdrop_path());

        System.out.printf("result.poster_path: %s\n", result.getPoster_path());
        film.get().setImageUrl(result.getPoster_path());

        System.out.printf("result.overview: %s\n", result.getOverview());
        film.get().setSynopsis(result.getOverview());
        film.get().setTmdb_id(result.getId());

        Set<Genre> updatedGenres = new HashSet<>();
        int[] genreIds = result.getGenre_ids();
        for (int gid : genreIds) {
            Optional<Genre> genre = genreRepository.getByTmdbId(gid);
            if (genre.isPresent())
                updatedGenres.add(genre.get());
        }
        System.out.printf("result.genre_ids: \n");
        for(int i : result.getGenre_ids()) {
            System.out.printf("%d\n", i);
        }
        film.get().setGenresOfFilm(updatedGenres);

        filmRepository.save(film.get());
        return film.get();
    }
}
