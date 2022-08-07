package com.esprit.entities;

import java.util.List;

public class Details {
	private String api;
	private String ressource;
	private List<OEParams> oeParams;
	private List<HttpParams> httpParams;
	private List<FunctionalErrors>functionalErrors;
	
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getRessource() {
		return ressource;
	}
	public void setRessource(String string) {
		this.ressource = string;
	}
	public List<OEParams> getOeParams() {
		return oeParams;
	}
	public void setOeParams(List<OEParams> oeParams) {
		this.oeParams = oeParams;
	}
	public List<HttpParams> getHttpParams() {
		return httpParams;
	}
	public void setHttpParams(List<HttpParams> httpParams) {
		this.httpParams = httpParams;
	}
	public List<FunctionalErrors> getFunctionalErrors() {
		return functionalErrors;
	}
	public void setFunctionalErrors(List<FunctionalErrors> functionalErrors) {
		this.functionalErrors = functionalErrors;
	}
	public Details(String api, String ressource, List<OEParams> oeParams, List<HttpParams> httpParams,
			List<FunctionalErrors> functionalErrors) {
		super();
		this.api = api;
		this.ressource = ressource;
		this.oeParams = oeParams;
		this.httpParams = httpParams;
		this.functionalErrors = functionalErrors;
	}
	public Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
