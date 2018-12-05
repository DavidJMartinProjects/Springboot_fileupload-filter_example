package com.microservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.business.EmployeeUploadService;
import com.microservice.domain.Employee;
import com.microservice.filter.FilterRules;

@RestController
public class WebController {
	
	private static final String WELCOME_MSG = "Welcome to Employee Onboarding Service.";

	@Autowired
	EmployeeUploadService employeeUploadService;
	
	@Autowired
	FilterRules filterRules;	
	
	@GetMapping("/")
	public String greeting() {
		return WELCOME_MSG;
	}
	
	@RequestMapping(value = "/filter-rules", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getfilterRules() {
		return new ResponseEntity<>(filterRules.getProperties(), HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = { "multipart/form-data" }, produces = { "application/json" })
	public ResponseEntity<Object> createEmployees(@RequestParam("file") final MultipartFile file) {		
		List<Employee> empList = employeeUploadService.convertCsvToFilteredEmployeeList(file);
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}
	
}
