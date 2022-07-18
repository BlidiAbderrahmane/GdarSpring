package com.esprit.entities;

import java.util.List;

public class Classe {
	
	private String idClass;
	private String nomClass;
	private String exportType;
	private List<Method> methods;
	
	public String getIdClass() {
		return idClass;
	}
	public void setIdClass(String idClass) {
		this.idClass = idClass;
	}
	public String getNomClass() {
		return nomClass;
	}
	public void setNomClass(String nomClass) {
		this.nomClass = nomClass;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public Classe(String idClass, String nomClass, String exportType, List<Method> methods) {
		super();
		this.idClass = idClass;
		this.nomClass = nomClass;
		this.exportType = exportType;
		this.methods = methods;
	}

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
