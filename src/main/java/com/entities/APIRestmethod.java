package com.entities;

import java.util.List;

public class APIRestmethod {

	private String idAPIRestmethod;
	private String nomMethode;
	private String description;
	private List<APIRestressource> APIRestresources;


	public String getIdAPIRestmethod() {
		return idAPIRestmethod;
	}
	public void setIdAPIRestmethod(String idAPIRestmethod) {
		this.idAPIRestmethod = idAPIRestmethod;
	}
	public String getNomMethode() {
		return nomMethode;
	}
	public void setNomMethode(String nomMethode) {
		this.nomMethode = nomMethode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<APIRestressource> getAPIRestresources() {
		return APIRestresources;
	}
	public void setAPIRestresources(List<APIRestressource> aPIRestresources) {
		APIRestresources = aPIRestresources;
	}

	public APIRestmethod(String idAPIRestmethod, String nomMethode, String description,
			List<APIRestressource> aPIRestresources) {
		super();
		this.idAPIRestmethod = idAPIRestmethod;
		this.nomMethode = nomMethode;
		this.description = description;
		APIRestresources = aPIRestresources;
	}
	public APIRestmethod() {
		super();
		// TODO Auto-generated constructor stub
	}


}
