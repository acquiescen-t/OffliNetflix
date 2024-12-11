package com.trevor.OffliNetflix.Genre;

import com.trevor.OffliNetflix.Film.Film;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Genre {

    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID id;

    @ManyToMany(mappedBy = "genresOfFilm")
    Set<Film> filmsOfMatchingGenre;

    private String name;

    private Integer tmdbId;

    public Genre() {

    }
    public Genre(String name) {
        setName(name);
    }
    public Genre(String name, Integer tmdbId) {
        setName(name);
        setTmdbId(tmdbId);
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

    public Integer getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId == null ? 0 : tmdbId;
    }

    public static Set<Genre> extractGenres(String genres, GenreRepository genreRepository) {
        String[] genreList = genres.split(", ");
        Set<Genre> genreSet = new HashSet<>();

        for (String s : genreList) {
            System.out.println(s);
            Optional<Genre> g = genreRepository.getByNameIgnoreCase(s);
            if (g.isEmpty())
                throw new IllegalStateException(("Genre '" + g + "' not found!"));

            genreSet.add(g.get());
        }
        return genreSet;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tmdbId='" + tmdbId + '\'' +
                '}';
    }
}
