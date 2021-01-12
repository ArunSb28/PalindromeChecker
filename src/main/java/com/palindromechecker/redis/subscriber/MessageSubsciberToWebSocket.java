package com.palindromechecker.redis.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.palindromechecker.ws.publish.SendMessageToWebSocket;

@Service
public class MessageSubsciberToWebSocket implements MessageListener {

	@Autowired
	SendMessageToWebSocket sendMessageToWebSocket;

	Logger log = LoggerFactory.getLogger(MessageSubsciberToWebSocket.class);

	@Value("${websocket.sender}")
	private String webSocketSender;

	@Value("${websocket.topic}")
	private String webSocketTopic;

	/**
	 * Subscribes the message from Redis PubSub and sends to WebSocket client that is listening
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			if (message == null) {
				return;
			}
			String webSocketPubResponse = sendMessageToWebSocket.sendMessage(message.toString(), webSocketSender,
					webSocketTopic);

			log.info(webSocketPubResponse);
		} catch (Exception e) {
			log.error("Encountered and error{}", e.getMessage());
		}
	}

}
