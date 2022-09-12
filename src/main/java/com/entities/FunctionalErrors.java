package com.entities;

public class FunctionalErrors {

	private String idError;
	private String value;
	private String code; 
	
	public String getIdError() {
		return idError;
	}

	public void setIdError(String idError) {
		this.idError = idError;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FunctionalErrors(String idError, String value, String code) {
		super();
		this.idError = idError;
		this.value = value;
		this.code = code;
	}
	
	public FunctionalErrors() {
		super();
		// TODO Auto-generated constructor stub
	}

}
