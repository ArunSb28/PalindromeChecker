package com.palindromechecker.ws.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.palindromechecker.entity.ChatMessage;
import com.palindromechecker.entity.ChatMessage.MessageType;

@Component
public class SendMessageToWebSocket implements SendMessage {

	Logger log = LoggerFactory.getLogger(SendMessageToWebSocket.class);

	@Autowired
	SimpMessagingTemplate simpTemplate;
	
	@Autowired
	ChatMessage chatMessage;
	
	/**
	 *@content has the data to be shown 
	 *@sender sender is the identity of sender
	 *@topic webSocket topic to which the chatMessage is published
	 *@return the response based on publishing
	 */
	@Override
	public String sendMessage(String content, String sender, String topic) {
		
		String response = "Publishing to WebSocket Failed";
		try {

			chatMessage.setContent(content);
			chatMessage.setSender(sender);
			chatMessage.setType(MessageType.CHAT);
			this.simpTemplate.convertAndSend(topic, chatMessage);
			response = "Published Successfully to WebSocket";
			
		} catch (MessagingException e) {
			
			log.error("WebSocket Publish Failed due to {}",e.getMessage());
		}
		return response;
	}
}
