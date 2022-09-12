package com.entities;

import java.util.List;

public class Ressource {
	private String idRessource;
	private String urlApi;
	private String urlRessource;
	private String verbHttp;
	private List<HttpParams> httpParams;

	public String getIdRessource() {
		return idRessource;
	}

	public void setIdRessource(String idRessource) {
		this.idRessource = idRessource;
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

	public String getVerbHttp() {
		return verbHttp;
	}

	public void setVerbHttp(String verbHttp) {
		this.verbHttp = verbHttp;
	}

	public List<HttpParams> getHttpParams() {
		return httpParams;
	}

	public void setHttpParams(List<HttpParams> httpParams) {
		this.httpParams = httpParams;
	}

	
	public Ressource(String idRessource, String urlApi, String urlRessource, String verbHttp,
			List<HttpParams> httpParams) {
		super();
		this.idRessource = idRessource;
		this.urlApi = urlApi;
		this.urlRessource = urlRessource;
		this.verbHttp = verbHttp;
		this.httpParams = httpParams;
	}

	public Ressource() {
		super();
		// TODO Auto-generated constructor stub
	}


}
