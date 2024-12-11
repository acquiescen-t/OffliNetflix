package com.trevor.OffliNetflix.Film;

import com.trevor.OffliNetflix.TMDBFilm.Result;
import com.trevor.OffliNetflix.TMDBFilm.TMDBCredits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Handles requests and responses. Delegates tasks of interaction with the DB via the FilmService class.

@RestController
@RequestMapping("api/v1/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        System.out.println("Calling getAllFilms() in FilmController");
        return new ResponseEntity<List<Film>>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Film>> getFilmById(@PathVariable UUID id) {
        return new ResponseEntity<Optional<Film>>(filmService.getFilmById(id), HttpStatus.OK);
    }

    @GetMapping("/search/film-name/{name}")
    public ResponseEntity<Optional<List<Film>>> getFilmsByName(@PathVariable String name) {
        return new ResponseEntity<Optional<List<Film>>>(filmService.getFilmsByName(name), HttpStatus.OK);
    }

    @GetMapping("/search/genre-name/{genre}")
    public ResponseEntity<Optional<List<Film>>> getFilmsByGenre(@PathVariable String genre) {
        System.out.printf("Calling getFilmsByGenre(%s) in FilmController.", genre);
        return new ResponseEntity<Optional<List<Film>>>(filmService.getFilmsByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("/search/star-name/{star}")
    public ResponseEntity<Optional<List<Film>>> getFilmsByStar(@PathVariable String star) {
        System.out.printf("Calling getFilmsByStar(%s) in FilmController.", star);
        return new ResponseEntity<Optional<List<Film>>>(filmService.getFilmsByStar(star), HttpStatus.OK);
    }

    @GetMapping("/get-random/{count}")
    public ResponseEntity<List<Film>> getRandomFilms(@PathVariable int count) {
        return new ResponseEntity<List<Film>>(filmService.getRandomFilms(count), HttpStatus.OK);
    }

    @GetMapping("/get-by-most-genres/{count}")
    public ResponseEntity<List<Film>> getFilmsByMostGenres(@PathVariable int count) {
        List<UUID> filmIds = filmService.getFilmsByMostGenres(count);
        List<Film> filmsWithMostGenres = new ArrayList<>();
        for(UUID filmId : filmIds) {
            Optional<Film> film = filmService.getFilmById(filmId);
            if (!film.isEmpty())
                filmsWithMostGenres.add(film.get());
        }
        return new ResponseEntity<List<Film>>(filmsWithMostGenres, HttpStatus.OK);
    }

    @PutMapping("/update-genres")
    public ResponseEntity<Film> updateFilmGenres(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Film>(filmService.updateGenres(UUID.fromString(payload.get("filmId")), payload.get("genres")), HttpStatus.CREATED);
    }

//    @PutMapping("/update-stars")
//    public ResponseEntity<Film> updateFilmStars(@RequestBody Map<String, String> payload) {
//        return new ResponseEntity<Film>(filmService.updateStars(UUID.fromString(payload.get("filmId")), payload.get("stars")), HttpStatus.CREATED);
//    }

    @PutMapping("/update-film/{filmId}")
    public ResponseEntity<Film> updateFilm(@PathVariable UUID filmId, @RequestBody Result result) {
        System.out.println("result: " + result);
        return new ResponseEntity<Film>(filmService.updateFilm(filmId, result), HttpStatus.OK);
    }

    @PutMapping("/{filmId}/update-stars")
    public ResponseEntity<Film> updateFilmStars(@PathVariable UUID filmId, @RequestBody TMDBCredits credits) {
        System.out.println("credits: " + credits);
        return new ResponseEntity<Film>(filmService.updateStars(filmId, credits), HttpStatus.OK);
    }
}
