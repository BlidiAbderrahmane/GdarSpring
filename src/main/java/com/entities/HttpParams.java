package com.entities;

public class HttpParams {
	
	private String idParamHttp;
	private String nomOE;
	private String nomHttp;
	private String typeIO;
	private String mappingHttp;
	
	public String getIdParamHttp() {
		return idParamHttp;
	}
	public void setIdParamHttp(String idParamHttp) {
		this.idParamHttp = idParamHttp;
	}
	public String getNomHttp() {
		return nomHttp;
	}
	public void setNomHttp(String nomHttp) {
		this.nomHttp = nomHttp;
	}
	public String getNomOE() {
		return nomOE;
	}
	public void setNomOE(String nomOE) {
		this.nomOE = nomOE;
	}
	public String getTypeIO() {
		return typeIO;
	}
	public void setTypeIO(String typeIO) {
		this.typeIO = typeIO;
	}
	public String getMappingHttp() {
		return mappingHttp;
	}
	public void setMappingHttp(String mappingHttp) {
		this.mappingHttp = mappingHttp;
	}
	
	public HttpParams(String idParamHttp, String nomOE, String nomHttp, String typeIO, String mappingHttp) {
		super();
		this.idParamHttp = idParamHttp;
		this.nomOE = nomOE;
		this.nomHttp = nomHttp;
		this.typeIO = typeIO;
		this.mappingHttp = mappingHttp;
	}
	
	public HttpParams() {
		super();
		// TODO Auto-generated constructor stub
	}




}
