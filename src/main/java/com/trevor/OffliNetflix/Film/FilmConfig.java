package com.trevor.OffliNetflix.Film;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
public class FilmConfig {

    @Bean
    CommandLineRunner commandLineRunner(FilmRepository repository) {
        return args -> {
            Film aQuietPlace = new Film(
                    "A Quiet Place",
                    2018,
                    new HashSet<>(Arrays.asList("Drama", "Horror", "Sci-Fi")),
                    new HashSet<>(Arrays.asList("Emily Blunt", "John Krasinski", "Millicent Simmonds")),
                    "A Quiet Place (2018)"
            );

            Film coherence = new Film(
                    "Coherence",
                    2013,
                    new HashSet<>(Arrays.asList("Drama", "Mystery", "Sci-Fi", "Thriller")),
                    new HashSet<>(Arrays.asList("Emily Baldoni", "Maury Sterling", "Nicholas Brendon")),
                    "Coherence (2013)"
            );

            repository.saveAll(List.of(aQuietPlace, coherence));
        };
    }
}
