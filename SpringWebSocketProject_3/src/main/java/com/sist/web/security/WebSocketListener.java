package com.sist.web.security;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebSocketListener {
	private final WebSocketSessionRegistry wsRegistry;
	private final SimpMessagingTemplate template;
	private final UserSessionRegistry userSessionRegistry;
	
	// Stomp => javascript : Stomp (Storm), SockJS
	// User 접속
	@EventListener
	public void connection(SessionConnectEvent event) {
		StompHeaderAccessor acc = StompHeaderAccessor.wrap(event.getMessage());
		Principal p = acc.getUser();
		
		if (p == null) {
			return;
		}
		
		String userid = p.getName();
		String sessionId = acc.getSessionId();
		
		wsRegistry.register(userid, sessionId);
		userSessionRegistry.add(userid);
		// 접속시 저장
		// 접속자 명단 갱신
		template.convertAndSend("/topic/users", userSessionRegistry.getUsers());
	}
	
	@EventListener
	public void subscribe(SessionSubscribeEvent event) {
		StompHeaderAccessor acc = StompHeaderAccessor.wrap(event.getMessage());
		String destination = acc.getDestination();
		
		if ("/topic/users".equals(destination)) {
			template.convertAndSend("/topic/users", new ArrayList<>(userSessionRegistry.getUsers()));
		}
	}
	
	// User 해제
	@EventListener
	public void disconnection(SessionDisconnectEvent event) {
		StompHeaderAccessor acc = StompHeaderAccessor.wrap(event.getMessage());
		Principal p = acc.getUser();
		
		if (p == null) {
			return;
		}
		
		String userid = p.getName();
		String sessionId = acc.getSessionId();
		
		wsRegistry.unregister(userid, sessionId);
		userSessionRegistry.remove(userid);
		
		template.convertAndSend("/topic/users", userSessionRegistry.getUsers());
	}
}
