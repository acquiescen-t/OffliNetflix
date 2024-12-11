package com.trevor.OffliNetflix.Film;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

//    public List<Object[]> getFilmsByStar(String star) {
//        String nativeQuery =
//                """
//                SELECT release_year, f.id, directory_path, film_path, image_url, name, synopsis, backdrop_url
//                FROM star s
//                INNER JOIN film_stars fs
//                ON s.id = fs.star_id
//                INNER JOIN film f
//                ON f.id = fs.film_id
//                WHERE UPPER(s.searchable_name) LIKE '
//                """.concat(star).concat("';");
//
//        Query query = entityManager.createNativeQuery(nativeQuery);
//        return query.getResultList();
//    }
    public List<Object[]> getFilmsByMostGenres(int count) {
        String nativeQuery = "SELECT release_year, f.id, directory_path, film_path, image_url, name, synopsis, backdrop_url " +
                "FROM Film f " +
                "INNER JOIN ( " +
                "    SELECT fg.film_id, COUNT(*) AS genre_count " +
                "    FROM film_genres fg " +
                "    INNER JOIN Genre g ON fg.genre_id = g.id " +
                "    GROUP BY fg.film_id " +
                "    ORDER BY genre_count DESC " +
                "    LIMIT :limitCount" +
                ") subquery ON f.id = subquery.film_id";

        Query query = entityManager.createNativeQuery(nativeQuery);
        query.setParameter("limitCount", count);

        return query.getResultList();
    }
}
