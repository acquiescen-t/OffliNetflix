package com.trevor.OffliNetflix.Film;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
@Table
public class Film {

    public static String rootDir = "G:\\Movies\\";

    @Id
    @SequenceGenerator(
            name = "film_sequence",
            sequenceName = "film_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "film_sequence"
    )
    private Long id;
    private String name;
    private int releaseYear;
    private HashSet<String> genres;
    private HashSet<String> stars;
    private String directoryPath;

    public Film() {

    }

    public Film(Long id, String name, int releaseYear, HashSet<String> genres, HashSet<String> stars, String directoryPath) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.stars = stars;
        this.directoryPath = String.join(rootDir, directoryPath);
    }

    public Film(String name, int releaseYear, HashSet<String> genres, HashSet<String> stars, String directoryPath) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.stars = stars;
        this.directoryPath = String.join(rootDir, directoryPath);
    }

    public static String getRootDir() {
        return rootDir;
    }

    public static void setRootDir(String rootDir) {
        Film.rootDir = rootDir;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public HashSet<String> getGenres() {
        return genres;
    }

    public void setGenres(HashSet<String> genres) {
        this.genres = genres;
    }

    public HashSet<String> getStars() {
        return stars;
    }

    public void setStars(HashSet<String> stars) {
        this.stars = stars;
    }

    public String getDirectoryPath() {
        return String.join(rootDir, directoryPath);
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = String.join(rootDir, directoryPath);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", genres=" + genres +
                ", stars=" + stars +
                ", directoryPath='" + String.join(rootDir, directoryPath) + '\'' +
                '}';
    }
}
