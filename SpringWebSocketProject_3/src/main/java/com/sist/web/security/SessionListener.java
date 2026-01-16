package com.sist.web.security;

import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

// 감지 => 생성, 소멸
@Controller
public class SessionListener implements HttpSessionListener {

	// 세션 생성시 호출
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SessionUtils.add(se.getSession());
	}

	// 세션 종료시 호출
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SessionUtils.remove(se.getSession());
	}

}
