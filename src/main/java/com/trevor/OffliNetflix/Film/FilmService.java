package com.trevor.OffliNetflix.Film;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class FilmService {

    public List<Film> getFilms() {
        return List.of(
                new Film(
                        1L,
                        "A Quiet Place",
                        2018,
                        new HashSet<>(Arrays.asList("Drama", "Horror", "Sci-Fi")),
                        new HashSet<>(Arrays.asList("Emily Blunt", "John Krasinski", "Millicent Simmonds")),
                        "A Quiet Place (2018)"
                )
        );
    }
}
