package com.palindromechecker.handler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.palindromechecker.dto.PalindromeDto;
import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.entity.PalindromeStringCalc;
import com.palindromechecker.redis.publisher.MessagePublisher;


@RunWith(MockitoJUnitRunner.class)
public class PalindromeHandlerTest {

	
	@Mock
	MessagePublisher messagePublisher;
	
	@Mock
	PalindromeDto palindromeDto;
	
	@InjectMocks
	PalindromeHandler palindromeHandler;
	
	@Test
	public void testPublish() {


		PalindromeInput palindromeInput = new PalindromeInput();
		
		palindromeInput.setContent("arun");
		palindromeInput.setTimestamp("2021-10-09 00:12:12+0100");

		
		Mockito.when(messagePublisher.publish(palindromeInput)).thenReturn("Published Successfully");
		
		String result = palindromeHandler.publish(palindromeInput);
		String expected = "Published Successfully";

		assertEquals(expected, result);
		
	}

	
	@Test
	public void testPublishWithNull() {

		
		Mockito.when(messagePublisher.publish(null)).thenReturn("Publishing failed due to");
		
		String result = palindromeHandler.publish(null);
		String expected = "Publishing failed due to";

		assertEquals(expected, result);
		
	}
	
	@Test
	public void testGetAllProductsWithDataInDB() {
		
		PalindromeStringCalc palindromeStringCalc = new PalindromeStringCalc();
		List<PalindromeStringCalc> value = new ArrayList<PalindromeStringCalc>();
		palindromeStringCalc.setContent("malayalam");
		palindromeStringCalc.setTimeStamp("8536976");
		palindromeStringCalc.setLongest_palindrome_size(9);
		
		value.add(palindromeStringCalc);
		
		Mockito.when(palindromeDto.findall()).thenReturn(value);
		
		List<PalindromeStringCalc> result =palindromeHandler.getAllContent();
		
		assertEquals(result.toString(), value.toString());
	}
	
	
	@Test
	public void testGetAllProductsWithNoDataInDB() {
		
		PalindromeStringCalc palindromeStringCalc = new PalindromeStringCalc();
		List<PalindromeStringCalc> value = new ArrayList<PalindromeStringCalc>();
		palindromeStringCalc.setContent("malayalam");
		palindromeStringCalc.setTimeStamp("8536976");
		palindromeStringCalc.setLongest_palindrome_size(9);
		
		value.add(palindromeStringCalc);
		
		Mockito.when(palindromeDto.findall()).thenReturn(new ArrayList<PalindromeStringCalc>());
		
		List<PalindromeStringCalc> result =palindromeHandler.getAllContent();
		
		assertEquals(result.toString(), "[]");
	}

}
