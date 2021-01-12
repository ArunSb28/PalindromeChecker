package com.palindromechecker.entity;


import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Used as part of webSocket as payload
 *
 */
@Data
@Component
public class ChatMessage {

	private String sender;
	private String content;
	private MessageType type;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}


}
