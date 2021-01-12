package com.palindromechecker.entity;

public class PalindromeStringCalc {

	private String content;
	private String timeStamp;
	private int longest_palindrome_size;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getLongest_palindrome_size() {
		return longest_palindrome_size;
	}

	public void setLongest_palindrome_size(int longest_palindrome_size) {
		this.longest_palindrome_size = longest_palindrome_size;
	}
	
	@Override
	public String toString() {
		return "\"PalindromeString\" : {\"content\":\"" + content + "\", \"timeStamp\":\"" + timeStamp
				+ "\", \"longest_palindrome_size\" : \"" + longest_palindrome_size + "\"}";
	}

	public PalindromeStringCalc(String content, String timeStamp, int longest_palindrome_size) {
		super();
		this.content = content;
		this.timeStamp = timeStamp;
		this.longest_palindrome_size = longest_palindrome_size;
	}

	public PalindromeStringCalc() {
		super();
	}

}
