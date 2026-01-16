package com.sist.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * 		스프링에서 관리 클래스 : DI (객체 생성, 객체 소멸)
 * 							클래스와 클래스의 연관 관계
 * 										 ------
 * 							주입 => setter / constructor
 * 		=> 스테레오 타입
 * 		@Configuration :XML => java로 환경 설정
 * 		@Controller
 * 		@RestController ==> 브라우저 연동
 * 		@Component => 일반 클래스
 * 		@Repository => DAO
 * 		@Service => BI
 * 		@RestControllerAdvice => 공통 예외 처리
 * 		@ControllerAdvice
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-ws").setAllowedOriginPatterns("*").withSockJS();
	}

	// Stomp의 메세지 규칙
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/queue");
		// /topic : 전체, /queue : 개인
		/*
		 * 	convertAndSend("/topic/chat", msg) => 전체 처리
		 * 	convertAndSend("/queue/msg", msg) => 개인 처리
		 */
		// 서버가 아니라 브로커로 바로 메세지 전송 => Message Hook
		registry.setApplicationDestinationPrefixes("/app");
		registry.setUserDestinationPrefix("/user"); // 1:1
	}

}
