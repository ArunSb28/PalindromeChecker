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
	SimpMessagingTemplate simpTemplate;
	
	@Autowired
	ChatMessage chatMessage;
	
	/**
	 *@param content has the data to be shown 
	 *@param sender is the identity of sender
	 *@topic webSocket topic which will be listened by client
	 *@return the response
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
		} catch (Exception e) {
			response = e.getLocalizedMessage();
		}
		return response;
	}
}
