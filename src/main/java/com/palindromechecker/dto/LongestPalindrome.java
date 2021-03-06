package com.palindromechecker.dto;

import org.springframework.stereotype.Component;

@Component
public class LongestPalindrome {

	/**
	 * @param palindromeString in which longest palindrome is to be identified. Numbers and
	 *        special characters will reset palindrome substring
	 * @return the longest palindrome substirng
	 */
	public String longestPalindrome(String palindromeString) {
		if (palindromeString == null || palindromeString.isEmpty() || palindromeString.trim().length() < 1)
			return "";
		int start = 0;
		int end = 0;
		for (int i = 0; i < palindromeString.length(); i++) {
			int len1 = expandAroundCenter(palindromeString, i, i);
			int len2 = expandAroundCenter(palindromeString, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return digitOrSpecialCharcterCheck(palindromeString.substring(start, end + 1));
	}

	private int expandAroundCenter(String inputString, int left, int right) {
		int leftIndex = left;
		int rightIndex = right;
		while (leftIndex >= 0 && rightIndex < inputString.length()
				&& inputString.charAt(leftIndex) == inputString.charAt(rightIndex)
				&& ((int) inputString.charAt(leftIndex) >= 65) && ((int) inputString.charAt(leftIndex) <= 90)
				&& ((int) inputString.charAt(rightIndex) >= 65) && ((int) inputString.charAt(rightIndex) <= 90)) {
			leftIndex--;
			rightIndex++;
		}
		return rightIndex - leftIndex - 1;
	}

	private String digitOrSpecialCharcterCheck(String palidrome) {
		if ((int) palidrome.charAt(0) < 65 || (int) palidrome.charAt(0) > 90) {
			return "";
		}

		return palidrome;
	}

}
