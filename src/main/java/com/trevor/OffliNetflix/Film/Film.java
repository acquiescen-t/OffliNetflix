package com.trevor.OffliNetflix.Film;

import com.trevor.OffliNetflix.Genre.Genre;
import com.trevor.OffliNetflix.Star.Star;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Film {

    private static String rootDir = "G:\\Movies\\";

    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID id;

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

    private String imageUrl;
    private String backdropUrl;
    private String filmPath;
    private String synopsis;
    public Film() {

    }

    public Film(String name, int releaseYear, String directoryPath, String imageUrl, String backdropUrl, String filmPath, String synopsis) {
        this.setName(name);
        this.setReleaseYear(releaseYear);
        this.setDirectoryPath(directoryPath);
        this.setImageUrl(imageUrl);
        this.setBackdropUrl(backdropUrl);
        this.setFilmPath(filmPath);
        this.setSynopsis(synopsis);
    }

    public static String getRootDir() {
        return rootDir;
    }
    public static void setRootDir(String rootDir) {
        Film.rootDir = rootDir;
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

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBackdropUrl() { return backdropUrl; }
    public void setBackdropUrl(String backdropUrl) { this.backdropUrl = backdropUrl; }
    public String getFilmPath() { return filmPath; }
    public void setFilmPath(String filmPath) {
        this.filmPath = filmPath;
    }

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", genresOfFilm=" + genresOfFilm +
                ", starsOfFilm=" + starsOfFilm +
                ", directoryPath='" + directoryPath + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", backdropUrl='" + backdropUrl + '\'' +
                ", filmPath='" + filmPath + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
