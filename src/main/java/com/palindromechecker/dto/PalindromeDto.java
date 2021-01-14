package com.palindromechecker.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.entity.PalindromeStringOutput;

@Repository
public class PalindromeDto {

	@Autowired
	private RedisTemplate<String, Object> palindromeTemplate;

	public static final String HASH_KEY = "PalindromeData";

	Logger log = LoggerFactory.getLogger(PalindromeDto.class);

	@Autowired
	LongestPalindrome longestPalindrome;

	/**
	 * @param Object of PalindromeInput which has to be persisted to DB
	 * @return the success or failure message
	 */
	public String save(PalindromeInput palindromeInput) {

		if (palindromeInput != null) {
			palindromeTemplate.opsForHash().put(HASH_KEY, palindromeInput.getContent(), palindromeInput);
			return "Persisted to DB";
		}
		return "Received Null value";
	}

	/**
	 * @return the list of PalindromeStringOutput object with palindrome length
	 *         calculated
	 */
	public List<PalindromeStringOutput> findall() {

		log.info("Received Get Request to retrive data from DB");

		List<PalindromeStringOutput> palindList = new ArrayList<>();

		try {
			List<Object> dataList = palindromeTemplate.opsForHash().values(HASH_KEY);
			for (Object prd : dataList) {
				PalindromeInput palind = (PalindromeInput) prd;
				String palindromeString = longestPalindrome.longestPalindrome(palind.getContent().toUpperCase());

				PalindromeStringOutput palindromeStringOutput = new PalindromeStringOutput();

				palindromeStringOutput.setContent(palind.getContent());
				palindromeStringOutput.setTimeStamp(palind.getTimestamp());
				palindromeStringOutput.setLongest_palindrome_size(palindromeString.length());

				palindList.add(palindromeStringOutput);
			}
		} catch (Exception e) {
			log.error("Encountered error while retriving {}", e.getMessage());
		}

		log.info("Get Request Completed");
		return palindList;
	}

}
