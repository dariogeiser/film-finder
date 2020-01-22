package ch.bbw.filmFinder;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.bbw.filmFinder.services.ImdbService;

@Controller
public class FilmFinderController {
	
	@Autowired
	ImdbService imbdSerivce = new ImdbService();
	
	@GetMapping("/index")
	public String getShowFilmInfos(Model model) throws IOException  {	
		model.addAttribute("userFilm", "");
		return "/index";
	}
	
	@RequestMapping("/index")
	public String showFilmInfos(@RequestParam(value = "userFilm", required = false ) String userFilm) throws IOException  {	
		if(userFilm != null) {
			System.out.println(userFilm);
			String id = imbdSerivce.getImdbId(userFilm);
			System.out.println(imbdSerivce.getFilmInfos(id));
		}
		return "/index";
	}

}
