package com.trevor.OffliNetflix.TMDBFilm;

import java.util.Arrays;

public class TMDBCredits {
    private int id;
    private TMDBStar[] cast;

    public TMDBCredits() {
    }
    public TMDBCredits(int id, TMDBStar[] cast) {
        this.id = id;
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TMDBStar[] getCast() {
        return cast;
    }

    public void setCast(TMDBStar[] cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "TMDBCredits{" +
                "id=" + id +
                ", cast=" + Arrays.toString(cast) +
                '}';
    }
}
