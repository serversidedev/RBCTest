package com.rbc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.test.repository.AppConfig;
import com.rbc.test.repository.AppConfigID;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConfigServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	StringBuffer testContent = new StringBuffer("{\n" + 
			"   \"properties\":[\"firstProperty=1\",\"secondProperty=2\",\"ThirdProperty=3\"]\n" + 
			"}");

		
	@Test
	public void AddPropertiesFile() throws Exception
	{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/323/config/1").accept(MediaType.APPLICATION_JSON_VALUE).content(testContent.toString()).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Data as expected", "Record_Added",response);		
	}
	
	@Test
	public void getSpecificPropertiesFile() throws Exception
	{
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/323/config/1").accept(MediaType.APPLICATION_JSON_VALUE).content(testContent.toString()).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		AppConfig appConfig = mapper.readValue(response,AppConfig.class);
		AppConfig expected = new AppConfig();
		expected.setId(new AppConfigID("1","323"));
		assertEquals(expected, appConfig);
	}
	@Test
	public void ListProperties() throws Exception
	{
		
		RequestBuilder requestBuilderAdd = MockMvcRequestBuilders.post("/api/444/config/1").accept(MediaType.APPLICATION_JSON_VALUE).content(testContent.toString()).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilderAdd).andReturn();
		RequestBuilder requestBuilderAddAnother = MockMvcRequestBuilders.post("/api/444/config/2").accept(MediaType.APPLICATION_JSON_VALUE).content(testContent.toString()).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilderAddAnother).andReturn();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/444/config").accept(MediaType.APPLICATION_JSON_VALUE).content(testContent.toString()).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		AppConfig[] appConfig = mapper.readValue(response, AppConfig[].class);
		Assert.assertTrue("Multiple Records", appConfig.length>1);
	}
	

}
