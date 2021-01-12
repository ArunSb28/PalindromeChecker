package com.palindromechecker.ws.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.palindromechecker.entity.ChatMessage;
import com.palindromechecker.entity.ChatMessage.MessageType;

@Component
public class SendMessageToWebSocket implements SendMessage {

	Logger log = LoggerFactory.getLogger(SendMessageToWebSocket.class);

	@Autowired
	private SimpMessagingTemplate simpTemplate;
	
	@Autowired
	ChatMessage chatMessage;
	
	@Override
	public void sendMessage(String content, String sender, String topic) {
		try {

			chatMessage.setContent(content);
			chatMessage.setSender(sender);
			chatMessage.setType(MessageType.CHAT);
			this.simpTemplate.convertAndSend(topic, chatMessage);
		} catch (Exception e) {

		}

	}
}
