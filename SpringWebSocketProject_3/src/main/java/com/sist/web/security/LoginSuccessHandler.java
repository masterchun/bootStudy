package com.sist.web.security;

import java.io.IOException;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final LoginSessionRegistry loginSessionRegistry;
	private final WebSocketSessionRegistry wsRegistry;
	private final SimpMessagingTemplate template;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String userid = authentication.getName();
		HttpSession newSession = request.getSession();
		String newSessionId = newSession.getId();
		
		// 기존 세션을 확인
		String oldSessionId = loginSessionRegistry.get(userid);
		
		// 중복 로그인 처리
		if (oldSessionId != null && !oldSessionId.equals(newSessionId)) {
			wsRegistry.getSession(userid).forEach(sid -> {
				template.convertAndSendToUser(userid, "/queuej/force-disconnect", "DUPLICATE_LOGIN");
			});
			
			SessionUtils.invalidate(oldSessionId);
		}
		
		loginSessionRegistry.registry(userid, newSessionId);
		newSession.setAttribute("userid", userid);
		response.sendRedirect("/");
	}

}
