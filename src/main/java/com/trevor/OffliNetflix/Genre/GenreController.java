package com.trevor.OffliNetflix.Genre;

// Handles requests and responses. Delegates task of interaction with the DB via the FilmService class.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        return new ResponseEntity<List<Genre>>(genreService.getAllGenres(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Genre>> getGenreById(@PathVariable Long id) {
        return new ResponseEntity<Optional<Genre>>(genreService.getGenreById(id), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Optional<List<Genre>>> getGenresByName(@PathVariable String name) {
        return new ResponseEntity<Optional<List<Genre>>>(genreService.getGenresByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody String genreName) {
        return new ResponseEntity<Genre>(genreService.createGenre(genreName), HttpStatus.CREATED);
    }

}
