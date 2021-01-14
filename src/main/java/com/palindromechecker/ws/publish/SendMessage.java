package com.palindromechecker.ws.publish;

/**
 *  Interface containing method to publish to WebSocket
 *
 */
public interface SendMessage {

	public String sendMessage(String content, String sender, String topic);
	
}
