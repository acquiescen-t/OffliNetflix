package com.trevor.OffliNetflix.Star;

import com.trevor.OffliNetflix.Film.Film;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Star {

    private static String rootDir = "G:\\Movies\\web-assets\\stars";

    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID id;

    @ManyToMany(mappedBy = "starsOfFilm")
    Set<Film> starFilm;

    private String name;
    private String imageUrl;

    private String searchableName;
    public Star() {

    }
    public Star(String name, String imageUrl, String searchableName) {
        setName(name);
        setImageUrl(imageUrl);
        setSearchableName(searchableName);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchableName() { return searchableName; }
    public void setSearchableName(String searchableName) { this.searchableName = searchableName; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public static String getRootDir() { return rootDir; }

    public static Set<Star> extractStars(String stars, StarRepository starRepository) {
        String[] starList = stars.split(", ");
        Set<Star> starSet = new HashSet<>();

        for (String s : starList) {
            Optional<Star> star = starRepository.getByNameIgnoreCase(s);
            if (star.isEmpty()) {
                throw new IllegalStateException(("Star '" + star + "' not found!"));
            }

            starSet.add(star.get());
        }
        return starSet;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", searchableName='" + searchableName + '\'' +
                '}';
    }
}
