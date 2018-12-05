package com.microservice.filter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/application.yml")
@ConfigurationProperties(prefix = "employee-service.filter.resource")
public class FilterRules {
	
	private static final Logger LOG = LoggerFactory.getLogger(FilterRules.class);
	
	@PostConstruct
	public void initMsg() {
		LOG.info("Filter properties initialised with the following values: {}", properties);
	}
	
	Map<String, Boolean> properties = new HashMap<>();

	public Map<String, Boolean> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Boolean> properties) {
		this.properties = properties;
	}

}
	