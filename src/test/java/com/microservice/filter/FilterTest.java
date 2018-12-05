package com.microservice.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservice.domain.Employee;
import com.microservice.domain.EmploymentStatus;
import com.microservice.domain.Nationality;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilterTest {
	
	@Autowired
	Filter filter;
	
	Map<String, Boolean> expectedProperties = new HashMap<>();
	List<Employee> unfilteredEmpList = new ArrayList<>();
	Employee emp1;
	Employee emp2;
	Employee emp3;
	
	@Before
	public void setUp() {
		emp1 = new Employee("E100", "John Doe", "Galway City", Nationality.EU, EmploymentStatus.UNEMPLOYED);
		emp2 = new Employee("E200", "Jane Doe", "Athlone", Nationality.EU, EmploymentStatus.EMPLOYED);
		emp3 = new Employee("E300", "James Joyce", "Dublin", Nationality.EU, EmploymentStatus.SELF_EMPLOYED);
		unfilteredEmpList.add(emp1);
		unfilteredEmpList.add(emp2);
		unfilteredEmpList.add(emp3);
	}

	@Test
	public void verifyApplyFilterIsFilteringAsExpected() {
		List<Employee> filteredEmployees = filter.applyFilter(unfilteredEmpList);
		assertEquals(2, filteredEmployees.size());
	}
	
	@Test
	public void verifyIsVisibleIsFilteringUnemployedPeopleAsExpected() throws Exception {
		boolean expectedResult = false;
		assertEquals(expectedResult, filter.isVisible(emp1));
	}
	
	@Test
	public void verifyGetFilterRulesReturnedTheExpectedCollection() throws Exception {
		initialiseExpectedProperties();
		assertEquals(expectedProperties, filter.getFilterRules());
	}

	private void initialiseExpectedProperties() {
		expectedProperties.put(EmploymentStatus.EMPLOYED.toString(), true); 
		expectedProperties.put(EmploymentStatus.UNEMPLOYED.toString(), false); 
		expectedProperties.put(EmploymentStatus.SELF_EMPLOYED.toString(), true);
	}

}
