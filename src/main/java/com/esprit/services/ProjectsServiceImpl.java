
package com.esprit.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esprit.entities.APIRest;
import com.esprit.entities.APIRestclass;
import com.esprit.entities.APIRestproject;
import com.esprit.entities.Classe;
import com.esprit.entities.Projet;
import com.esprit.entities.Projets;
import com.esprit.entities.SearchCritiria;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProjectsServiceImpl implements IPorjectsService{

	@Override
	public List<Projet> getDocumentations() {
		List<Projet> documentation= new ArrayList<Projet>() ;

		ObjectMapper mapper= new ObjectMapper();
		try {

			Projets lp = mapper.readValue(new File ("data/documentation.json"), Projets.class);
			documentation= lp.getProjets();

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
		return documentation;

	}

	@Override
	public APIRest searchAPIProject(SearchCritiria f) {

		List<Projet> l1 = getDocumentations();
		if(f.isEmpty()) {
			System.out.println("les critères de recherche sont vides");
			return null;
		}
		else {
			APIRest rapi = new APIRest();
			List<APIRestproject> pl = new ArrayList<APIRestproject>();
			if(f.getProjet()!="") {
				for(int i=0 ; i< l1.size(); i++) {
					if(f.getProjet().equals( l1.get(i).getNomProjet() )) {
						APIRestproject rproject= new APIRestproject();
						rproject.setProjet(l1.get(i).getNomProjet());
						rproject.setVersion(l1.get(i).getVersion());
						pl.add(rproject);
					}
				}
				rapi.setAPIRestprojects(pl);

			}
			else {
				System.out.println("ALL");			
				for(int i=0 ; i< l1.size(); i++) {
					APIRestproject rproject= new APIRestproject();
					rproject.setProjet(l1.get(i).getNomProjet());
					rproject.setVersion(l1.get(i).getVersion());
					pl.add(rproject);
				}
				rapi.setAPIRestprojects(pl);
			}
			return searchAPIClasses(rapi,l1,f) ; 
		}

	}

	@Override
	public APIRest searchAPIClasses( APIRest rl, List<Projet> l1, SearchCritiria f) {
		if(f.getController()=="") {
			for(int i=0;i<rl.getAPIRestprojects().size();i++) {
				List<APIRestclass> rcs = new ArrayList<APIRestclass>() ;
				for(int j=0;j<l1.size();j++) {
					if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
						for (int k=0;k<l1.get(j).getClasses().size();k++) {
							APIRestclass rc = new APIRestclass();
							rc.setController(l1.get(j).getClasses().get(k).getNomClass());
							rcs.add(rc);
						}
					}
				}
				rl.getAPIRestprojects().get(i).setAPIRestclasses(rcs);
			}
		}
		else {
			 APIRest rl2=new APIRest();
			 rl2.setAPIRestprojects(rl.getAPIRestprojects());
			 //rl2=rl;
			 int varsupp=0;
			for(int i=0;i<rl2.getAPIRestprojects().size();i++) {
				System.out.println("mlolwl " + rl2.getAPIRestprojects().get(i).getProjet());
				List<APIRestclass> rcs = new ArrayList<APIRestclass>() ;
				boolean Rlfounded =false;
				for(int j=0;j<l1.size();j++) {
					
					
					if(l1.get(j).getNomProjet().equals( rl2.getAPIRestprojects().get(i).getProjet())) {
						System.out.println(i);
						System.out.println("mou9 " + l1.get(j).getNomProjet() + rl2.getAPIRestprojects().get(i).getProjet());
						boolean found =false;
						int k=0;
						while(k<l1.get(j).getClasses().size()) {
							if(f.getController().equals( l1.get(j).getClasses().get(k).getNomClass() )) {
								Rlfounded = true;
								found=true;
								APIRestclass rc = new APIRestclass();
								rc.setController(l1.get(j).getClasses().get(k).getNomClass());
								rcs.add(rc);
							}
							k++;
						}

						if(!found) {
						
							System.out.println(i +" var " + varsupp);
							System.out.println("rl size " + rl.getAPIRestprojects().size());
							System.out.println("rl2 size " + rl2.getAPIRestprojects().size());
							rl.getAPIRestprojects().remove(i-varsupp);
							
							System.out.println("ppppp "+i +" var " + varsupp);
							
							varsupp++;
							
						}

					}
				}

				if(Rlfounded) {
					//System.out.println(i +" var " + varsupp);
			//rl.getAPIRestprojects().get(i-varsupp).setAPIRestclasses(rcs);
				}

			}
		}

		return rl;

	}

	/*@Override
	public List<APIRest> searchAPIMethod(APIRest l1, List<Projet> Lp, SearchCritiria f) {
		// TODO Auto-generated method stub
		return null;
	} 

	@Override
	public List<APIRest> searchAPICodeErreur(List<Projet> Lp, SearchCritiria f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<APIRest> searchAPICriteresHTTP(List<Projet> Lp, SearchCritiria f) {
		// TODO Auto-generated method stub
		return null;
	}

	 */	

}