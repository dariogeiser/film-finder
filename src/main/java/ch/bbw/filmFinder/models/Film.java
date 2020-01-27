package ch.bbw.filmFinder.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author 5ia17dageiser
 *
 */
@Entity
@Table(name = "film")
public class Film {

	@Id
	@GeneratedValue
	private int id;

	private String name;
	private String rating;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "film")
	private MainFilm mainFilm;

	public Film() {

	}

	public Film(String name, String rating) {
		this.name = name;
		this.rating = rating;
	}

	public MainFilm getMainFilm() {
		return mainFilm;
	}

	public void setMainFilm(MainFilm mainFilm) {
		this.mainFilm = mainFilm;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRating() {
		return rating;
	}

}
