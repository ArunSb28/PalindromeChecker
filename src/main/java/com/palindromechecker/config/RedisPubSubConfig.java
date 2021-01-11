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

import com.palindromechecker.redis.subscriber.MessageSubscriber;


@Configuration
public class RedisPubSubConfig {

	@Value("${redis.hostname}")
	private String hostName;
	
	@Value("${redis.port}")
	private int port;
	
	@Value("${redis.topic}")
	private String redisTopic;
	
	@Autowired
	MessageSubscriber messageSubscriber;
	
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
		return new MessageListenerAdapter(messageSubscriber);
	}

	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connFactory());
		container.addMessageListener(msgAdapter(), topic());
		return container;
	}
	
}
