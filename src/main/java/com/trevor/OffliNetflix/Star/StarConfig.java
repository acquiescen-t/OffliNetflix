package com.trevor.OffliNetflix.Star;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class StarConfig {

    @Bean
    @Order(2)
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
