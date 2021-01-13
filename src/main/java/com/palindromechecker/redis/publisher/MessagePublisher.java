package com.palindromechecker.redis.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import com.palindromechecker.entity.PalindromeInput;

@Component
public class MessagePublisher {
	@Autowired
	private RedisTemplate<String, Object> template;

	@Autowired
	private ChannelTopic topic;

	Logger log = LoggerFactory.getLogger(MessagePublisher.class);

	/**
	 * @param palindrome is published to Redis Pub-Sub
	 * @return Returns the string based on success/failure of the publishing
	 */
	public String publish(PalindromeInput palindrome) {

		String response = "Publish Failed";
		try {
			if (palindrome == null ) {
				log.info("Message Publishing failed as input received was null");
				
				return "Input value is null";
			}
			
			template.convertAndSend(topic.getTopic(), palindrome.toString());
			response = "Event Published";
			log.info("Message Published to {}", topic.getTopic());
			
		} catch (Exception e) {
			log.error("Publishing failed due to {}", e.getMessage());
		}

		return response;
	}
}
