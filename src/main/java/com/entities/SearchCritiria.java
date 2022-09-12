package com.entities;


public class SearchCritiria {
	private String projet ;
	private String controller ;
	private String methode ;
	private String description ;
	private String codeErreur  ;
	private String urlApi ;
	private String urlRessource ;
	
	public String getProjet() {
		return projet;
	}
	public void setProjet(String projet) {
		this.projet = projet;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getMethode() {
		return methode;
	}
	public void setMethode(String methode) {
		this.methode = methode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCodeErreur() {
		return codeErreur;
	}
	public void setCodeErreur(String codeErreur) {
		this.codeErreur = codeErreur;
	}
	public String getUrlApi() {
		return urlApi;
	}
	public void setUrlApi(String urlApi) {
		this.urlApi = urlApi;
	}
	public String getUrlRessource() {
		return urlRessource;
	}
	public void setUrlRessource(String urlRessource) {
		this.urlRessource = urlRessource;
	}
	public SearchCritiria(String projet, String controller, String methode, String description, String codeErreur,
			String urlApi, String urlRessource) {
		super();
		this.projet = projet;
		this.controller = controller;
		this.methode = methode;
		this.description = description;
		this.codeErreur = codeErreur;
		this.urlApi = urlApi;
		this.urlRessource = urlRessource;
	}
	public SearchCritiria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEmpty() {
		return (this.projet =="" && this.controller =="" && this.methode =="" &&  this.description =="" && this.codeErreur =="" && this.urlApi =="" &&  this.urlRessource =="");
		
	}

	
	
}
