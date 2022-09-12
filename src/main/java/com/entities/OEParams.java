package com.entities;

public class OEParams {

	private String idParamOE;
	private String nom;
	private String typeIO;
	private String typeOE;

	public String getIdParamOE() {
		return idParamOE;
	}

	public void setIdParamOE(String idParamOE) {
		this.idParamOE = idParamOE;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String gettypeOE() {
		return typeOE;
	}

	public void settypeOE(String typeOE) {
		this.typeOE = typeOE;
	}

	public String getTypeIO() {
		return typeIO;
	}

	public void setTypeIO(String typeIO) {
		this.typeIO = typeIO;
	}

	public OEParams(String idParamOE, String nom, String typeIO, String typeOE) {
		super();
		this.idParamOE = idParamOE;
		this.nom = nom;
		this.typeIO = typeIO;
		this.typeOE = typeOE;
	}

	public OEParams() {
		super();
		// TODO Auto-generated constructor stub
	}


}
