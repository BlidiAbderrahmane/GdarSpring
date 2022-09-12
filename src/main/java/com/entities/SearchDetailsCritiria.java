package com.entities;

public class SearchDetailsCritiria {
	private String idProjet;
	private String idClass;
	private String idMethod;
	public String getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(String idProjet) {
		this.idProjet = idProjet;
	}
	public String getIdClass() {
		return idClass;
	}
	public void setIdClass(String idClass) {
		this.idClass = idClass;
	}
	public String getIdMethod() {
		return idMethod;
	}
	public void setIdMethod(String idMethod) {
		this.idMethod = idMethod;
	}
	public SearchDetailsCritiria(String idProjet, String idClass, String idMethod) {
		super();
		this.idProjet = idProjet;
		this.idClass = idClass;
		this.idMethod = idMethod;
	}
	public SearchDetailsCritiria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
