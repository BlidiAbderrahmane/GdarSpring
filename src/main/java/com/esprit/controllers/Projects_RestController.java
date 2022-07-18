package com.esprit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.services.IPorjectsService;

@RestController
public class Projects_RestController {

	//http://localhost:8081/getAll
    @GetMapping(value = "getAll")
    @ResponseBody
	public String getAllEmployeNamesJPQL() {
		
		return "yes!";
	}

}
