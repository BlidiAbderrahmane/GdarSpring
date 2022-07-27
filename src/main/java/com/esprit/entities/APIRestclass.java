package com.esprit.entities;

import java.util.List;

public class APIRestclass {
	
	private String controller;
	private List<APIRestmethod> APIRestmethods;
	
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
	public APIRestclass(String controller, List<APIRestmethod> aPIRestmethods) {
		super();
		this.controller = controller;
		APIRestmethods = aPIRestmethods;
	}
	public APIRestclass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
