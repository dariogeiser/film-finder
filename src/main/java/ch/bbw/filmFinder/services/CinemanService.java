package ch.bbw.filmFinder.services;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;


import java.io.IOException;

import java.util.HashMap;
import java.util.List;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Service
public class CinemanService {
	private final static String URL = "https://www.kitag.com/en/program/now-playing/#?view=grid";
	private HashMap<String, List<String>> cinemasWithTime;

	public CinemanService() {
		cinemasWithTime = new HashMap<String, List<String>>();
	}

	public HashMap<String, List<String>> getCinemaWithTime(String film) {
		Document  doc;
		try {
			doc = Jsoup.connect(URL).get();

			// List<String> cinemas = new ArrayList<>();
			Element[] elements = doc.select("table.program-table").toArray(new Element[0]);
			for (Element e : elements) {
				System.out.println(e);
			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		return cinemasWithTime;
	}

}
