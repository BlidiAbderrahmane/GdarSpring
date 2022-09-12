package com.entities;

import java.util.List;

public class APIRestproject {

	private String idAPIRestproject;
	private String projet;
	private String version;
	private List<APIRestclass> APIRestclasses;


	public String getIdAPIRestproject() {
		return idAPIRestproject;
	}

	public void setIdAPIRestproject(String idAPIRestproject) {
		this.idAPIRestproject = idAPIRestproject;
	}
	
	public String getProjet() {
		return projet;
	}

	public void setProjet(String projet) {
		this.projet = projet;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<APIRestclass> getAPIRestclasses() {
		return APIRestclasses;
	}

	public void setAPIRestclasses(List<APIRestclass> aPIRestclasses) {
		APIRestclasses = aPIRestclasses;
	}

	

	public APIRestproject(String idAPIRestproject, String projet, String version, List<APIRestclass> aPIRestclasses) {
		super();
		this.idAPIRestproject = idAPIRestproject;
		this.projet = projet;
		this.version = version;
		APIRestclasses = aPIRestclasses;
	}

	public APIRestproject() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "APIRestproject [idAPIRestproject=" + idAPIRestproject + ", projet=" + projet + ", version=" + version
				+ ", APIRestclasses=" + APIRestclasses + ", getIdAPIRestproject()=" + getIdAPIRestproject()
				+ ", getProjet()=" + getProjet() + ", getVersion()=" + getVersion() + ", getAPIRestclasses()="
				+ getAPIRestclasses() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	



}
