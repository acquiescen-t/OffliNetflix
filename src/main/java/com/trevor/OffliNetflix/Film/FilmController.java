package com.trevor.OffliNetflix.Film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return new ResponseEntity<List<Film>>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Film>> getFilmById(@PathVariable Long id) {
        return new ResponseEntity<Optional<Film>>(filmService.getFilmById(id), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Optional<List<Film>>> getFilmsByName(@PathVariable String name) {
        return new ResponseEntity<Optional<List<Film>>>(filmService.getFilmsByName(name), HttpStatus.OK);
    }

    @PostMapping("/update-genres")
    public ResponseEntity<Film> updateFilmGenres(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Film>(filmService.updateGenres(payload.get("filmId"), payload.get("genres")), HttpStatus.CREATED);
    }

    @PostMapping("/update-stars")
    public ResponseEntity<Film> updateFilmStars(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Film>(filmService.updateStars(payload.get("filmId"), payload.get("stars")), HttpStatus.CREATED);
    }
}
