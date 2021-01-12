package com.palindromechecker.redis.subscriber;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.palindromechecker.dto.PalindromeDto;
import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.ws.publish.SendMessageToWebSocket;

@Service
public class MessageSubscriber implements MessageListener {

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	PalindromeDto palindromeDto;

	@Autowired
	SendMessageToWebSocket sendMessageToWebSocket;

	Logger log = LoggerFactory.getLogger(MessageSubscriber.class);

	@Value("${websocket.sender}")
	private String webSocketSender;

	@Value("${websocket.topic}")
	private String webSocketTopic;

	@Override
	public void onMessage(Message message, byte[] pattern) {

		try {

			if (message == null) {
				return;
			}

			log.info("Message Subscribed");

			PalindromeInput pi = objectMapper.readValue(message.getBody(), PalindromeInput.class);
			palindromeDto.save(pi);

			log.info("Data Saved to DB");

			sendMessageToWebSocket.sendMessage(message.toString(), webSocketSender, webSocketTopic);

			log.info("Message Published to webSocket");

		} catch (IOException e) {
			log.error("Not a valid JSON {}", e.getMessage());
		}

	}

}
