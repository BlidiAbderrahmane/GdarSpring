package com.entities;

import java.util.List;

public class APIRestclass {
	
	private String idAPIRestclass;
	private String controller;
	private List<APIRestmethod> APIRestmethods;
	
	public String getIdAPIRestclass() {
		return idAPIRestclass;
	}
	public void setIdAPIRestclass(String idAPIRestclass) {
		this.idAPIRestclass = idAPIRestclass;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public List<APIRestmethod> getAPIRestmethods() {
		return APIRestmethods;
	}
	public void setAPIRestmethods(List<APIRestmethod> aPIRestmethods) {
		APIRestmethods = aPIRestmethods;
	}
	
	public APIRestclass(String idAPIRestclass, String controller, List<APIRestmethod> aPIRestmethods) {
		super();
		this.idAPIRestclass = idAPIRestclass;
		this.controller = controller;
		APIRestmethods = aPIRestmethods;
	}
	public APIRestclass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
