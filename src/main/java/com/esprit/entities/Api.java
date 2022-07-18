package com.esprit.entities;

import java.util.List;

public class Api {
	private String urlApi;
	List<Ressource> listeRessources;

	public Api() {
		super();
	}

	public String getUrlApi() {
		return urlApi;
	}

	public void setUrlApi(String urlApi) {
		this.urlApi = urlApi;
	}

	public List<Ressource> getListeRessources() {
		return listeRessources;
	}

	public void setListeRessources(List<Ressource> listeRessources) {
		this.listeRessources = listeRessources;
	}

	public Api(String urlApi, List<Ressource> listeRessources) {
		super();
		this.urlApi = urlApi;
		this.listeRessources = listeRessources;
	}

}
