package com.esprit.entities;

import java.util.List;

public class APIRestressource {
	private String api;
	private String ressource;
	private String verbHTTP;
	
	
	
	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getRessource() {
		return ressource;
	}

	public void setRessource(String ressource) {
		this.ressource = ressource;
	}

	public String getVerbHTTP() {
		return verbHTTP;
	}

	public void setVerbHTTP(String verbHTTP) {
		this.verbHTTP = verbHTTP;
	}

	public APIRestressource(String api, String ressource, String verbHTTP) {
		super();
		this.api = api;
		this.ressource = ressource;
		this.verbHTTP = verbHTTP;
	}

	public APIRestressource() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
