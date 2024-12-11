package com.trevor.OffliNetflix.Film;

import com.trevor.OffliNetflix.Genre.Genre;
import com.trevor.OffliNetflix.Star.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

      Optional<List<Film>> findByNameContainingIgnoreCase(String name);
      Optional<List<Film>> findByGenresOfFilmIn(Optional<List<Genre>> genre);
      Optional<List<Film>> findByStarsOfFilmIn(Optional<List<Star>> star);
      Optional<Film> findById(UUID uuid);
}
