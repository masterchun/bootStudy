package com.sist.web.controller;

import java.security.Principal;

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
	private final SimpMessagingTemplate template;
	
	// 전체 채팅
	@MessageMapping("/chat/public")
	@SendTo("/topic/chat")
	public ChatMessage publicChat(ChatMessage msg, Principal p) {
		msg.setSender(p.getName());
		return msg;
	}
	// 1:1 채팅
	@MessageMapping("/chat/private")
	public void privateChat(ChatMessage msg, Principal p) {
		msg.setSender(p.getName());
		template.convertAndSendToUser(msg.getReceiver(), "/queue/chat", msg);
	}
	
	@GetMapping("/chat")
	public String chat_page() {
		return "chat2";
	}
}
