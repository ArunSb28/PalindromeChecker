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

	public String publish(PalindromeInput palindrome) {

		try {
			template.convertAndSend(topic.getTopic(), palindrome.toString());
			log.info("Message Published to {}", topic.getTopic());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "Event Published";
	}
}
