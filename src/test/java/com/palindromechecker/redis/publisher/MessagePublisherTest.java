package com.palindromechecker.redis.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import com.palindromechecker.entity.PalindromeInput;

@RunWith(MockitoJUnitRunner.class)
public class MessagePublisherTest {

	@Mock
	private RedisTemplate<String, Object> template;

	@Mock
	private ChannelTopic topic;

	@Mock
	PalindromeInput palindromeInput;

	@InjectMocks
	MessagePublisher messagePublisher;

	@Test
	public void testPublishWithInput() {

		String actual = messagePublisher.publish(palindromeInput);
		String expected = "Processed Successfully";
		assertEquals(expected, actual);

	}

	@Test
	public void testPublishWithNullValue() {

		String actual = messagePublisher.publish(null);
		String expected = "Invalid input";
		assertEquals(expected, actual);
	}

}
