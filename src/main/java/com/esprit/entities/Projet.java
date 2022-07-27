package com.esprit.entities;

import java.util.List;

public class Projet {
	
	
	private String idProject;
	private String nomProjet;
	private String version;
	private List<Classe> classes;
	
	public String getIdProject() {
		return idProject;
	}
	public void setIdProject(String idProject) {
		this.idProject = idProject;
	}
	public String getNomProjet() {
		return nomProjet;
	}
	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<Classe> getClasses() {
		return classes;
	}
	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public Projet(String idProject, String nomProjet, String version, List<Classe> classes) {
		super();
		this.idProject = idProject;
		this.nomProjet = nomProjet;
		this.version = version;
		this.classes = classes;
	}
	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
