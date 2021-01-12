package com.palindromechecker.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.entity.PalindromeStringCalc;

@Repository
public class PalindromeDto {

	@Autowired
	private RedisTemplate<String, Object> palindromeTemplate;

	public static final String HASH_KEY = "Palindrome1";

	Logger log = LoggerFactory.getLogger(PalindromeDto.class);

	@Autowired
	LongestPalindrome longestPalindrome;

	/**
	 * @param palind
	 * @return palind object re
	 */
	public String save(PalindromeInput palind) {
		palindromeTemplate.opsForHash().put(HASH_KEY, palind.getContent(), palind);
		return "Persisted to DB";
	}
	
	

	/**
	 * @return
	 */
	public List<PalindromeStringCalc> findall() {

		log.info("Received Get Request");

		List<PalindromeStringCalc> palindList = new ArrayList<>();

		try {
			List<Object> dataList = palindromeTemplate.opsForHash().values(HASH_KEY);
			for (Object prd : dataList) {
				PalindromeInput palind = (PalindromeInput) prd;
				String palindromeString = longestPalindrome.longestPalindrome(palind.getContent().toUpperCase());

				PalindromeStringCalc palindCalc = new PalindromeStringCalc();

				palindCalc.setContent(palind.getContent());
				palindCalc.setTimeStamp(palind.getTimestamp());
				palindCalc.setLongest_palindrome_size(palindromeString.length());

				palindList.add(palindCalc);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		log.info("Get Request Completed");
		return palindList;
	}

}
