package ch.bbw.filmFinder.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.bbw.filmFinder.models.Film;

/**
 * @author 5ia17dageiser
 *
 */
public interface FilmRepository extends JpaRepository<Film, Long>{
	
}


