package ch.bbw.filmFinder;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.bbw.filmFinder.services.CinemanService;
import ch.bbw.filmFinder.services.ImdbService;

@Controller
public class FilmFinderController {
	
	@Autowired
	ImdbService imbdSerivce = new ImdbService();
	
	@Autowired
	CinemanService cinemanService = new CinemanService();
	
	@GetMapping("/index")
	public String getShowFilmInfos(Model model) throws IOException  {	
		model.addAttribute("userFilm", "");
		return "/index";
	}
	
	@RequestMapping("/index")
	public String showFilmInfos(@RequestParam(value = "userFilm", required = false ) String userFilm, Model model) throws IOException  {	
		if(userFilm != null) {
			String id = imbdSerivce.getImdbId(userFilm);
			if(id == null) {
				model.addAttribute("showError", "Please enter a valid Film");
			} else {
				System.out.println(1);
				model.addAttribute("showError", "");
				JSONObject fimJsonObj = imbdSerivce.getFilmInfos(id);
				model.addAttribute("fimJsonObj", fimJsonObj);
				cinemanService.getCinemaWithTime((String) fimJsonObj.get("title"));
			}
		}
		return "/index";
	}

}
