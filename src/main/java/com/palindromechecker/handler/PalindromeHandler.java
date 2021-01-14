package com.palindromechecker.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.palindromechecker.dto.PalindromeDto;
import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.entity.PalindromeStringOutput;
import com.palindromechecker.redis.publisher.MessagePublisher;

@Component
public class PalindromeHandler {

	@Autowired
	private PalindromeDto palindromDto;

	@Autowired
	MessagePublisher messagePublisher;

	/**
	 * @param palindromInput received as input to be published
	 * @return the response from MessagePublisher Class
	 */
	public String publish(PalindromeInput palindromeInput) {
		return messagePublisher.publish(palindromeInput);
	}

	/**
	 * @return Returns the List of Objects retrieved from DB with enriched data
	 */
	public List<PalindromeStringOutput> getAllContent() {
		return palindromDto.findall();
	}
}
