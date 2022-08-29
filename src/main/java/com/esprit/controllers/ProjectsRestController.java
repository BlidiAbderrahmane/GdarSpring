package com.esprit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.entities.APIRest;
import com.esprit.entities.Details;
import com.esprit.entities.Projet;
import com.esprit.entities.SearchCritiria;
import com.esprit.entities.SearchDetailsCritiria;
import com.esprit.services.IPorjectsService;


@RestController
public class ProjectsRestController {
	@Autowired
	IPorjectsService projServ;
	//http://localhost:8081/getAll
    @GetMapping(value = "getAll")
    @ResponseBody
	public String getAllEmployeNamesJPQL() {
		
		return "test";
	}
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/searchAllProjects")
  //@RequestMapping(value = "/searchAPIProject", method = RequestMethod.POST, consumes="application/json")
  	@ResponseBody
  	public List<Projet> searchAPIProject() {
  		return projServ.getDocumentations();
  			
  	}
    
    @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/searchAPIProject")
	//@RequestMapping(value = "/searchAPIProject", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public APIRest searchAPIProject(@RequestBody SearchCritiria f) {
		return projServ.searchAPIProject(f);
			
	}
    @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/searchAPIDetail")
	//@RequestMapping(value = "/searchAPIProject", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Details searchAPIDetails(@RequestBody SearchDetailsCritiria f) {
		return projServ.searchAPIDetails(f);
			
	}

}
