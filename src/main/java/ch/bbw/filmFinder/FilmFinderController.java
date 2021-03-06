package ch.bbw.filmFinder;

import java.io.IOException;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.bbw.filmFinder.models.Film;
import ch.bbw.filmFinder.models.MainFilm;
import ch.bbw.filmFinder.services.CinemanService;
import ch.bbw.filmFinder.services.ExportFilmService;
import ch.bbw.filmFinder.services.ImdbService;
import ch.bbw.filmFinder.services.FilmHistoryService;

/**
 * @author 5ia17dageiser
 *
 */
@Controller
public class FilmFinderController {

	@Autowired
	ImdbService imbdSerivce = new ImdbService();

	@Autowired
	CinemanService cinemanService = new CinemanService();

	@Autowired
	FilmHistoryService filmHistory = new FilmHistoryService();

	@Autowired
	ExportFilmService exportFilm = new ExportFilmService();

	/**
	 * @param name
	 * @param rating
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/index")
	public String getShowFilmInfos(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "rating", required = false) String rating, Model model) throws IOException {

		// XML
		if (name != null && rating != null) {
			exportFilm.exportXml(name, rating);
		}
		model.addAttribute("userFilm", "");
		model.addAttribute("filmData", "");
		return "/index";
	}

	@RequestMapping("/index")
	public String showFilmInfos(@RequestParam(value = "userFilm", required = false) String userFilm, Model model)
			throws IOException {

		if (userFilm != null) {
			String id = imbdSerivce.getImdbId(userFilm);
			if (id == null) {
				model.addAttribute("showError", "Please enter a valid Film");
			} else {
				// JSON
				model.addAttribute("userFilm", userFilm);
				model.addAttribute("showError", "");
				JSONObject fimJsonObj = imbdSerivce.getFilmInfos(id);
				model.addAttribute("fimJsonObj", fimJsonObj);

				// HTML
				HashMap<String, String> cinemas = cinemanService.getCinemaWithTime(userFilm);
				model.addAttribute("cinemas", cinemas);

				// MySQL
				Film film = new Film((String) fimJsonObj.get("Title"), (String) fimJsonObj.get("imdbRating"));
				MainFilm mainFilmTable = new MainFilm();
				mainFilmTable.setFilm(film);
				film.setMainFilm(mainFilmTable);
				filmHistory.addMainFilm(mainFilmTable);

				Iterable<MainFilm> films = filmHistory.getFilms();
				model.addAttribute("filmsFromDb", films);

			}
		}

		return "/index";
	}

}
