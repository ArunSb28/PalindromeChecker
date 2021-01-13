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

	/**
	 * Message enum Specifies the type of message sent to WebSocket. Currently CHAT
	 * is used to display the input published. Refer to the main.js code for better
	 * understanding.
	 *
	 */
	public enum MessageType {
		CHAT, LEAVE, JOIN
	}

}
