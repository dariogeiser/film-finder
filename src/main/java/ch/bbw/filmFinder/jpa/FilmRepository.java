package ch.bbw.filmFinder.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ch.bbw.filmFinder.models.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{
	
}


