package ch.bbw.filmFinder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bbw.filmFinder.jpa.MainFilmRepository;
import ch.bbw.filmFinder.models.MainFilm;

/**
 * @author 5ia17dageiser
 *
 */
@Service
public class FilmHistoryService {

	@Autowired
	private MainFilmRepository repo;

	/**
	 * @param mainFilm
	 */
	public void addMainFilm(MainFilm mainFilm) {
		repo.save(mainFilm);
	}

	/**
	 * @return
	 */
	public Iterable<MainFilm> getFilms() {
		return repo.findLast10Films();
	}

}
