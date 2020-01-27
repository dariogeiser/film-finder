package ch.bbw.filmFinder.services;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author 5ia17dageiser
 *
 */
@Service
public class CinemanService {
	private String URL;
	private HashMap<String, String> cinemasWithLink;

	public CinemanService() {
		URL = "";
		cinemasWithLink = new HashMap<String, String>();
	}

	/**
	 * @param film
	 * @return
	 */
	public HashMap<String, String> getCinemaWithTime(String film) {
		cinemasWithLink.clear();
	
		film = film.replaceAll("\\s+", "");
		URL = "https://www.cineman.ch/en/movie/2019/" + film + "/cinema.html";
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();

			// List<String> cinemas = new ArrayList<>();
			Element[] elements = doc.select("h5 a.link").toArray(new Element[0]);
			for (Element element : elements) {
				if (element.text().equals("KITAG CINEMAS Abaton") || element.text().equals("Arthouse Movie")
						|| element.text().equals("KITAG CINEMAS Corso") || element.text().equals("Riffraff")) {
					String link = element.attr("href");
					if (!link.startsWith("http")) {
						link = "https://www.cineman.ch" + link;
					}
					cinemasWithLink.put(element.text(), link);
				}
			}
		} catch (Exception e1) {

		}
		return cinemasWithLink;
	}

}
