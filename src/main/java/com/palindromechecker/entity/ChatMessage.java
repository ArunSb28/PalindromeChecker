package com.palindromechecker.entity;


import org.springframework.stereotype.Component;

import lombok.Data;

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
