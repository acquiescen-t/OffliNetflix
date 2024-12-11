package com.trevor.OffliNetflix.Star;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<List<Star>> findByNameContainingIgnoreCase(String name);
    Optional<List<Star>> findBySearchableNameContainingIgnoreCase(String name);
    Optional<Star> getByNameIgnoreCase(String s);
}
