package com.entities;

import java.util.List;

public class Method {
	
	private String idMethod;
	private String nomMethod;
	private String description;
	private String exportType;
	private List<FunctionalErrors> functionalErrors;
	private List<OEParams> oeParams;
	private List<Ressource> resources;

	public String getIdMethod() {
		return idMethod;
	}
	public void setIdMethod(String idMethod) {
		this.idMethod = idMethod;
	}
	public List<Ressource> getResources() {
		return resources;
	}
	public void setResources(List<Ressource> resources) {
		this.resources = resources;
	}
	public String getNomMethod() {
		return nomMethod;
	}
	public void setNomMethod(String nomMethod) {
		this.nomMethod = nomMethod;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public List<OEParams> getOeParams() {
		return oeParams;
	}
	public void setOeParams(List<OEParams> oeParams) {
		this.oeParams = oeParams;
	}
	public List<FunctionalErrors> getFunctionalErrors() {
		return functionalErrors;
	}
	public void setFunctionalErrors(List<FunctionalErrors> functionalErrors) {
		this.functionalErrors = functionalErrors;
	}

	public Method(String idMethod, String nomMethod, String description, String exportType, List<FunctionalErrors> functionalErrors,
			List<OEParams> oeParams,List<Ressource> resources) {
		super();
		this.idMethod = idMethod;
		this.nomMethod = nomMethod;
		this.description = description;
		this.exportType = exportType;
		this.functionalErrors = functionalErrors;
		this.oeParams = oeParams;
		this.resources = resources;
	}
	public Method() {
		super();
		// TODO Auto-generated constructor stub
	}

}
