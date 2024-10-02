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

    public Optional<Film> getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    public Optional<List<Film>> getFilmsByName(String name) {
        return filmRepository.findByNameContainingIgnoreCase(name);
    }

    public Film updateGenres(String filmId, String genres) {
        Optional<Film> film = getFilmById(Long.parseLong(filmId));
        if (film.isEmpty())
            throw new IllegalStateException("Film '" + film + "' not found!");

        Set<Genre> genreSet = Genre.extractGenres(genres, genreRepository);

        film.get().setGenresOfFilm(genreSet);
        filmRepository.save(film.get());

        return film.get();
    }

    public Film updateStars(String filmId, String stars) {
        Optional<Film> film = getFilmById(Long.parseLong(filmId));
        if (film.isEmpty())
            throw new IllegalStateException("Film '" + film + "' not found!");

        Set<Star> starSet = Star.extractStars(stars, starRepository);

        film.get().setStarsOfFilm(starSet);
        filmRepository.save(film.get());

        return film.get();
    }
}
