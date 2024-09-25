package com.trevor.OffliNetflix.Star;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StarConfig {

    @Bean
    CommandLineRunner starCLR(StarRepository repository) {
        return args -> {
            Star emilyBlunt = new Star("Emily Blunt");
            Star johnKrasinski = new Star("John Krasinski");

            repository.saveAll(List.of(
                    emilyBlunt,
                    johnKrasinski
            ));
        };
    }
}
