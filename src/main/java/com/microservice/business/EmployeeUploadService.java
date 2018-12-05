package com.microservice.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.business.api.EmployeeService;
import com.microservice.business.api.FileService;
import com.microservice.domain.Employee;
import com.microservice.domain.EmploymentStatus;
import com.microservice.domain.Nationality;
import com.microservice.filter.Filter;

@Component
public class EmployeeUploadService implements FileService, EmployeeService {
	
	@Autowired
	Filter filter;
	
	public List<Employee> convertCsvToFilteredEmployeeList(MultipartFile multipartFile) {
		File file = convertMultipartToFile(multipartFile);
		List<String> EmpsAsListOfStrings = convertFileToList(file);
		List<Employee> employeeList = convertListToEmployees(EmpsAsListOfStrings);
		List<Employee> filteredEmployeeList = filter.applyFilter(employeeList);
		return filteredEmployeeList;		
	}

	@Override
	public File convertMultipartToFile(MultipartFile multipartFile) {
		File convertedFile = new File(multipartFile.getOriginalFilename());
		try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);) {
			convertedFile.createNewFile();
			fileOutputStream.write(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedFile;
	}

	@Override
	public List<String> convertFileToList(File file) {
		ArrayList<String> list = new ArrayList<String>();
		try(Scanner scanner = new Scanner(file);) {
			while (scanner.hasNext()){
			    list.add(scanner.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Employee> convertListToEmployees(List<String> empList) {
		return empList.stream()
			.map(e -> convertStringToEmployee(e))
			.collect(Collectors.toList());
	}

	@Override
	public Employee convertStringToEmployee(String empString) {
		Employee tempEmployee = new Employee();
		String[] empArray = empString.split(",");		
		tempEmployee.setId(empArray[0]);
		tempEmployee.setName(empArray[1]);		
		tempEmployee.setAddress(empArray[2]);
		tempEmployee.setNationality(Nationality.valueOf(empArray[3]));
		tempEmployee.setEmploymentStatus(EmploymentStatus.valueOf(empArray[4]));
		return tempEmployee;
	}
	
}
