package com.trevor.OffliNetflix.Star;

import com.trevor.OffliNetflix.Film.Film;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Star {

    @Id
    @SequenceGenerator(
            name = "stars_sequence",
            sequenceName = "stars_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stars_sequence"
    )
    private Long id;

    @ManyToMany(mappedBy = "starring")
    Set<Film> starFilm;

    private String name;

    public Star() {

    }
    public Star(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
