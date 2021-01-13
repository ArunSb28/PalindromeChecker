package com.palindromechecker.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.palindromechecker.entity.PalindromeStringCalc;
import com.palindromechecker.handler.PalindromeHandler;

@RunWith(SpringRunner.class)
@WebMvcTest(PalindromeController.class)
class PalindromeControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	PalindromeHandler palindromeHandler;

	@Test
	void testSaveWithCorrectInputs() throws Exception {

		String requestContent = "{\"content\": \"testingMalayalam\",\"timestamp\": \"2021-10-09 00:12:12+0100\"}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/palindrome/save").content(requestContent)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "{\"success\":\"true\",\"message\":\"Processed Successfully\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	void testSaveWithTimeStampAsNull() throws Exception {

		String requestContent = "{\"content\": \"arun\"}";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/palindrome/save").content(requestContent)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "{\"success\":\"false\",\"message\":\"Invalid Request\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	void testSaveWithContentAsNull() throws Exception {

		String requestContent = "{\"timestamp\": \"2021-10-09 00:12:12+0100\"}";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/palindrome/save").content(requestContent)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "{\"success\":\"false\",\"message\":\"Invalid Request\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	void testSaveWithInputAsNull() throws Exception {

		String requestContent = "{}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/palindrome/save").content(requestContent)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "{\"success\":\"false\",\"message\":\"Invalid Request\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	void testGetAllProducts() throws Exception {

		PalindromeStringCalc palindromeStringCalc = new PalindromeStringCalc("1112111", "2018-10-09 00:12:12+0100", 0);
		List<PalindromeStringCalc> palindomeList = new ArrayList<PalindromeStringCalc>();
		palindomeList.add(palindromeStringCalc);
		Mockito.when(palindromeHandler.getAllContent()).thenReturn(palindomeList);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/palindrome/findAll").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String expected = "[{\"content\": \"1112111\",\"timeStamp\": \"2018-10-09 00:12:12+0100\",\"longest_palindrome_size\": 0 }]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
