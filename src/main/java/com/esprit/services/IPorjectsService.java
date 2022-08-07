package com.esprit.services;

import java.util.List;

import com.esprit.entities.APIRest;
import com.esprit.entities.Details;
import com.esprit.entities.Projet;
import com.esprit.entities.SearchCritiria;
import com.esprit.entities.SearchDetailsCritiria;

public interface IPorjectsService {
	
	public List<Projet> getDocumentations(); 
	public APIRest searchAPIProject(SearchCritiria f);
	public APIRest searchAPIClasses(APIRest rl,List<Projet> l1, SearchCritiria f);
	public APIRest searchAPIMethod(APIRest rl,List<Projet> l1,SearchCritiria f);
	public APIRest searchAPICodeErreur(APIRest rl,List<Projet> l1, SearchCritiria f);
	public APIRest searchAPICriteresHTTP(APIRest rl,List<Projet> l1, SearchCritiria f);
	public Details searchAPIDetails(SearchDetailsCritiria f);
	
}
