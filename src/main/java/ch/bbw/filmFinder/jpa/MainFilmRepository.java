package ch.bbw.filmFinder.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ch.bbw.filmFinder.models.Film;
import ch.bbw.filmFinder.models.MainFilm;



public interface MainFilmRepository extends JpaRepository<MainFilm, Long>{
	
	@Query(value = "SELECT film_mainlist.id,film_mainlist.film_id,film.name, film.rating FROM film_mainlist INNER JOIN film ON film_mainlist.film_id=film.id ORDER BY film_mainlist.id DESC LIMIT 10;", nativeQuery = true)
	Iterable<MainFilm> findLast10Films();
}
		