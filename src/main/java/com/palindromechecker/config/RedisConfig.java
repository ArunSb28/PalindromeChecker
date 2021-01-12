package com.palindromechecker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.palindromechecker.redis.subscriber.MessageSubsciberToWebSocket;
import com.palindromechecker.redis.subscriber.MessageSubscriberToDB;


/**
 * Redis DB and Pub-Sub config class
 */
@Configuration
public class RedisConfig {

	@Value("${redis.hostname}")
	private String hostName;
	
	@Value("${redis.port}")
	private int port;
	
	@Value("${redis.topic}")
	private String redisTopic;
	
	@Autowired
	MessageSubscriberToDB messageSubscriberToDB;
	
	@Autowired
	MessageSubsciberToWebSocket messageSubscriberToWebSocket;
	
	@Bean
	public JedisConnectionFactory connFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(hostName);
		config.setPort(port);
		return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connFactory());
		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		return template;

	}

	@Bean
	public ChannelTopic topic() {
		return new ChannelTopic(redisTopic);
	}

	@Bean
	public MessageListenerAdapter msgAdapter() {
		return new MessageListenerAdapter(messageSubscriberToDB);
	}
	
	@Bean
	public MessageListenerAdapter msgAdapterForWebSocket() {
		return new MessageListenerAdapter(messageSubscriberToWebSocket);
	}

	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connFactory());
		container.addMessageListener(msgAdapter(), topic());
		container.addMessageListener(msgAdapterForWebSocket(), topic());
		return container;
	}
	
}
