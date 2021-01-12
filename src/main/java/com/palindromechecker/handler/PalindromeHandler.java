package com.palindromechecker.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palindromechecker.dto.PalindromeDto;
import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.entity.PalindromeStringCalc;
import com.palindromechecker.redis.publisher.MessagePublisher;

@Service
public class PalindromeHandler {

	@Autowired
	private PalindromeDto palindromDto;
	
	@Autowired
	MessagePublisher messagePublisher;

	public String save(PalindromeInput palindromeInput) {
		return messagePublisher.publish(palindromeInput);
	}

	public List<PalindromeStringCalc> getAllProducts() {
		return palindromDto.findall();
	}
}
