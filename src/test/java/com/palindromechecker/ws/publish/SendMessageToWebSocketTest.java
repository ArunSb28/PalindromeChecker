package com.palindromechecker.ws.publish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.palindromechecker.entity.ChatMessage;
import com.palindromechecker.entity.ChatMessage.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class SendMessageToWebSocketTest {

	@Mock
	SimpMessagingTemplate simpTemplate;

	@Mock
	ChatMessage chatMessage;

	@InjectMocks
	SendMessageToWebSocket sendMessagetoWebSocket;

	@Test
	public void testSendMessage() {
		String response = "Published Successfully to WebSocket";
		chatMessage.setContent("Hello");
		chatMessage.setSender("Arun");
		chatMessage.setType(MessageType.CHAT);

		doNothing().when(simpTemplate).convertAndSend("/topic/publicPalindrome", chatMessage);

		String expected = sendMessagetoWebSocket.sendMessage("Hello", "Arun", "/topic/publicPalindrome");
		assertEquals(expected, response);

	}
	

	@Test
	public void testSendMessageWithNull() {
		String response = "Publishing to WebSocket Failed";

		doThrow(new MessagingException("Exception")).when(simpTemplate).convertAndSend("/topic/publicPalindrome", chatMessage);

		String expected = sendMessagetoWebSocket.sendMessage("Hello", "Arun", "/topic/publicPalindrome");
		assertEquals(expected, response);

	}
	
}
