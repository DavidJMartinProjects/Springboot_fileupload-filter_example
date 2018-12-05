package com.microservice.web;

import static com.microservice.web.test_utilities.Constants.$_EMPLOYED;
import static com.microservice.web.test_utilities.Constants.$_SELF_EMPLOYED;
import static com.microservice.web.test_utilities.Constants.$_UNEMPLOYED;
import static com.microservice.web.test_utilities.Constants.EMPLOYED;
import static com.microservice.web.test_utilities.Constants.FILTER_RULES;
import static com.microservice.web.test_utilities.Constants.SELF_EMPLOYED;
import static com.microservice.web.test_utilities.Constants.WELCOME_MSG;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc	
public class WebControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
		
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void verifyGetRequestToRootReturnsExpectedGreetingMessage() throws Exception {
		MvcResult response = this.mockMvc.perform(get("/")).andReturn();
		String responseBody = response.getResponse().getContentAsString();
		assertTrue(responseBody.contains(WELCOME_MSG));		
	}
	
	@Test
	public void verifyGetRequestToFilterRulesReturnsExpectedResponseFields() throws Exception {
		ResultActions actualResponse = this.mockMvc.perform(get(FILTER_RULES));
		actualResponse
			.andExpect(jsonPath($_EMPLOYED).exists())
			.andExpect(jsonPath($_UNEMPLOYED).exists())
			.andExpect(jsonPath($_SELF_EMPLOYED).exists());
	}
	
	@Test
	public void verifyGetRequestToFilterRulesReturnsExpectedResponseValues() throws Exception {
		ResultActions actualResponse = this.mockMvc.perform(get(FILTER_RULES));
		actualResponse
			.andExpect(jsonPath($_EMPLOYED, is(true)))
			.andExpect(jsonPath($_UNEMPLOYED, is(false)))
			.andExpect(jsonPath($_SELF_EMPLOYED, is(true)));
	}
	
	@Test
	public void givenValidGetRequest_ThenAssertThatExpectedFilteredPropertiesAreReturned() throws Exception {
		MvcResult response = this.mockMvc.perform(get(FILTER_RULES)).andReturn();
		String responseBody = response.getResponse().getContentAsString();
		assertTrue(responseBody.contains(EMPLOYED));
		assertTrue(responseBody.contains(SELF_EMPLOYED));		
	}

}
