package com.trevor.OffliNetflix.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<List<Genre>> findByNameContainingIgnoreCase(String name);

    Optional<Genre> getByNameIgnoreCase(String name);

    Optional<Genre> getByTmdbId(int id);
}
