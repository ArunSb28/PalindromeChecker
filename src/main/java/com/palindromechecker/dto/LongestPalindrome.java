package com.palindromechecker.dto;

import org.springframework.stereotype.Component;

@Component
public class LongestPalindrome {

	public String longestPalindrome(String palindormeString) {
		if (palindormeString==null || palindormeString.isEmpty() || palindormeString.trim().length() < 1)
			return "";
		int start = 0;
		int end = 0;
		for (int i = 0; i < palindormeString.length(); i++) {
			int len1 = expandAroundCenter(palindormeString, i, i);
			int len2 = expandAroundCenter(palindormeString, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return hasOnlyDigits(palindormeString.substring(start, end + 1));
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

	private String hasOnlyDigits(String palidrome) {
		if ((int) palidrome.charAt(0) < 65 || (int) palidrome.charAt(0) > 90) {
			return "";
		}

		return palidrome;
	}

}
