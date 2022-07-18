package com.esprit;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.esprit.entities.Classe;
import com.esprit.entities.Projet;
import com.esprit.entities.Projets;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@SpringBootApplication
public class JsonProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonProjectApplication.class, args);
		/*ObjectMapper mapper= new ObjectMapper();
		try {
			
			Projets lp = mapper.readValue(new File ("data/documentation.json"), Projets.class);
			List<Projet> documentation= lp.getProjets();
			for (int i = 0; i < documentation.size(); i++) {
			      System.out.println("nom : "+ documentation.get(i).getNomProjet());
			      List<Classe> l = documentation.get(i).getClasses();
			  	for (int j = 0; j < l.size(); j++) {
				     // System.out.println("    url "+l.get(j).getUrlApi());
				    // List<Ressource> rs = l.get(j).getListeRessources();
				      //for (int a = 0; a < rs.size(); a++) {
					  //    System.out.println("        methode : " +rs.get(a).getNomMethode());
					  //  }
				      
				    }
			    }
			
		
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		
	}

}
