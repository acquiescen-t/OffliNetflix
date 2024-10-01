package com.trevor.OffliNetflix.Film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

      Optional<List<Film>> findByNameContainingIgnoreCase(String name);
}
