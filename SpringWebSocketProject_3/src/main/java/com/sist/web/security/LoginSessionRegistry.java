package com.sist.web.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class LoginSessionRegistry {
	// userid => HttpSession 
	private final Map<String, String> sessions = new ConcurrentHashMap<>();
	
	// 기존의 세션 ID 조회 => 중복 제거
	public String get(String userid) {
		return sessions.get(userid);
	}
	
	// 로그인 성공시 session에 등록
	public void registry(String userid, String sessionId) {
		sessions.put(userid, sessionId);
	}
	
	// 로그아웃시 제거
	public void remove(String userid) {
		sessions.remove(userid);
	}
}
