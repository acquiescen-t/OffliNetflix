package com.trevor.OffliNetflix.Film;

import com.trevor.OffliNetflix.Genre.Genre;
import com.trevor.OffliNetflix.Genre.GenreRepository;
import com.trevor.OffliNetflix.Star.Star;
import com.trevor.OffliNetflix.Star.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
}
