package com.trevor.OffliNetflix.Film;

import com.trevor.OffliNetflix.Genre.Genre;
import com.trevor.OffliNetflix.Genre.GenreRepository;
import com.trevor.OffliNetflix.Star.Star;
import com.trevor.OffliNetflix.Star.StarRepository;
import com.trevor.OffliNetflix.TMDBFilm.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

// Interacts with the database via FilmRepository to return responses to FilmController

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    private final StarRepository starRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, GenreRepository genreRepository, StarRepository starRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.starRepository = starRepository;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Optional<Film> getFilmById(UUID id) {
        return filmRepository.findById(id);
    }

    public Optional<List<Film>> getFilmsByName(String name) {
        return filmRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<List<Film>> getFilmsByGenre(String genre) {
        Optional<List<Genre>> g = genreRepository.findByNameContainingIgnoreCase(genre);

        if (g.isPresent()) {
            g.get().stream().forEach(System.out::print);
        }

        return filmRepository.findByGenresOfFilmIn(g);
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
/*
    public Optional<List<Film>> updateInformation(UUID[] filmsId, String[] filmsGenres, String[] filmsStars) {
        if (filmsId.length != filmsGenres.length || filmsGenres.length != filmsStars.length) {
            String message = "Mismatch between filmsId.length ["
                    .concat(String.valueOf(filmsId.length))
                    .concat("], filmsGenre.length [")
                    .concat(String.valueOf(filmsGenres.length))
                    .concat("] and filmsStars.length [")
                    .concat(String.valueOf(filmsStars.length))
                    .concat("]!");
            throw new IllegalStateException(message);
        }

        int count = filmsId.length;
        Optional<List<Film>> updatedFilms = null;

        for (int loop = 0; loop < count; loop++) {
            updateGenres(filmsId[loop], filmsGenres[loop]);
            Film updatedFilm = updateStars(filmsId[loop], filmsStars[loop]);
            updatedFilms.get().add(updatedFilm);
        }

        return updatedFilms;
    }
*/
    public Film updateStars(UUID filmId, String stars) {
        Optional<Film> film = getFilmById(filmId);
        if (film.isEmpty())
            throw new IllegalStateException("Film '" + film + "' not found!");

        Set<Star> starSet = Star.extractStars(stars, starRepository);

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

        film.get().setBackdropUrl(result.getBackdrop_path());
        film.get().setImageUrl(result.getPoster_path());
        film.get().setSynopsis(result.getOverview());

        System.out.printf("result.backdrop_path: %s\n", result.getBackdrop_path());
        System.out.printf("result.poster_path: %s\n", result.getPoster_path());
        System.out.printf("result.overview: %s\n", result.getOverview());


        filmRepository.save(film.get());
        return film.get();
    }

//    public List<Film> getFilmsByMostGenres() {
//        List<Film> filmsWithMostGenres = filmRepository.getFilmsByMostGenres();
//        return filmsWithMostGenres;
//    }
}
