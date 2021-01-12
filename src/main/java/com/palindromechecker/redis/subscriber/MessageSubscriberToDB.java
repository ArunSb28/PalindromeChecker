package com.palindromechecker.redis.subscriber;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palindromechecker.dto.PalindromeDto;
import com.palindromechecker.entity.PalindromeInput;

@Service
public class MessageSubscriberToDB implements MessageListener {

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	PalindromeDto palindromeDto;

	Logger log = LoggerFactory.getLogger(MessageSubscriberToDB.class);

	/**
	 * Subscribes the message from Redis PubSub and invokes method to persists to DB
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {

		String result = "Unable to Persist to DB";
		try {
			if (message == null) {
				return;
			}
			log.info("Message Subscribed");
			PalindromeInput pi = objectMapper.readValue(message.getBody(), PalindromeInput.class);
			result = palindromeDto.save(pi);
			log.info(result);

		} catch (IOException e) {
			log.error(result,"{} due to {}", e.getMessage());
		}

	}

}
