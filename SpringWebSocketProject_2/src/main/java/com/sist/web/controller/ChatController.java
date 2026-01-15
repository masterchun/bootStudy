package com.sist.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.vo.ChatMessage;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	private final SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(ChatMessage message) {
		return message;
	}

	@MessageMapping("/chat.private")
	public void privateMessage(ChatMessage message) {
		messagingTemplate.convertAndSend("/queue/private/" + message.getReceiver(), message);
	}

	@GetMapping("/chat")
	public String chat_page() {
		return "chat";
	}
}
