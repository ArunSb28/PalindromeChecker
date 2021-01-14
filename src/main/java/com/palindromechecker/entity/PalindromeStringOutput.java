package com.palindromechecker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Input Object class used in /palindrome/findAll endpoint
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PalindromeStringOutput {

	private String content;
	private String timeStamp;
	private int longest_palindrome_size;

	@Override
	public String toString() {
		return "\"PalindromeString\" : {\"content\":\"" + content + "\", \"timeStamp\":\"" + timeStamp
				+ "\", \"longest_palindrome_size\" : \"" + longest_palindrome_size + "\"}";
	}

}
