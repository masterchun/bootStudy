package com.sist.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 서버 연결 => URL 설정
		registry.addEndpoint("/ws-chat") // ws://
			.setAllowedOriginPatterns("*")
			.withSockJS(); // javascript : stomp / sockjs
		// new SockJS('/ws-chat');
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 기능에 해당되는 => URL 설정
		registry.enableSimpleBroker("/topic", "/queue");
		registry.setApplicationDestinationPrefixes("/app");
		registry.setUserDestinationPrefix("/user");
	}

}
