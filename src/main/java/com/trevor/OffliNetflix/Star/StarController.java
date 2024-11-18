package com.trevor.OffliNetflix.Star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/stars")
public class StarController {

    private final StarService starService;

    @Autowired
    public StarController(StarService starService) {
        this.starService = starService;
    }

    @GetMapping
    public ResponseEntity<List<Star>> getAllStars() {
        return new ResponseEntity<List<Star>>(starService.getAllStars(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Star>> getStarById(@PathVariable Long id) {
        return new ResponseEntity<Optional<Star>>(starService.getStarById(id), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Optional<List<Star>>> getStarsByName(@PathVariable String name) {
        return new ResponseEntity<Optional<List<Star>>>(starService.getStarsByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Star> createStar(@RequestBody String starName, String imageUrl) {
        return new ResponseEntity<Star>(starService.createStar(starName, imageUrl), HttpStatus.CREATED);
    }
}
