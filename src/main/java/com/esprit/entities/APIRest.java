package com.esprit.entities;

import java.util.List;

public class APIRest {
	private List<APIRestproject> APIRestprojects;

	public List<APIRestproject> getAPIRestprojects() {
		return APIRestprojects;
	}

	public void setAPIRestprojects(List<APIRestproject> aPIRestprojects) {
		APIRestprojects = aPIRestprojects;
	}

	public APIRest(List<APIRestproject> aPIRestprojects) {
		super();
		APIRestprojects = aPIRestprojects;
	}

	public APIRest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIRest(APIRest rl) {
		APIRestprojects=rl.APIRestprojects;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "APIRest [APIRestprojects=" + APIRestprojects + ", getAPIRestprojects()=" + getAPIRestprojects()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


	
	
}
