package com.trevor.OffliNetflix.Genre;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class GenreConfig {

    @Bean
    @Order(1)
    CommandLineRunner genreCLR(GenreRepository repository) {
        return args -> {
            Genre horror = new Genre("Horror");
            Genre thriller = new Genre("Slasher");
            Genre romance = new Genre("Slasher");
            Genre scienceFiction = new Genre("Science Fiction");
            Genre action = new Genre("Action");
            Genre crime = new Genre("Crime");
            Genre comedy = new Genre("Comedy");
            Genre drama = new Genre("Drama");
            Genre superhero = new Genre("Superhero");
            Genre adventure = new Genre("Adventure");
            Genre fantasy = new Genre("Fantasy");
            Genre sport = new Genre("Sport");

            repository.saveAll(List.of(
                    horror,
                    thriller,
                    romance,
                    scienceFiction,
                    action,
                    crime,
                    comedy,
                    drama,
                    superhero,
                    adventure,
                    fantasy,
                    sport
            ));
        };
    }
}
