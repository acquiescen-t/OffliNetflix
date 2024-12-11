package com.trevor.OffliNetflix.Star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarService {

    private final StarRepository starRepository;

    @Autowired
    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public List<Star> getAllStars() {
        return starRepository.findAll();
    }

    public Optional<Star> getStarById(Long id) {
        return starRepository.findById(id);
    }

    public Optional<List<Star>> getStarsByName(String name) {
        return starRepository.findByNameContainingIgnoreCase(name);
    }

    public Star createStar(Star star) {
        return starRepository.save(star);
    }
}
