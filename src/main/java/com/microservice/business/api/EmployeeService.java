package com.microservice.business.api;

import java.util.List;

import com.microservice.domain.Employee;

public interface EmployeeService {
	
	List<Employee> convertListToEmployees(List<String> list);
	Employee convertStringToEmployee(String emp);
	
}
