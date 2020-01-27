package ch.bbw.filmFinder.services;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * @author 5ia17dageiser
 *
 */
@Service
public class ImdbService {
	OkHttpClient client;

	public ImdbService() {
		this.client = new OkHttpClient();
	}

	/**
	 * @param film
	 * @return
	 */
	public String getImdbId(String film) {

		String filmId = "";
		if (film.contains(" ")) {
			String[] splited = film.split(" ");
			film = "";
			for (String val : splited) {
				film += val + "%20";
			}
			film = film.substring(0, film.length() - 3);
		}
		System.out.println(film);
		try {
			Request request = new Request.Builder()
					.url("https://movie-database-imdb-alternative.p.rapidapi.com/?page=1&r=json&s=" + film).get()
					.addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
					.addHeader("x-rapidapi-key", "76548df5a4msh7fb56fc2794b204p1d3423jsn99afe8e6de92").build();

			Response response = client.newCall(request).execute();
			JSONArray search = (JSONArray) stringToJson(response.body().string()).get("Search");
			Iterator<JSONObject> iterator = search.iterator();
			filmId = (String) iterator.next().get("imdbID");
			System.out.println(filmId);
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
		return filmId;
	}

	/**
	 * @param id
	 * @return
	 */
	public JSONObject getFilmInfos(String id) {
		JSONObject filmInfosJson = null;
		try {
			Request request = new Request.Builder()
					.url("https://movie-database-imdb-alternative.p.rapidapi.com/?i=" + id + "&r=json").get()
					.addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
					.addHeader("x-rapidapi-key", "76548df5a4msh7fb56fc2794b204p1d3423jsn99afe8e6de92").build();

			Response response = client.newCall(request).execute();
			filmInfosJson = stringToJson(response.body().string());
			System.out.println(filmInfosJson);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filmInfosJson;
	}

	/**
	 * @param value
	 * @return
	 */
	private JSONObject stringToJson(String value) {
		JSONObject json = null;
		try {
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json;
	}
}
