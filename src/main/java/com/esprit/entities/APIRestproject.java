package com.esprit.entities;

import java.util.List;

public class APIRestproject {

	private String projet;
	private String version;
	private List<APIRestclass> APIRestclasses;
	
	
	
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

	public APIRestproject(String projet, String version, List<APIRestclass> aPIRestclasses) {
		super();
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
		return "APIRestproject [projet=" + projet + ", version=" + version + ", APIRestclasses=" + APIRestclasses + "]";
	}


	
}
