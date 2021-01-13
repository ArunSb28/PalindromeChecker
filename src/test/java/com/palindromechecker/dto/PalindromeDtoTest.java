package com.palindromechecker.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.entity.PalindromeStringCalc;

@RunWith(MockitoJUnitRunner.class)
public class PalindromeDtoTest {

	@Mock
	RedisTemplate<String, Object> palindromeTemplate;

	@Mock
	LongestPalindrome longestPalindrome;

	@Mock
	private HashOperations<String, Object, Object> hashOperations;

	@InjectMocks
	PalindromeDto palindromeDto;

	String HASH_KEY = "Palindrome1";

	@Test
	public void testSaveWithInputs() {

		PalindromeInput palindromeInput = new PalindromeInput();
		palindromeInput.setContent("malayalam");
		palindromeInput.setTimestamp("2021-10-09 00:12:12+0100");
		MockitoAnnotations.openMocks(this);
		Mockito.when(palindromeTemplate.opsForHash()).thenReturn(hashOperations);
		Mockito.doNothing().when(hashOperations).put(HASH_KEY, palindromeInput.getContent(), palindromeInput);

		String expected = "Persisted to DB";
		String result = palindromeDto.save(palindromeInput);

		assertEquals(expected, result);

	}

	@Test
	public void testSaveWithNull() {

		PalindromeInput palindromeInput = null;
		MockitoAnnotations.openMocks(this);
		lenient().when(palindromeTemplate.opsForHash()).thenReturn(hashOperations);
		lenient().doNothing().when(hashOperations).put(HASH_KEY, "Test", palindromeInput);

		String expected = "Received Null value";
		String result = palindromeDto.save(palindromeInput);

		assertEquals(expected, result);
	}

	@Test
	public void testFindall() throws JSONException {

		List<Object> dataList = new ArrayList<Object>();
		dataList.add(new PalindromeInput());

		List<PalindromeStringCalc> palindList = new ArrayList<PalindromeStringCalc>();

		Mockito.when(palindromeTemplate.opsForHash()).thenReturn(hashOperations);
		Mockito.when(hashOperations.values(HASH_KEY)).thenReturn(dataList);

		palindList = palindromeDto.findall();
		String actualStr = "[]";
		JSONAssert.assertEquals(palindList.toString(), actualStr, false);

	}

}
