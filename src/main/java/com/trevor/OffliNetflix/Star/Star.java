package com.trevor.OffliNetflix.Star;

import jakarta.persistence.*;

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
    private String name;

    public Star() {

    }

    public Star(Long id, String name) {
        this.id = id;
        this.name = name;
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
