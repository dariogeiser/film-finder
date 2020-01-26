package ch.bbw.filmFinder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.bbw.filmFinder.jpa.MainFilmRepository;
import ch.bbw.filmFinder.models.MainFilm;


@Service
public class FilmHistoryService {
	
	@Autowired
	private MainFilmRepository repo;

	
	public void addMainFilm(MainFilm mainFilm){
		repo.save(mainFilm);
	}
	
	public Iterable<MainFilm> getFilms(){
		return repo.findLast10Films();
	}

	
}

