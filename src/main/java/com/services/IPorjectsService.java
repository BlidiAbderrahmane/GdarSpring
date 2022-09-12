package com.services;

import java.util.List;

import com.entities.APIRest;
import com.entities.Details;
import com.entities.Projet;
import com.entities.SearchCritiria;
import com.entities.SearchDetailsCritiria;

public interface IPorjectsService {
	
	public List<Projet> getDocumentations(); 
	public APIRest searchAPIProject(SearchCritiria f);
	public APIRest searchAPIClasses(APIRest rl,List<Projet> l1, SearchCritiria f);
	public APIRest searchAPIMethod(APIRest rl,List<Projet> l1,SearchCritiria f);
	public APIRest searchAPICodeErreur(APIRest rl,List<Projet> l1, SearchCritiria f);
	public APIRest searchAPICriteresHTTP(APIRest rl,List<Projet> l1, SearchCritiria f);
	public Details searchAPIDetails(SearchDetailsCritiria f);
	
}
