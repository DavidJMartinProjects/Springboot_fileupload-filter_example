package com.microservice.business.api;

import java.util.List;
import java.util.Map;

import com.microservice.domain.Employee;

public interface FilterService {

	List<Employee> applyFilter(List<Employee> unfilteredEmpList);
	boolean isVisible(Employee employee);
	Map<String, Boolean> getFilterRules();
	
}
