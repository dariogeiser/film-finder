package ch.bbw.filmFinder.services;

import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Service;

@Service
public class ExportFilmService {

	public void exportXml(String name, String rating) {
		try {
			System.out.println(name);
			System.out.println(rating);
			Element root = new Element("film");

			
			Document dokument = new Document(root);
		
			root.addContent(new Element("name").addContent(name));
			root.addContent(new Element("rating").addContent(rating));

			XMLOutputter outputter = new XMLOutputter();
			FileOutputStream output;

			output = new FileOutputStream("film.xml");
			outputter.output(dokument, output);
			output.close();
		} catch (Exception e) {
			System.out.println("ERROR:");
			System.out.println(e.getMessage());
		}

	}

}
