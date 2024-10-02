package com.trevor.OffliNetflix.Film;

import com.trevor.OffliNetflix.Genre.Genre;
import com.trevor.OffliNetflix.Star.Star;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Film {

    private static String rootDir = "G:\\Movies\\";

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

    @ManyToMany
    @JoinTable(
            name = "film_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    Set<Genre> genresOfFilm;

    @ManyToMany
    @JoinTable(
            name = "film_stars",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "star_id")
    )
    Set<Star> starsOfFilm;

    private String name;
    private int releaseYear;
    private String directoryPath;

    public Film() {

    }

    public Film(String name, int releaseYear, String directoryPath) {
        this.setName(name);
        this.setReleaseYear(releaseYear);
        this.setDirectoryPath(directoryPath);
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

    public Set<Genre> getGenresOfFilm() {
        return genresOfFilm;
    }
    public void setGenresOfFilm(Set<Genre> genres) {
        this.genresOfFilm = genres;
    }

    public Set<Star> getStarsOfFilm() {
        return starsOfFilm;
    }
    public void setStarsOfFilm(Set<Star> stars) {
        this.starsOfFilm = stars;
    }

    public String getDirectoryPath() {
            return directoryPath;
    }
    public void setDirectoryPath(String directoryPath) {
        if (!directoryPath.startsWith(rootDir))
            this.directoryPath = rootDir.concat(directoryPath);
        else
            this.directoryPath = directoryPath;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", genresOfFilm=" + genresOfFilm +
                ", starring=" + starsOfFilm +
                ", directoryPath='" + directoryPath + '\'' +
                '}';
    }
}
