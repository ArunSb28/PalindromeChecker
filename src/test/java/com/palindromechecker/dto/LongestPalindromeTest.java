package com.palindromechecker.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LongestPalindromeTest {

	LongestPalindrome longestPalindrome = new LongestPalindrome();
	
	@Test
	void testLongestPalindromeWithProperInput() {
		
		int length = 9;
		String palindrome = longestPalindrome.longestPalindrome("malayalam".toUpperCase());
		assertEquals(palindrome.length(), length);
	}

	@Test
	void testLongestPalindromeWithAlpabetsSpecailCharactersNumbers() {
		
		int length = 3;
		String palindrome = longestPalindrome.longestPalindrome("mala$y4ala36m".toUpperCase());
		assertEquals(palindrome.length(), length);
	}

	
	@Test
	void testLongestPalindromeWithAllSpecialCharacter() {
		
		int length = 0;
		String palindrome = longestPalindrome.longestPalindrome("@@@####$$$$%%%%".toUpperCase());
		assertEquals(palindrome.length(), length);

	}
	
	@Test
	void testLongestPalindromeWithAllNumbers() {
		
		int length = 0;
		String palindrome = longestPalindrome.longestPalindrome("58537916806867".toUpperCase());
		assertEquals(palindrome.length(), length);
	}
	
	
	@Test
	void testLongestPalindromeWithEmptyString() {
		
		int length = 0;
		String palindrome = longestPalindrome.longestPalindrome("".toUpperCase());
		assertEquals(palindrome.length(), length);
	}

	
	@Test
	void testLongestPalindromeWithNULL() {
		
		int length = 0;
		String palindrome = longestPalindrome.longestPalindrome(null);
		assertEquals(palindrome.length(), length);
	}
}
