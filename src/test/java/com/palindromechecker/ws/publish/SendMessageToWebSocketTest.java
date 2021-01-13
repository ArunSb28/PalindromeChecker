package com.palindromechecker.ws.publish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.palindromechecker.entity.ChatMessage;
import com.palindromechecker.entity.ChatMessage.MessageType;

@RunWith(MockitoJUnitRunner.class)
class SendMessageToWebSocketTest {

	@Mock
	SimpMessagingTemplate simpTemplate;

//	@Mock
//	ChatMessage chatMessage;

	@Mock
	MessageType messageType;

	@InjectMocks
	SendMessageToWebSocket sendMessagetoWebSocket;

	void testSendMessage() {
		String response = "Processed Successfully";
//		chatMessage.setContent("Hello");
//		chatMessage.setSender("Arun");
//		chatMessage.setType(MessageType.CHAT);

		ChatMessage chatMessage = new ChatMessage();

		doNothing().when(simpTemplate).convertAndSend("/topic/publicPalindrome", chatMessage);

		String expected = sendMessagetoWebSocket.sendMessage("Hello", "Arun", "/topic/publicPalindrome");
		assertEquals(expected, response);

	}

}
