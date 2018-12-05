package com.microservice.filter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.microservice.business.api.FilterService;
import com.microservice.domain.Employee;

@Component
public class Filter implements FilterService {

	@Autowired
	FilterRules filterRules;
	
	private static final Logger LOG = LoggerFactory.getLogger(Filter.class);
	
	@Override
	public List<Employee> applyFilter(List<Employee> unfilteredEmpList) {
		LOG.info("Filter.applyFilter() called with list : {}", unfilteredEmpList);
		return unfilteredEmpList.stream()
			.filter(employee -> isVisible(employee))
			.collect(Collectors.toList());
	}

	@Override
	public boolean isVisible(Employee employee) {		
		boolean isVisible = true;
		if (!StringUtils.isEmpty(employee.getEmploymentStatus())) {
			if (filterRules != null) {
				if (filterRules.getProperties().containsKey(String.valueOf(employee.getEmploymentStatus()))) {
					isVisible = filterRules.getProperties().get(employee.getEmploymentStatus().toString());
				}
			} 
		}
		return isVisible;
	}	

	@Override
	public Map<String, Boolean> getFilterRules() {		
		return filterRules.getProperties();
	}

}
